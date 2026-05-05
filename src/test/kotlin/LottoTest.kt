import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class LottoTest {

    @Test
    fun `로또는 6개의 숫자가 아니면 예외`() {
        assertThatThrownBy {
            Lotto(setOf(1, 2, 3))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `당첨 번호 매칭 개수 계산`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6))

        val result = lotto.matchCount(setOf(1, 2, 3, 7, 8, 9))

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `보너스 번호 포함 여부`() {
        val lotto = Lotto(setOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto.contains(3)).isTrue()
        assertThat(lotto.contains(10)).isFalse()
    }
}