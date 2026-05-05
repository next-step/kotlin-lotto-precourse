package lotto

class LottoGame {
    fun run() {
        val amount = readPurchaseAmount()
        val lottos = buyLottos(amount)
        printLottos(lottos)
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
        lottos.forEach { lotto ->
            println(lotto.getNumbers())
        }
    }
}