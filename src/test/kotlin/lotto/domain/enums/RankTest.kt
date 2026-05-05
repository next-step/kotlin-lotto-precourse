package lotto.domain.enums

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `6개 일치하면 1등이다`() {
        // given & when
        val rank = Rank.of(6, false)

        // then
        assertThat(rank).isEqualTo(Rank.FIRST)
        assertThat(rank.prize).isEqualTo(2_000_000_000)
    }

    @Test
    fun `5개 일치하고 보너스볼이 일치하면 2등이다`() {
        // given & when
        val rank = Rank.of(5, true)

        // then
        assertThat(rank).isEqualTo(Rank.SECOND)
        assertThat(rank.prize).isEqualTo(30_000_000)
    }

    @Test
    fun `5개 일치하고 보너스볼이 일치하지 않으면 3등이다`() {
        // given & when
        val rank = Rank.of(5, false)

        // then
        assertThat(rank).isEqualTo(Rank.THIRD)
        assertThat(rank.prize).isEqualTo(1_500_000)
    }

    @Test
    fun `4개 일치하면 4등이다`() {
        // given & when
        val rank = Rank.of(4, false)

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH)
        assertThat(rank.prize).isEqualTo(50_000)
    }

    @Test
    fun `3개 일치하면 5등이다`() {
        // given & when
        val rank = Rank.of(3, false)

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH)
        assertThat(rank.prize).isEqualTo(5_000)
    }

    @Test
    fun `2개 이하로 일치하면 당첨되지 않는다`() {
        // given & when
        val rank = Rank.of(2, false)

        // then
        assertThat(rank).isEqualTo(Rank.NONE)
        assertThat(rank.prize).isEqualTo(0)
    }

    @Test
    fun `일치하는 번호가 없으면 당첨되지 않는다`() {
        // given & when
        val rank = Rank.of(0, false)

        // then
        assertThat(rank).isEqualTo(Rank.NONE)
    }

    @Test
    fun `1등은 보너스볼 매칭이 필요없다`() {
        // given & when
        val rank = Rank.FIRST

        // then
        assertThat(rank.bonusMatch).isFalse()
    }

    @Test
    fun `2등은 보너스볼 매칭이 필요하다`() {
        // given & when
        val rank = Rank.SECOND

        // then
        assertThat(rank.bonusMatch).isTrue()
    }
}
