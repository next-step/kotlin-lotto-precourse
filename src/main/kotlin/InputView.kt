object InputView {

    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")

        var purchase = readInt()
        validatePurchase(purchase)

        return purchase
    }

    fun readWinningNumbers(): Set<Int> {
        println("당첨 번호를 입력해 주세요.")
        return readNumbers()
    }

    fun readBonusNumber(winning: Set<Int>): Int {
        println("보너스 번호를 입력해 주세요.")
        val bonus = readInt()
        if (bonus in winning) {
            throw IllegalArgumentException("[ERROR] 보너스 번호 중복")
        }
        if(bonus !in 1..45 ) {
            throw IllegalArgumentException("[ERROR] 번호 범위 오류")
        }
        return bonus
    }

    private fun readInt(): Int {
        val input = readln()
        return input.toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.")
    }

    private fun readNumbers(): Set<Int> {
        val input = readln()
        val numbers = input.split(",").map { it.trim().toIntOrNull() }
        if (numbers.any { it == null }) {
            throw IllegalArgumentException("[ERROR] 숫자 형식 오류")
        }

        val result = numbers.filterNotNull().toSet()

        if (result.size != 6 || result.any { it !in 1..45 }) {
            throw IllegalArgumentException("[ERROR] 번호 범위 오류")
        }

        return result
    }

    private fun validatePurchase(purchase: Int) {
        if (purchase < 1000) {
            throw IllegalArgumentException("[ERROR] 로또는 1개 이상 사야 합니다.")
        }
    }
}