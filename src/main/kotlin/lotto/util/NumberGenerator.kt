package lotto.util

import lotto.model.LottoNumber

interface NumberGenerator {
    fun generate(): List<LottoNumber>
}
