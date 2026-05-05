package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 6, 7)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `로또 번호가 범위를 벗어나면 예외가 발생한다`() {
        assertThatThrownBy { Lotto(listOf(0, 2, 3, 4, 5, 6)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
        assertThatThrownBy { Lotto(listOf(1, 2, 3, 4, 5, 46)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `번호는 정렬된 형태로 저장된다`() {
        val lotto = Lotto(listOf(6, 1, 4, 2, 5, 3))
        assertThat(lotto.numbers).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `다른 로또와의 일치 개수를 계산할 수 있다`() {
        val ticket = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val winning = Lotto(listOf(1, 2, 3, 10, 11, 12))
        assertThat(ticket.countMatches(winning)).isEqualTo(3)
    }

    @Test
    fun `포함 여부를 확인할 수 있다`() {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        assertThat(lotto.contains(3)).isTrue()
        assertThat(lotto.contains(7)).isFalse()
    }
}
