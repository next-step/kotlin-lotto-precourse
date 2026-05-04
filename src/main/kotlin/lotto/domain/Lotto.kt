package lotto.domain

class Lotto(numbers: List<Int>) {
    private val numbers: List<Int>

    init {
        require(numbers.size == LOTTO_NUMBER_COUNT) { "[ERROR] 로또 번호는 ${LOTTO_NUMBER_COUNT}개여야 합니다." }
        require(numbers.all { it in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER }) { "[ERROR] 로또 번호는 ${LOTTO_MIN_NUMBER}부터 ${LOTTO_MAX_NUMBER} 사이의 숫자여야 합니다." }
        require(numbers.distinct().size == numbers.size) { "[ERROR] 로또 번호는 중복될 수 없습니다." }
        this.numbers = numbers.sorted()
    }

    fun countMatch(winningNumbers: List<Int>): Int = numbers.count { it in winningNumbers }

    fun contains(number: Int): Boolean = number in numbers

    override fun toString(): String = numbers.toString()
}