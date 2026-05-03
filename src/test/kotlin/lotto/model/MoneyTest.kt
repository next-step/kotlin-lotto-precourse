package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    @DisplayName("구입 금액으로 구매 가능한 로또 개수를 계산한다")
    fun `calculates purchasable lotto count`() {
        val money = Money(8_000)
        assertThat(money.lottoCount()).isEqualTo(8)
    }

    @Test
    @DisplayName("구입 금액이 0 이하이면 예외가 발생한다")
    fun `throws exception when amount is zero or negative`() {
        assertThatThrownBy { Money(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }

    @Test
    @DisplayName("구입 금액이 1,000원 단위가 아니면 예외가 발생한다")
    fun `throws exception when amount is not multiple of lotto price`() {
        assertThatThrownBy { Money(1_500) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }
}
