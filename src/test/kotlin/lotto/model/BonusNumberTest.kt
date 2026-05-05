package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BonusNumberTest {
    private val winning = Lotto(listOf(1, 2, 3, 4, 5, 6))

    @Test
    fun `당첨 번호와 중복되면 예외가 발생한다`() {
        assertThatThrownBy { BonusNumber.of("3", winning) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `범위를 벗어나면 예외가 발생한다`() {
        assertThatThrownBy { BonusNumber.of("46", winning) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `숫자가 아니면 예외가 발생한다`() {
        assertThatThrownBy { BonusNumber.of("a", winning) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `정상 입력시 보너스 번호를 생성한다`() {
        assertThat(BonusNumber.of("7", winning).value).isEqualTo(7)
    }
}
