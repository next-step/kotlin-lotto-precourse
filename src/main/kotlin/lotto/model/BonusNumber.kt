package lotto.model

import lotto.model.LottoConstants.MAX_LOTTO_NUMBER
import lotto.model.LottoConstants.MIN_LOTTO_NUMBER

@JvmInline
value class BonusNumber(val value: Int) {
    init {
        require(value in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) {
            "[ERROR] 보너스 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER} 사이여야 합니다."
        }
    }

    companion object {
        fun of(input: String, winningLotto: Lotto): BonusNumber {
            val number = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.")
            require(!winningLotto.contains(number)) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
            return BonusNumber(number)
        }
    }
}
