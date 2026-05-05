package lotto.util

import lotto.model.LottoConstants.LOTTO_NUMBER_COUNT
import lotto.model.LottoConstants.MAX_LOTTO_NUMBER
import lotto.model.LottoConstants.MIN_LOTTO_NUMBER

class RandomLottoNumberGenerator : LottoNumberGenerator {
    override fun generate(): List<Int> {
        return (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).shuffled().take(LOTTO_NUMBER_COUNT).sorted()
    }
}
