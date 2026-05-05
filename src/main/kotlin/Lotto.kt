package lotto

class Lotto(private val numbers: List<Int>) {
    init {
        validateSize(numbers)
        validateDuplicate(numbers)
        validateRange(numbers)
    }

    private fun validateSize(numbers: List<Int>) {
        if (numbers.size != 6) {
            throw IllegalArgumentException("로또 번호는 6개여야 합니다.")
        }
    }

    private fun validateDuplicate(numbers: List<Int>) {
        if (numbers.distinct().size != 6) {
            throw IllegalArgumentException("로또 번호는 중복될 수 없습니다.")
        }
    }

    private fun validateRange(numbers: List<Int>) {
        if (numbers.any { it !in 1..45 }) {
            throw IllegalArgumentException("로또 번호는 1부터 45 사이여야 합니다.")
        }
    }

    fun getNumbers(): List<Int> = numbers.sorted()
}