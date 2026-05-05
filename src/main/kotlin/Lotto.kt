class Lotto(private val numbers: Set<Int>) {

    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
    }

    fun matchCount(winning: Set<Int>): Int {
        return numbers.count { it in winning }
    }

    fun contains(number: Int): Boolean {
        return numbers.contains(number)
    }

    override fun toString(): String {
        return numbers.sorted().toString()
    }
}