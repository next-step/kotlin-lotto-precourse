package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class WinningNumbersTest {
    @Test
    fun `당첨 번호와 보너스 번호가 정상적으로 생성된다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 7

        // when & then
        assertDoesNotThrow {
            WinningNumbers(lotto, bonusNumber)
        }
    }

    @Test
    fun `보너스 번호가 1부터 45 사이일 때 정상적으로 생성된다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 45

        // when
        val winningNumbers = WinningNumbers(lotto, bonusNumber)

        // then
        assertThat(winningNumbers.bonusNumber).isEqualTo(45)
    }

    @Test
    fun `보너스 번호가 1보다 작으면 예외가 발생한다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 0

        // when & then
        assertThatThrownBy { WinningNumbers(lotto, bonusNumber) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("1부터 45")
    }

    @Test
    fun `보너스 번호가 45보다 크면 예외가 발생한다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 46

        // when & then
        assertThatThrownBy { WinningNumbers(lotto, bonusNumber) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("1부터 45")
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        // given
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 6

        // when & then
        assertThatThrownBy { WinningNumbers(lotto, bonusNumber) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
            .hasMessageContaining("중복")
    }
}
