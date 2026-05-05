package lotto.domain.service

import lotto.domain.Lotto
import lotto.domain.Lottos
import lotto.domain.PurchaseAmount

class LottoGenerator {
    fun generate(): Lotto {
        val numbers = (1..45).shuffled().take(6)
        return Lotto(numbers)
    }

    fun generateLottos(purchaseAmount: PurchaseAmount): Lottos {
        val count = purchaseAmount.getLottoPurchaseCount()
        val lottos = List(count) { generate() }
        return Lottos(lottos)
    }
}
