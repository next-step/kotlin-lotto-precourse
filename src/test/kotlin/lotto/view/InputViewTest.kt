package lotto.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.InputStream

class InputViewTest {

    private val originalIn: InputStream = System.`in`

    @AfterEach
    fun tearDown() {
        System.setIn(originalIn)
    }

    @Test
    @DisplayName("구입 금액을 입력받아 Money 객체로 반환한다")
    fun `reads money`() {
        System.setIn("8000".byteInputStream())
        val money = InputView().readMoney()
        assertThat(money.amount).isEqualTo(8_000)
    }

    @Test
    @DisplayName("당첨 번호를 쉼표로 구분해 입력받아 Lotto 객체로 반환한다")
    fun `reads winning lotto`() {
        System.setIn("1,2,3,4,5,6".byteInputStream())
        val lotto = InputView().readWinningLotto()
        assertThat(lotto.numbers.map { it.value }).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    @DisplayName("잘못된 입력이 들어오면 다시 입력받는다")
    fun `retries on invalid input`() {
        System.setIn("abc\n8000\n".byteInputStream())
        val money = InputView().readMoney()
        assertThat(money.amount).isEqualTo(8_000)
    }
}
