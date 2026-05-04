package lotto.view

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import lotto.domain.Lotto
import lotto.domain.LottoResult
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutputHandlerTest : StringSpec({
    val standardOut = System.out
    val outputStream = ByteArrayOutputStream()

    beforeTest {
        System.setOut(PrintStream(outputStream))
    }

    afterTest {
        System.setOut(standardOut)
        outputStream.reset()
    }

    "구매한 로또 목록을 출력한다" {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(7, 8, 9, 10, 11, 12))
        )

        OutputHandler.printLottos(lottos)

        val output = outputStream.toString().replace("\r\n", "\n")
        output shouldBe """
2개를 구매했습니다.
[1, 2, 3, 4, 5, 6]
[7, 8, 9, 10, 11, 12]
"""
    }

    "당첨 통계 결과를 출력한다" {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)), // 1등
            Lotto(listOf(1, 2, 3, 4, 5, 7)), // 2등
            Lotto(listOf(1, 2, 3, 4, 10, 11)) // 4등
        )
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7
        val purchaseAmount = 3000
        val result = LottoResult(lottos, winningNumbers, bonusNumber, purchaseAmount)

        OutputHandler.printResult(result)

        val output = outputStream.toString().replace("\r\n", "\n")
        output.contains("당첨 통계") shouldBe true
        output.contains("---") shouldBe true
        output.contains("3개 일치 (5,000원) - 0개") shouldBe true
        output.contains("4개 일치 (50,000원) - 1개") shouldBe true
        output.contains("5개 일치 (1,500,000원) - 0개") shouldBe true
        output.contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 1개") shouldBe true
        output.contains("6개 일치 (2,000,000,000원) - 1개") shouldBe true
        // 수익률: (2,000,000,000 + 30,000,000 + 50,000) / 3000 * 100 = 203,005,000 / 3 * 10 = 67668333.3...
        // %.1f format will round it
        val expectedProfitRate = (2_000_000_000 + 30_000_000 + 50_000).toDouble() / 3000 * 100
        output.contains("총 수익률은 ${"%.1f".format(expectedProfitRate)}%입니다.") shouldBe true
    }
})
