package lotto.domain

enum class LottoRank(val matchCount: Int, val bonusMatch: Boolean, val prize: Long) {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0);

    companion object {
        fun of(matchCount: Int, bonusMatch: Boolean): LottoRank = when (matchCount) {
            6 -> FIRST
            5 if bonusMatch -> SECOND
            5 -> THIRD
            4 -> FOURTH
            3 -> FIFTH
            else -> NONE
        }
    }
}