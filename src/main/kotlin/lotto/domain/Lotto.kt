package lotto.domain

class Lotto(private val numbers: List<Int>) {

    init {
        require(numbers.size == NUMBER_COUNT) { "[ERROR] 로또 번호는 ${NUMBER_COUNT}개여야 합니다." }
        require(numbers.toSet().size == NUMBER_COUNT) { "[ERROR] 로또 번호는 중복될 수 없습니다." }
        require(numbers.all { it in MIN_NUMBER..MAX_NUMBER }) { "[ERROR] 로또 번호는 ${MIN_NUMBER}부터 ${MAX_NUMBER} 사이의 숫자여야 합니다." }
    }

    fun getNumbers(): List<Int> = numbers.sorted()

    fun countMatch(winningNumbers: List<Int>): Int = numbers.count { it in winningNumbers }

    fun contains(number: Int): Boolean = number in numbers

    companion object {
        const val NUMBER_COUNT = 6
        const val MIN_NUMBER = 1
        const val MAX_NUMBER = 45

        fun generate(): Lotto {
            val numbers = (MIN_NUMBER..MAX_NUMBER).shuffled().take(NUMBER_COUNT)
            return Lotto(numbers)
        }
    }
}
