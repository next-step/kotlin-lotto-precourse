package lotto.controller

import lotto.model.BonusNumber
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.PurchaseAmount
import lotto.model.WinningResult
import lotto.util.LottoNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val numberGenerator: LottoNumberGenerator,
) {
    fun run() {
        val amount = inputPurchaseAmount()
        val tickets = LottoMachine(numberGenerator).issue(amount)
        outputView.printPurchasedLottos(tickets)
        val winningLotto = inputWinningLotto()
        val bonus = inputBonusNumber(winningLotto)
        val result = WinningResult.of(tickets, winningLotto, bonus)
        outputView.printWinningStatistics(result, amount)
    }

    private fun inputPurchaseAmount(): PurchaseAmount = retryOnError {
        PurchaseAmount.from(inputView.readPurchaseAmount())
    }

    private fun inputWinningLotto(): Lotto = retryOnError {
        val raw = inputView.readWinningNumbers()
        Lotto(parseWinningNumbers(raw))
    }

    private fun inputBonusNumber(winningLotto: Lotto): BonusNumber = retryOnError {
        BonusNumber.of(inputView.readBonusNumber(), winningLotto)
    }

    private fun parseWinningNumbers(raw: String): List<Int> {
        return raw.split(",").map { token ->
            token.trim().toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
        }
    }

    private fun <T> retryOnError(block: () -> T): T {
        while (true) {
            runCatching { return block() }
                .onFailure { outputView.printError(it.message ?: "[ERROR] 잘못된 입력입니다.") }
        }
    }
}
