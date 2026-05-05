package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 일치는 1등이다`() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치 + 보너스는 2등이다`() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치 보너스 미일치는 3등이다`() {
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치는 4등이다`() {
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치는 5등이다`() {
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개 이하 일치는 NONE이다`() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.NONE)
        assertThat(Rank.of(0, true)).isEqualTo(Rank.NONE)
    }
}
