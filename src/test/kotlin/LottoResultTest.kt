import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoResultTest {

    @Test
    fun `1등 당첨`() {
        val lotto = Lotto(setOf(1,2,3,4,5,6))

        val result = LottoResult.calculate(
            listOf(lotto),
            setOf(1,2,3,4,5,6),
            7
        )

        assertThat(result[1]).isEqualTo(1)
    }

    @Test
    fun `2등 당첨 보너스 포함`() {
        val lotto = Lotto(setOf(1,2,3,4,5,7))

        val result = LottoResult.calculate(
            listOf(lotto),
            setOf(1,2,3,4,5,6),
            7
        )

        assertThat(result[2]).isEqualTo(1)
    }

    @Test
    fun `3등 당첨`() {
        val lotto = Lotto(setOf(1,2,3,4,5,10))

        val result = LottoResult.calculate(
            listOf(lotto),
            setOf(1,2,3,4,5,6),
            7
        )

        assertThat(result[3]).isEqualTo(1)
    }

    @Test
    fun `수익률 계산`() {
        val result = mapOf(1 to 1) // 1등 1개

        val profit = LottoResult.calculateProfit(result, 1000)

        assertThat(profit).isEqualTo(200_000_000.0)
    }
}