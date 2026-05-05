package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.LottoResult
import lotto.model.Money
import lotto.model.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutputViewTest {

    private val originalOut: PrintStream = System.out
    private val captured = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(captured))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    @DisplayName("발행된 로또 개수와 번호를 오름차순으로 출력한다")
    fun `prints purchased lottos`() {
        val lotto = Lotto(listOf(8, 21, 23, 41, 42, 43).map { LottoNumber(it) })
        OutputView().printLottos(listOf(lotto))
        val output = captured.toString()
        assertThat(output).contains("1개를 구매했습니다.")
        assertThat(output).contains("[8, 21, 23, 41, 42, 43]")
    }

    @Test
    @DisplayName("당첨 통계와 수익률을 출력한다")
    fun `prints result matching example`() {
        val ranks = List(7) { Rank.NONE } + Rank.FIFTH
        OutputView().printResult(LottoResult(ranks), Money(8_000))
        val output = captured.toString()
        assertThat(output).contains("3개 일치 (5,000원) - 1개")
        assertThat(output).contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 0개")
        assertThat(output).contains("총 수익률은 62.5%입니다.")
    }
}
