package lotto.view

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputHandlerTest : StringSpec({
    val standardOut = System.out
    val outputStream = ByteArrayOutputStream()

    beforeTest {
        System.setOut(PrintStream(outputStream))
    }

    afterTest {
        System.setOut(standardOut)
        outputStream.reset()
    }

    "유효한 구입 금액을 입력하면 해당 금액을 반환한다" {
        val input = "8000\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readPurchaseAmount()

        result shouldBe 8000
    }

    "잘못된 입력(숫자 아님, 음수, 단위 불일치) 후 유효한 입력을 하면 최종적으로 유효한 값을 반환한다" {
        val input = "abc\n-1000\n1500\n2000\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readPurchaseAmount()

        result shouldBe 2000
        outputStream.toString() shouldBe """구입금액을 입력해 주세요.
[ERROR] 구입 금액은 숫자여야 합니다.
[ERROR] 구입 금액은 양수여야 합니다.
[ERROR] 구입 금액은 1000원 단위여야 합니다.
"""
    }

    "유효한 당첨 번호를 입력하면 리스트로 반환한다" {
        val input = "1,2,3,4,5,6\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readWinningNumbers()

        result shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    "당첨 번호 입력 시 잘못된 입력 후 유효한 입력을 하면 최종적으로 유효한 값을 반환한다" {
        val input = "1,2,3,a,5,6\n1,1,2,3,4,5\n1,2,3,4,5,46\n1,2,3,4,5,6\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readWinningNumbers()

        result shouldBe listOf(1, 2, 3, 4, 5, 6)
        outputStream.toString().contains("[ERROR] 당첨 번호는 숫자여야 합니다.") shouldBe true
    }

    "유효한 보너스 번호를 입력하면 해당 번호를 반환한다" {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val input = "7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readBonusNumber(winningNumbers)

        result shouldBe 7
    }

    "보너스 번호 입력 시 잘못된 입력 후 유효한 입력을 하면 최종적으로 유효한 값을 반환한다" {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 6)
        val input = "abc\n46\n3\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val result = InputHandler.readBonusNumber(winningNumbers)

        result shouldBe 7
        val output = outputStream.toString()
        output.contains("[ERROR] 보너스 번호는 숫자여야 합니다.") shouldBe true
        output.contains("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.") shouldBe true
        output.contains("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.") shouldBe true
    }
})
