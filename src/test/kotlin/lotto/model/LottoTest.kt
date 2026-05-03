package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoTest {

    @Test
    @DisplayName("6개의 중복되지 않는 번호로 로또를 생성할 수 있다")
    fun `creates lotto with six unique numbers`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }
        val lotto = Lotto(numbers)
        assertThat(lotto.numbers).hasSize(6)
    }

    @Test
    @DisplayName("로또 번호 개수가 6개보다 적으면 예외가 발생한다")
    fun `throws exception when size is less than six`() {
        val numbers = listOf(1, 2, 3, 4, 5).map { LottoNumber(it) }
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }

    @Test
    @DisplayName("로또 번호 개수가 6개보다 많으면 예외가 발생한다")
    fun `throws exception when size is more than six`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7).map { LottoNumber(it) }
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }

    @Test
    @DisplayName("로또 번호에 중복이 있으면 예외가 발생한다")
    fun `throws exception when numbers contain duplicates`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5).map { LottoNumber(it) }
        assertThatThrownBy { Lotto(numbers) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }
}
