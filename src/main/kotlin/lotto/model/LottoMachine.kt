package lotto.model

import lotto.util.NumberGenerator

class LottoMachine(private val generator: NumberGenerator) {
    fun issue(count: Int): List<Lotto> {
        return List(count) { Lotto(generator.generate()) }
    }
}
