package lotto.domain

data class WinningNumbers(
    val numbers: Lotto,
    val bonusNumber: Int
) {
    init {
        require(bonusNumber in LOTTO_NUMBER_RANGE) { "[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다." }
        require(bonusNumber !in numbers.numbers) { "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다." }
    }

    companion object {
        private val LOTTO_NUMBER_RANGE = 1..45
    }
}
