package lotto.application

import lotto.domain.Lotto

class LottoGenerator {
    fun generate(): Lotto {
        val numbers = (1..45).shuffled().take(6)
        return Lotto(numbers)
    }
}
