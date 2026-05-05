import kotlin.random.Random

fun main() {
    LottoGame().run()
}

class LottoGame {
    fun run() {
        val purchase = InputView.readPurchaseAmount()

        val lottos = LottoGenerator.generate(purchase / 1000)

        OutputView.printLottos(lottos)

        val winning = InputView.readWinningNumbers()
        val bonus = InputView.readBonusNumber(winning)

        val result = LottoResult.calculate(lottos, winning, bonus)

        OutputView.printResult(result, purchase)
    }
}