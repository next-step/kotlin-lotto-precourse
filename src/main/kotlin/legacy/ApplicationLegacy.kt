package legacy

// 옛날 스타일 — 비교용
// 실행: fun main() 을 주석 해제

/*
fun main() = runLegacyLotto()
*/

enum class LegacyRank(val prize: Long, val description: String) {
    FIRST(2_000_000_000, "6개 일치"),
    SECOND(30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(1_500_000, "5개 일치"),
    FOURTH(50_000, "4개 일치"),
    FIFTH(5_000, "3개 일치"),
    MISS(0, "");

    companion object {
        fun of(matchCount: Int, bonusMatch: Boolean): LegacyRank = when {
            matchCount == 6 -> FIRST
            matchCount == 5 && bonusMatch -> SECOND
            matchCount == 5 -> THIRD
            matchCount == 4 -> FOURTH
            matchCount == 3 -> FIFTH
            else -> MISS
        }
    }
}

fun runLegacyLotto() {
    val purchaseAmount = readPurchaseAmount()
    val lottos = generateLottos(purchaseAmount)
    val winningNumbers = readWinningNumbers()
    val bonusNumber = readBonusNumber(winningNumbers)
    printResult(lottos, winningNumbers, bonusNumber, purchaseAmount)
}

private fun readPurchaseAmount(): Int {
    while (true) {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        val amount = input.toIntOrNull()
        when {
            amount == null -> println("[ERROR] 숫자를 입력해 주세요.")
            amount <= 0 -> println("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.")
            amount % 1000 != 0 -> println("[ERROR] 구입 금액은 1,000원 단위여야 합니다.")
            else -> return amount
        }
    }
}

private fun generateLottos(purchaseAmount: Int): List<List<Int>> {
    val lottoCount = purchaseAmount / 1000
    println("\n${lottoCount}개를 구매했습니다.")
    return List(lottoCount) {
        val lotto = (1..45).shuffled().take(6).sorted()
        println(lotto)
        lotto
    }
}

private fun readWinningNumbers(): List<Int> {
    while (true) {
        println("\n당첨 번호를 입력해 주세요.")
        val parts = readln().split(",")
        val parsed = parts.mapNotNull { it.trim().toIntOrNull() }
        when {
            parsed.size != parts.size -> println("[ERROR] 당첨 번호는 숫자여야 합니다.")
            parsed.size != 6 -> println("[ERROR] 로또 번호는 6개여야 합니다.")
            parsed.any { it !in 1..45 } -> println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.")
            parsed.toSet().size != 6 -> println("[ERROR] 로또 번호는 중복될 수 없습니다.")
            else -> return parsed
        }
    }
}

private fun readBonusNumber(winningNumbers: List<Int>): Int {
    while (true) {
        println("\n보너스 번호를 입력해 주세요.")
        val input = readln()
        val number = input.toIntOrNull()
        when {
            number == null -> println("[ERROR] 보너스 번호는 숫자여야 합니다.")
            number !in 1..45 -> println("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
            number in winningNumbers -> println("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.")
            else -> return number
        }
    }
}

private fun printResult(lottos: List<List<Int>>, winningNumbers: List<Int>, bonusNumber: Int, purchaseAmount: Int) {
    val rankCounts = lottos
        .map { LegacyRank.of(it.count { n -> n in winningNumbers }, bonusNumber in it) }
        .groupingBy { it }
        .eachCount()

    println("\n당첨 통계")
    println("---")
    listOf(LegacyRank.FIFTH, LegacyRank.FOURTH, LegacyRank.THIRD, LegacyRank.SECOND, LegacyRank.FIRST).forEach {
        println("${it.description} (${"%,d".format(it.prize)}원) - ${rankCounts.getOrDefault(it, 0)}개")
    }
    val totalPrize = rankCounts.entries.sumOf { (rank, count) -> rank.prize * count }
    println("총 수익률은 ${"%.1f".format(totalPrize.toDouble() / purchaseAmount * 100)}%입니다.")
}
