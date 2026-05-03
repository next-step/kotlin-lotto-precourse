package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoResultTest {

    @Test
    @DisplayName("등수별 당첨 횟수를 집계한다")
    fun `counts ranks correctly`() {
        val ranks = listOf(Rank.FIRST, Rank.FIFTH, Rank.FIFTH, Rank.NONE)
        val result = LottoResult(ranks)
        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1)
        assertThat(result.countOf(Rank.FIFTH)).isEqualTo(2)
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(0)
    }

    @Test
    @DisplayName("총 상금을 계산한다")
    fun `calculates total prize`() {
        val ranks = listOf(Rank.FIFTH, Rank.FIFTH, Rank.FOURTH)
        val result = LottoResult(ranks)
        assertThat(result.totalPrize()).isEqualTo(60_000L)
    }

    @Test
    @DisplayName("PDF 예시: 8,000원 구매하여 5등 1개 → 수익률 62.5%")
    fun `calculates yield rate matching PDF example`() {
        val ranks = List(7) { Rank.NONE } + Rank.FIFTH
        val result = LottoResult(ranks)
        val money = Money(8_000)
        assertThat(result.yieldRate(money)).isEqualTo(62.5)
    }

    @Test
    @DisplayName("당첨이 하나도 없으면 수익률은 0이다")
    fun `yield rate is zero when no winnings`() {
        val ranks = List(8) { Rank.NONE }
        val result = LottoResult(ranks)
        val money = Money(8_000)
        assertThat(result.yieldRate(money)).isEqualTo(0.0)
    }
}
