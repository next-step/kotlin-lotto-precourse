package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PurchaseAmountTest {

    @Test
    fun `1000원 단위가 아니면 예외가 발생한다`() {
        assertThatThrownBy { PurchaseAmount(1500) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `0원 이하면 예외가 발생한다`() {
        assertThatThrownBy { PurchaseAmount(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `구입 금액에 맞는 로또 발행 수를 반환한다`() {
        assertThat(PurchaseAmount(5000).lottoCount()).isEqualTo(5)
    }
}
