package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PurchaseAmountTest {
    @Test
    fun `1000원으로 구매 금액을 생성한다`() {
        // given
        val amount = 1000

        // when & then
        assertDoesNotThrow {
            PurchaseAmount(amount)
        }
    }

    @Test
    fun `1000원 단위로 구매 금액을 생성한다`() {
        // given
        val amount = 5000

        // when
        val purchaseAmount = PurchaseAmount(amount)

        // then
        assertThat(purchaseAmount.amount).isEqualTo(5000)
    }

    @Test
    fun `구매 금액이 0원이면 예외가 발생한다`() {
        // given
        val amount = 0

        // when & then
        assertThatThrownBy { PurchaseAmount(amount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `구매 금액이 음수이면 예외가 발생한다`() {
        // given
        val amount = -1000

        // when & then
        assertThatThrownBy { PurchaseAmount(amount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `구매 금액이 1000원 단위가 아니면 예외가 발생한다`() {
        // given
        val amount = 1500

        // when & then
        assertThatThrownBy { PurchaseAmount(amount) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("[ERROR]")
    }

    @Test
    fun `구매 가능한 로또 개수를 반환한다`() {
        // given
        val purchaseAmount = PurchaseAmount(5000)

        // when
        val count = purchaseAmount.getLottoPurchaseCount()

        // then
        assertThat(count).isEqualTo(5)
    }

    @Test
    fun `1000원으로 1개의 로또를 구매할 수 있다`() {
        // given
        val purchaseAmount = PurchaseAmount(1000)

        // when
        val count = purchaseAmount.getLottoPurchaseCount()

        // then
        assertThat(count).isEqualTo(1)
    }
}
