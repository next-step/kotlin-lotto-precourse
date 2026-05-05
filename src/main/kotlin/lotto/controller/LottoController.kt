package lotto.controller

import lotto.domain.Lottos
import lotto.domain.PurchaseAmount
import lotto.domain.WinningNumbers
import lotto.domain.WinningResult
import lotto.domain.service.LottoGenerator
import lotto.domain.service.LottoMatcher
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoGenerator: LottoGenerator,
) {
    fun run() {
        val purchaseAmount = inputView.readPurchaseAmount()
        val lottos = lottoGenerator.generateLottos(purchaseAmount)
        printLottos(purchaseAmount, lottos)
        val winningNumbers = inputView.readWinningNumbers()
        val winningResult = calculateWinningResult(lottos, winningNumbers)
        outputView.printWinningStatistics(winningResult, purchaseAmount)
    }

    private fun printLottos(purchaseAmount: PurchaseAmount, lottos: Lottos) {
        outputView.printPurchaseCount(purchaseAmount.getLottoPurchaseCount())
        outputView.printLottos(lottos)
    }

    private fun calculateWinningResult(
        lottos: Lottos,
        winningNumbers: WinningNumbers
    ): WinningResult {
        val lottoMatcher = LottoMatcher()
        val ranks = lottos.lottos.map { lotto ->
            lottoMatcher.match(lotto, winningNumbers)
        }
        return WinningResult(ranks)
    }
}