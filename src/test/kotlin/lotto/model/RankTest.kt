package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RankTest {

    @Test
    @DisplayName("6개 일치하면 1등")
    fun `first when six matches`() {
        assertThat(Rank.of(MatchResult(matchCount = 6, bonusMatched = false))).isEqualTo(Rank.FIRST)
    }

    @Test
    @DisplayName("5개 일치 + 보너스 일치이면 2등")
    fun `second when five matches with bonus`() {
        assertThat(Rank.of(MatchResult(matchCount = 5, bonusMatched = true))).isEqualTo(Rank.SECOND)
    }

    @Test
    @DisplayName("5개 일치 + 보너스 불일치이면 3등")
    fun `third when five matches without bonus`() {
        assertThat(Rank.of(MatchResult(matchCount = 5, bonusMatched = false))).isEqualTo(Rank.THIRD)
    }

    @Test
    @DisplayName("4개 일치하면 4등")
    fun `fourth when four matches`() {
        assertThat(Rank.of(MatchResult(matchCount = 4, bonusMatched = false))).isEqualTo(Rank.FOURTH)
    }

    @Test
    @DisplayName("3개 일치하면 5등")
    fun `fifth when three matches`() {
        assertThat(Rank.of(MatchResult(matchCount = 3, bonusMatched = false))).isEqualTo(Rank.FIFTH)
    }

    @Test
    @DisplayName("2개 이하 일치하면 미당첨")
    fun `none when two or less matches`() {
        assertThat(Rank.of(MatchResult(matchCount = 2, bonusMatched = false))).isEqualTo(Rank.NONE)
        assertThat(Rank.of(MatchResult(matchCount = 0, bonusMatched = true))).isEqualTo(Rank.NONE)
    }

    @Test
    @DisplayName("각 등수의 상금이 요구사항과 일치한다")
    fun `prize amount matches requirement`() {
        assertThat(Rank.FIRST.prize).isEqualTo(2_000_000_000L)
        assertThat(Rank.SECOND.prize).isEqualTo(30_000_000L)
        assertThat(Rank.THIRD.prize).isEqualTo(1_500_000L)
        assertThat(Rank.FOURTH.prize).isEqualTo(50_000L)
        assertThat(Rank.FIFTH.prize).isEqualTo(5_000L)
    }
}
