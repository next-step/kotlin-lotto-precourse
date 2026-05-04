package lotto

import lotto.domain.Lotto
import lotto.domain.PurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val purchaseAmount = PurchaseAmount(InputView.readPurchaseAmount())
    val lottos = List(purchaseAmount.lottoCount()) { Lotto.generate() }
    OutputView.printLottos(lottos)
}
