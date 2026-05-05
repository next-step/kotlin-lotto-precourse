package lotto.view

import lotto.domain.Lotto
import lotto.domain.Lottos
import lotto.domain.PurchaseAmount
import lotto.domain.WinningResult
import lotto.domain.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutputViewTest {
    private val outputView = OutputView()
    private val outputStream = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUpStreams() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun restoreStreams() {
        System.setOut(originalOut)
    }

    @Test
    fun `구매 개수를 출력한다`() {
        // given
        val count = 8

        // when
        outputView.printPurchaseCount(count)

        // then
        assertThat(outputStream.toString()).contains("8개를 구매했습니다.")
    }

    @Test
    fun `로또 번호들을 출력한다`() {
        // given
        val lottos = Lottos(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                Lotto(listOf(7, 8, 9, 10, 11, 12))
            )
        )

        // when
        outputView.printLottos(lottos)

        // then
        val output = outputStream.toString()
        assertThat(output).contains("[1, 2, 3, 4, 5, 6]")
        assertThat(output).contains("[7, 8, 9, 10, 11, 12]")
    }

    @Test
    fun `당첨 통계를 출력한다`() {
        // given
        val ranks = listOf(Rank.FIFTH, Rank.NONE, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(3000)

        // when
        outputView.printWinningStatistics(winningResult, purchaseAmount)

        // then
        val output = outputStream.toString()
        assertThat(output).contains("당첨 통계")
        assertThat(output).contains("---")
        assertThat(output).contains("3개 일치 (5,000원) - 1개")
        assertThat(output).contains("4개 일치 (50,000원) - 0개")
        assertThat(output).contains("5개 일치 (1,500,000원) - 0개")
        assertThat(output).contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 0개")
        assertThat(output).contains("6개 일치 (2,000,000,000원) - 0개")
        assertThat(output).contains("총 수익률은 166.67%입니다.")
    }

    @Test
    fun `수익률을 출력한다`() {
        // given
        val ranks = listOf(Rank.NONE, Rank.NONE)
        val winningResult = WinningResult(ranks)
        val purchaseAmount = PurchaseAmount(2000)

        // when
        outputView.printWinningStatistics(winningResult, purchaseAmount)

        // then
        assertThat(outputStream.toString()).contains("총 수익률은 0.0%입니다.")
    }
}
