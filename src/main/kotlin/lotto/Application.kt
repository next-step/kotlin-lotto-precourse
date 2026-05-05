package lotto

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val purchaseAmount = retryOnError { PurchaseAmount(InputView.readPurchaseAmount()) }
    val lottos = List(purchaseAmount.lottoCount()) { Lotto.generate() }
    OutputView.printLottos(lottos)

    val winningNumbers = retryOnError { Lotto(InputView.readWinningNumbers()) }
    val winningLotto = retryOnError { WinningLotto(winningNumbers, InputView.readBonusNumber()) }

    val result = LottoResult(lottos, winningLotto, purchaseAmount)
    OutputView.printResult(result)
}

private fun <T> retryOnError(action: () -> T): T {
    while (true) {
        val result = runCatching { action() }
        if (result.isSuccess) return result.getOrThrow()
        println(result.exceptionOrNull()?.message)
    }
}
