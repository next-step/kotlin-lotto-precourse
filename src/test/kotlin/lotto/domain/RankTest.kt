package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {

    private val winningLotto = WinningLotto(
        numbers = Lotto(listOf(1, 2, 3, 4, 5, 6)),
        bonusNumber = 7,
    )

    @Test
    fun `6개 일치하면 1등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `5개 일치하고 보너스 번호가 일치하면 2등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `5개 일치하고 보너스 번호가 일치하지 않으면 3등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 8))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `4개 일치하면 4등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 8, 9))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `3개 일치하면 5등이다`() {
        val lotto = Lotto(listOf(1, 2, 3, 8, 9, 10))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `2개 이하 일치하면 낙첨이다`() {
        val lotto = Lotto(listOf(1, 2, 8, 9, 10, 11))
        assertThat(winningLotto.rank(lotto)).isEqualTo(Rank.MISS)
    }
}
