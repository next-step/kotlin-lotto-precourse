package lotto.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WinningLottoTest {

    private val winningNumbers = Lotto(listOf(1, 2, 3, 4, 5, 6))

    @Test
    fun `보너스 번호가 범위를 벗어나면 예외가 발생한다`() {
        assertThatThrownBy { WinningLotto(winningNumbers, 46) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 예외가 발생한다`() {
        assertThatThrownBy { WinningLotto(winningNumbers, 6) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
