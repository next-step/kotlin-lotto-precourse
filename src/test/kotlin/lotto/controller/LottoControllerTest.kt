package lotto.controller

import lotto.util.LottoNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LottoControllerTest {
    @Test
    fun `정상 시나리오 - 8000원 입력시 8개 발행 후 통계가 출력된다`() {
        val inputs = ArrayDeque(listOf("8000", "1,2,3,4,5,6", "7"))
        val fakeInput = object : InputView() {
            override fun readPurchaseAmount() = inputs.removeFirst()
            override fun readWinningNumbers() = inputs.removeFirst()
            override fun readBonusNumber() = inputs.removeFirst()
        }
        val fixed = FixedGenerator(listOf(1, 2, 3, 4, 5, 6))
        val output = ByteArrayOutputStream()
        val original = System.out
        System.setOut(PrintStream(output))
        try {
            LottoController(fakeInput, OutputView(), fixed).run()
        } finally {
            System.setOut(original)
        }
        val printed = output.toString()
        assertThat(printed).contains("8개를 구매했습니다.")
        assertThat(printed).contains("당첨 통계")
        assertThat(printed).contains("총 수익률")
    }

    private class FixedGenerator(private val numbers: List<Int>) : LottoNumberGenerator {
        override fun generate(): List<Int> = numbers
    }
}
