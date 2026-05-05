package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.junit.jupiter.api.Test

class WinningResultTest {
    @Test
    fun `당첨 결과를 집계한다`() {
        val tickets = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 4, 5, 8)),
            Lotto(listOf(1, 2, 3, 10, 11, 12)),
            Lotto(listOf(20, 21, 22, 23, 24, 25)),
        )
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = BonusNumber.of("7", winning)
        val result = WinningResult.of(tickets, winning, bonus)

        assertThat(result.countOf(Rank.FIRST)).isEqualTo(1)
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(1)
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(1)
        assertThat(result.countOf(Rank.FIFTH)).isEqualTo(1)
        assertThat(result.countOf(Rank.NONE)).isEqualTo(1)
    }

    @Test
    fun `수익률을 계산한다 - 8000원에 5등 1장이면 62_5 퍼센트`() {
        val tickets = List(7) { Lotto(listOf(40, 41, 42, 43, 44, 45)) } +
            Lotto(listOf(1, 2, 3, 10, 11, 12))
        val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonus = BonusNumber.of("7", winning)
        val result = WinningResult.of(tickets, winning, bonus)
        val rate = result.profitRate(PurchaseAmount(8_000))

        assertThat(rate).isCloseTo(62.5, within(0.01))
    }
}
