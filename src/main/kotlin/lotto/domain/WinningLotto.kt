package lotto.domain

class WinningLotto(val numbers: Lotto, val bonusNumber: Int) {

    init {
        require(bonusNumber in Lotto.MIN_NUMBER..Lotto.MAX_NUMBER) {
            "[ERROR] 보너스 번호는 ${Lotto.MIN_NUMBER}부터 ${Lotto.MAX_NUMBER} 사이의 숫자여야 합니다."
        }
        require(bonusNumber !in numbers.getNumbers()) {
            "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
        }
    }

    fun rank(lotto: Lotto): Rank {
        val matchCount = lotto.countMatch(numbers.getNumbers())
        val bonusMatch = matchCount == 5 && lotto.contains(bonusNumber)
        return Rank.of(matchCount, bonusMatch)
    }
}
