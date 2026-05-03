package lotto.model

class WinningLotto(
    private val winningLotto: Lotto,
    private val bonus: LottoNumber,
) {
    init {
        require(bonus !in winningLotto.numbers) {
            "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
        }
    }

    fun match(target: Lotto): MatchResult {
        val matchCount = winningLotto.numbers.count { it in target.numbers }
        val bonusMatched = bonus in target.numbers
        return MatchResult(matchCount, bonusMatched)
    }
}
