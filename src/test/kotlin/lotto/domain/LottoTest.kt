package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class LottoTest {
    @Test
    fun `로또 번호가 6개일 때 정상적으로 생성된다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 6)

        // when & then
        assertDoesNotThrow {
            Lotto(numbers)
        }
    }

    @Test
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5)

        // when & then
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 5)

        // when & then
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("중복")
    }

    @Test
    fun `로또 번호가 1보다 작으면 예외가 발생한다`() {
        // given
        val numbers = listOf(0, 1, 2, 3, 4, 5)

        // when & then
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("1부터 45")
    }

    @Test
    fun `로또 번호가 45보다 크면 예외가 발생한다`() {
        // given
        val numbers = listOf(1, 2, 3, 4, 5, 46)

        // when & then
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("1부터 45")
    }

    @Test
    fun `로또 번호가 1부터 45 사이의 숫자일 때 정상적으로 생성된다`() {
        // given
        val numbers = listOf(1, 10, 20, 30, 40, 45)

        // when
        val lotto = Lotto(numbers)

        // then
        assertThat(lotto.numbers).containsExactly(1, 10, 20, 30, 40, 45)
    }
}
