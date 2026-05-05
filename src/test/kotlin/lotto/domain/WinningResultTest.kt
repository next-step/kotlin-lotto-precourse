package lotto.domain

import lotto.domain.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningResultTest {
    @Test
    fun `당첨되지 않으면 수익률은 0이다`() {
        // given
        val ranks = listOf(Rank.NONE, Rank.NONE, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(3000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(0.0)
    }

    @Test
    fun `5등 1개 당첨 시 수익률을 계산한다`() {
        // given
        val ranks = listOf(Rank.FIFTH, Rank.NONE, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(3000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(166.67)
    }

    @Test
    fun `여러 등수 당첨 시 총 수익률을 계산한다`() {
        // given
        val ranks = listOf(Rank.FIFTH, Rank.FOURTH, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(3000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(1833.33)
    }

    @Test
    fun `소수점 셋째 자리에서 반올림한다`() {
        // given
        val ranks = listOf(Rank.FIFTH, Rank.FIFTH, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(3000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(333.33)
    }

    @Test
    fun `1등 당첨 시 수익률을 계산한다`() {
        // given
        val ranks = listOf(Rank.FIRST)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(1000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(200000000.0)
    }

    @Test
    fun `구매 금액과 동일한 금액 당첨 시 수익률은 100이다`() {
        // given
        val ranks = listOf(Rank.FIFTH, Rank.FIFTH)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(10000)

        // when
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)

        // then
        assertThat(profitRate).isEqualTo(100.0)
    }
}
