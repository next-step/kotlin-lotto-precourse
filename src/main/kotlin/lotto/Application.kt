package lotto

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.PurchaseAmount
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val purchaseAmount = PurchaseAmount(InputView.readPurchaseAmount())
    val lottos = List(purchaseAmount.lottoCount()) { Lotto.generate() }
    OutputView.printLottos(lottos)

    val winningLotto = WinningLotto(
        numbers = Lotto(InputView.readWinningNumbers()),
        bonusNumber = InputView.readBonusNumber(),
    )

    val result = LottoResult(lottos, winningLotto, purchaseAmount)
    OutputView.printResult(result)
}
