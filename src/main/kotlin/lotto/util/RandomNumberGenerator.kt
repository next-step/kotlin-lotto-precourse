package lotto.util

import lotto.model.Lotto
import lotto.model.LottoNumber

class RandomNumberGenerator : NumberGenerator {
    override fun generate(): List<LottoNumber> {
        return (LottoNumber.MIN..LottoNumber.MAX)
            .shuffled()
            .take(Lotto.SIZE)
            .map { LottoNumber(it) }
    }
}
