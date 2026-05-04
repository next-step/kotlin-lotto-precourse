package lotto.domain

class WinningLotto(private val numbers: List<Int>, val bonusNumber: Int) {
    init {
        Lotto(numbers)
        require(bonusNumber in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) {
            "[ERROR] 보너스 번호는 ${LOTTO_MIN_NUMBER}부터 $LOTTO_MAX_NUMBER 사이의 숫자여야 합니다."
        }
        require(bonusNumber !in numbers) {
            "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
        }
    }

    fun match(lotto: Lotto): LottoRank {
        val matchCount = lotto.countMatch(numbers)
        val bonusMatch = lotto.contains(bonusNumber)
        return LottoRank.of(matchCount, bonusMatch)
    }
}
