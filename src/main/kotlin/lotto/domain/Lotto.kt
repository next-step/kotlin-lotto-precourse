package lotto.domain

data class Lotto(val numbers: List<Int>) {
    init {
        require(numbers.size == LOTTO_NUMBER_COUNT) { "[ERROR] 로또 번호는 ${LOTTO_NUMBER_COUNT}개여야 합니다." }
        require(numbers.all { it in LOTTO_NUMBER_RANGE }) { "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(numbers.size == numbers.toSet().size) { "[ERROR] 로또 번호에 중복된 숫자가 있습니다." }
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
        private val LOTTO_NUMBER_RANGE = 1..45
    }
}
