package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PurchaseAmountTest {
    @Test
    fun `구입 금액이 1000원 단위가 아니면 예외가 발생한다`() {
        assertThatThrownBy { PurchaseAmount.from("1500") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `구입 금액이 0이하이면 예외가 발생한다`() {
        assertThatThrownBy { PurchaseAmount.from("0") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `숫자가 아니면 예외가 발생한다`() {
        assertThatThrownBy { PurchaseAmount.from("abc") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `로또 매수를 정확히 계산한다`() {
        assertThat(PurchaseAmount.from("8000").lottoCount()).isEqualTo(8)
    }
}
