package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoNumberTest {

    @Test
    @DisplayName("1부터 45 사이의 숫자로 로또 번호를 생성할 수 있다")
    fun `creates lotto number with valid value`() {
        val lottoNumber = LottoNumber(1)
        assertThat(lottoNumber.value).isEqualTo(1)
    }

    @Test
    @DisplayName("로또 번호가 1보다 작으면 예외가 발생한다")
    fun `throws exception when value is below minimum`() {
        assertThatThrownBy { LottoNumber(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }

    @Test
    @DisplayName("로또 번호가 45보다 크면 예외가 발생한다")
    fun `throws exception when value is above maximum`() {
        assertThatThrownBy { LottoNumber(46) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }
}
