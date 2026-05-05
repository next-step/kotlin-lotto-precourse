package lotto.model

enum class Rank(val matchCount: Int, val prize: Int, val requireBonus: Boolean) {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    NONE(0, 0, false);

    companion object {
        fun of(matchCount: Int, matchBonus: Boolean): Rank {
            if (matchCount == SECOND.matchCount && matchBonus) return SECOND
            return values().firstOrNull { it != SECOND && it.matchCount == matchCount } ?: NONE
        }
    }
}
