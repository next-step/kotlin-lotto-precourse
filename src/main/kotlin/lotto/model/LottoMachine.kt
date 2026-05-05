package lotto.model

import lotto.util.LottoNumberGenerator

class LottoMachine(private val numberGenerator: LottoNumberGenerator) {
    fun issue(amount: PurchaseAmount): List<Lotto> {
        return List(amount.lottoCount()) { Lotto(numberGenerator.generate()) }
    }
}
