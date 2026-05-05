package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.util.NumberGenerator
import lotto.view.InputView
import lotto.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream

class LottoControllerTest {

    private val originalIn: InputStream = System.`in`
    private val originalOut: PrintStream = System.out
    private val captured = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(captured))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(originalIn)
        System.setOut(originalOut)
    }

    @Test
    @DisplayName("전체 흐름 통합 테스트: 8,000원 구매 + 모두 [1..6] → 1등 당첨")
    fun `full flow ends with first place when all lottos match`() {
        System.setIn("8000\n1,2,3,4,5,6\n7\n".byteInputStream())
        val controller = LottoController(
            inputView = InputView(),
            outputView = OutputView(),
            machine = LottoMachine(FixedGenerator(listOf(1, 2, 3, 4, 5, 6))),
        )

        controller.run()

        val output = captured.toString()
        assertThat(output).contains("8개를 구매했습니다.")
        assertThat(output).contains("6개 일치 (2,000,000,000원) - 8개")
    }

    private class FixedGenerator(values: List<Int>) : NumberGenerator {
        private val numbers = values.map { LottoNumber(it) }
        override fun generate(): List<LottoNumber> = numbers
    }
}
