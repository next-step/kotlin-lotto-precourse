package lotto.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputViewTest {
    private val inputView = InputView()
    private val outputStream = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalIn = System.`in`

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setIn(originalIn)
    }

    @Test
    fun `정상적인 구매 금액을 입력받는다`() {
        // given
        val input = "5000\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val purchaseAmount = inputView.readPurchaseAmount()

        // then
        assertThat(purchaseAmount.amount).isEqualTo(5000)
    }

    @Test
    fun `잘못된 구매 금액 입력 후 재입력한다`() {
        // given
        val input = "abc\n5000\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val purchaseAmount = inputView.readPurchaseAmount()

        // then
        assertThat(purchaseAmount.amount).isEqualTo(5000)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }

    @Test
    fun `1000원 단위가 아닌 금액 입력 후 재입력한다`() {
        // given
        val input = "1500\n2000\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val purchaseAmount = inputView.readPurchaseAmount()

        // then
        assertThat(purchaseAmount.amount).isEqualTo(2000)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }

    @Test
    fun `정상적인 당첨 번호와 보너스 번호를 입력받는다`() {
        // given
        val input = "1,2,3,4,5,6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val winningNumbers = inputView.readWinningNumbers()

        // then
        assertThat(winningNumbers.numbers.numbers).containsExactly(1, 2, 3, 4, 5, 6)
        assertThat(winningNumbers.bonusNumber).isEqualTo(7)
    }

    @Test
    fun `잘못된 당첨 번호 입력 후 재입력한다`() {
        // given
        val input = "1,2,3,4,5\n1,2,3,4,5,6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val winningNumbers = inputView.readWinningNumbers()

        // then
        assertThat(winningNumbers.numbers.numbers).containsExactly(1, 2, 3, 4, 5, 6)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }

    @Test
    fun `중복된 당첨 번호 입력 후 재입력한다`() {
        // given
        val input = "1,1,2,3,4,5\n1,2,3,4,5,6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val winningNumbers = inputView.readWinningNumbers()

        // then
        assertThat(winningNumbers.numbers.numbers).containsExactly(1, 2, 3, 4, 5, 6)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 재입력한다`() {
        // given
        val input = "1,2,3,4,5,6\n6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val winningNumbers = inputView.readWinningNumbers()

        // then
        assertThat(winningNumbers.bonusNumber).isEqualTo(7)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }

    @Test
    fun `보너스 번호가 범위를 벗어나면 재입력한다`() {
        // given
        val input = "1,2,3,4,5,6\n46\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // when
        val winningNumbers = inputView.readWinningNumbers()

        // then
        assertThat(winningNumbers.bonusNumber).isEqualTo(7)
        assertThat(outputStream.toString()).contains("[ERROR]")
    }
}
