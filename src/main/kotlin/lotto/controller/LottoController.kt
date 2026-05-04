package lotto.controller

import lotto.domain.LOTTO_PRICE
import lotto.domain.LottoMachine
import lotto.domain.LottoResult
import lotto.view.InputHandler
import lotto.view.OutputHandler

class LottoController(private val machine: LottoMachine = LottoMachine()) {

    fun run() {
        val purchaseAmount = InputHandler.readPurchaseAmount()
        val lottos = machine.generate(purchaseAmount / LOTTO_PRICE)
        OutputHandler.printLottos(lottos)

        val winningNumbers = InputHandler.readWinningNumbers()
        val bonusNumber = InputHandler.readBonusNumber(winningNumbers)

        val result = LottoResult(lottos, winningNumbers, bonusNumber, purchaseAmount)
        OutputHandler.printResult(result)
    }
}