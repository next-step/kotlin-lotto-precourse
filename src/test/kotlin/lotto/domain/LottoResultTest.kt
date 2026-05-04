package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {

    private val winningLotto = WinningLotto(
        numbers = Lotto(listOf(1, 2, 3, 4, 5, 6)),
        bonusNumber = 7,
    )

    @Test
    fun `등수별 당첨 횟수를 집계한다`() {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 8, 9, 10)),  // 5등
            Lotto(listOf(1, 2, 3, 8, 9, 10)),  // 5등
            Lotto(listOf(10, 11, 12, 13, 14, 15)), // 낙첨
        )
        val result = LottoResult(lottos, winningLotto, PurchaseAmount(3000))

        assertThat(result.rankCounts[Rank.FIFTH]).isEqualTo(2)
        assertThat(result.rankCounts.getOrDefault(Rank.FOURTH, 0)).isEqualTo(0)
    }

    @Test
    fun `수익률을 계산한다`() {
        val lottos = listOf(Lotto(listOf(1, 2, 3, 8, 9, 10)))  // 5등 5000원
        val result = LottoResult(lottos, winningLotto, PurchaseAmount(1000))

        assertThat(result.profitRate).isEqualTo(500.0)
    }
}
