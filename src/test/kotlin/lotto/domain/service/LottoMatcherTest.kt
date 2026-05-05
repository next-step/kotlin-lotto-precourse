package lotto.domain.service

import lotto.domain.Lotto
import lotto.domain.WinningNumbers
import lotto.domain.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMatcherTest {
    @Test
    fun `6개 일치하면 1등이다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치하고 보너스볼이 일치하면 2등이다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치하고 보너스볼이 일치하지 않으면 3등이다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 8))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치하면 4등이다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 3, 4, 8, 9))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치하면 5등이다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 3, 8, 9, 10))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개 이하로 일치하면 당첨되지 않는다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(1, 2, 8, 9, 10, 11))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.NONE)
    }

    @Test
    fun `일치하는 번호가 없으면 당첨되지 않는다`() {
        // given
        val matcher = LottoMatcher()
        val lotto = Lotto(listOf(8, 9, 10, 11, 12, 13))
        val winningNumbers = WinningNumbers(Lotto(listOf(1, 2, 3, 4, 5, 6)), 7)

        // when
        val rank = matcher.match(lotto, winningNumbers)

        // then
        assertThat(rank).isEqualTo(Rank.NONE)
    }
}
