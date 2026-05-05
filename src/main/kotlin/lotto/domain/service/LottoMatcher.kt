package lotto.domain.service

import lotto.domain.Lotto
import lotto.domain.WinningNumbers
import lotto.domain.enums.Rank

class LottoMatcher {
    fun match(lotto: Lotto, winningNumbers: WinningNumbers): Rank {
        val matchCount = countMatches(lotto, winningNumbers)
        val bonusMatch = isBonusMatch(lotto, winningNumbers, matchCount)
        return Rank.of(matchCount, bonusMatch)
    }

    private fun countMatches(lotto: Lotto, winningNumbers: WinningNumbers): Int {
        return lotto.numbers.count { it in winningNumbers.numbers.numbers }
    }

    private fun isBonusMatch(lotto: Lotto, winningNumbers: WinningNumbers, matchCount: Int): Boolean {
        if (matchCount != BONUS_MATCH_COUNT) return false
        return winningNumbers.bonusNumber in lotto.numbers
    }

    companion object {
        private const val BONUS_MATCH_COUNT = 5
    }
}
