package lotto

enum class LottoRank(val matchCount: Int, val prize: Int, val message: String) {
    FIRST(6, 2_000_000_000, "6개 일치 (2,000,000,000원)"),
    SECOND(5, 30_000_000, "5개 일치, 보너스 볼 일치 (30_000_000원)"),
    THIRD(5, 1_500_000, "5개 일치 (1,500_000원)"),
    FOURTH(4, 50_000, "4개 일치 (50_000원)"),
    FIFTH(3, 5_000, "3개 일치 (5,000원)"),
    NONE(0, 0, "");

    companion object {
        fun valueOf(matchCount: Int, bonusMatch: Boolean): LottoRank {
            return when (matchCount) {
                6 -> FIRST
                5 -> if (bonusMatch) SECOND else THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> NONE
            }
        }
    }
}