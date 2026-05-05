package lotto

class LottoGame {
    fun run() {
        val amount = readPurchaseAmount()
        val lottos = buyLottos(amount)
        printLottos(lottos)

        // Step 3 추가: 당첨 번호 및 보너스 번호 입력
        val winningNumbers = readWinningNumbers()
        val bonusNumber = readBonusNumber(winningNumbers)
    }

    private fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        val amount = input.toIntOrNull() ?: throw IllegalArgumentException("숫자만 입력 가능합니다.")
        validateAmount(amount)
        return amount
    }

    private fun validateAmount(amount: Int) {
        if (amount % 1000 != 0) {
            throw IllegalArgumentException("1,000원 단위로 입력해야 합니다.")
        }
    }

    private fun buyLottos(amount: Int): List<Lotto> {
        val count = amount / 1000
        println("\n${count}개를 구매했습니다.")
        return List(count) {
            Lotto((1..45).shuffled().take(6))
        }
    }

    private fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { println(it.getNumbers()) }
    }

    // --- Step 3 ---

    private fun readWinningNumbers(): List<Int> {
        println("\n당첨 번호를 입력해 주세요.")
        val input = readln()
        val numbers = input.split(",").map { it.trim().toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해 주세요.") }
        return Lotto(numbers).getNumbers() // Lotto 클래스의 init 블록을 통해 검증 수행
    }

    private fun readBonusNumber(winningNumbers: List<Int>): Int {
        println("\n보너스 번호를 입력해 주세요.")
        val input = readln()
        val bonus = input.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력해 주세요.")
        validateBonus(winningNumbers, bonus)
        return bonus
    }

    private fun validateBonus(winningNumbers: List<Int>, bonus: Int) {
        if (bonus !in 1..45) {
            throw IllegalArgumentException("보너스 번호는 1에서 45 사이여야 합니다.")
        }
        if (winningNumbers.contains(bonus)) {
            throw IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.")
        }
    }
}