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


})
