package lotto.model

import lotto.model.LottoConstants.LOTTO_NUMBER_COUNT
import lotto.model.LottoConstants.MAX_LOTTO_NUMBER
import lotto.model.LottoConstants.MIN_LOTTO_NUMBER

class Lotto(numbers: List<Int>) {
    val numbers: List<Int> = numbers.sorted()

    init {
        validateSize(numbers)
        validateDuplicate(numbers)
        validateRange(numbers)
    }

    fun contains(number: Int): Boolean = numbers.contains(number)

    fun countMatches(other: Lotto): Int = numbers.count { other.contains(it) }

    private fun validateSize(numbers: List<Int>) {
        require(numbers.size == LOTTO_NUMBER_COUNT) {
            "[ERROR] 로또 번호는 ${LOTTO_NUMBER_COUNT}개여야 합니다."
        }
    }

    private fun validateDuplicate(numbers: List<Int>) {
        require(numbers.toSet().size == numbers.size) {
            "[ERROR] 로또 번호에 중복된 숫자가 있을 수 없습니다."
        }
    }

    private fun validateRange(numbers: List<Int>) {
        require(numbers.all { it in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER }) {
            "[ERROR] 로또 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER} 사이여야 합니다."
        }
    }
}
