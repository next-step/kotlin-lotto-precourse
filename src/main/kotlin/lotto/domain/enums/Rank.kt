package lotto.domain.enums

enum class Rank(
    val matchCount: Int,
    val bonusMatch: Boolean,
    val prize: Int
) {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    fun getDescription(): String {
        if (this == NONE) return ""
        if (bonusMatch) return "${matchCount}개 일치, 보너스 볼 일치"
        return "${matchCount}개 일치"
    }

    companion object {
        fun of(matchCount: Int, bonusMatch: Boolean): Rank {
            return entries.find { it.matchCount == matchCount && it.bonusMatch == bonusMatch }
                ?: NONE
        }
    }
}
