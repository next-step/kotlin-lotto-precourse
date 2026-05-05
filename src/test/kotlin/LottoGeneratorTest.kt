import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoGeneratorTest {

    @Test
    fun `구매 금액으로 로또 개수 생성`() {
        val lottos = LottoGenerator.generate(5000)

        assertThat(lottos).hasSize(5)
    }

    @Test
    fun `로또 번호는 1부터 45 사이`() {
        val lotto = LottoGenerator.generate(1000).first()

        assertThat(lotto.toString())
            .containsPattern("([1-9]|[1-3][0-9]|4[0-5])")
    }
}