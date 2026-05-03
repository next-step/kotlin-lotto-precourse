package lotto.model

enum class Rank(val matchCount: Int, val prize: Long) {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NONE(0, 0L);

    companion object {
        fun of(result: MatchResult): Rank {
            if (isSecond(result)) return SECOND
            return entries.firstOrNull { it != SECOND && it.matchCount == result.matchCount } ?: NONE
        }

        private fun isSecond(result: MatchResult): Boolean =
            result.matchCount == SECOND.matchCount && result.bonusMatched
    }
}
