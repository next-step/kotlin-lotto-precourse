package lotto.controller

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldContain
import lotto.domain.LottoMachine
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LottoControllerTest : StringSpec({
    val standardOut = System.out
    val outputStream = ByteArrayOutputStream()

    beforeTest {
        System.setOut(PrintStream(outputStream))
    }

    afterTest {
        System.setOut(standardOut)
        outputStream.reset()
    }

    "로또 게임의 전체 프로세스를 실행한다" {
        // given
        val input = "2000\n1,2,3,4,5,6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 6) }
        val controller = LottoController(machine)

        // when
        controller.run()

        // then
        val output = outputStream.toString()
        output shouldContain "2개를 구매했습니다."
        output shouldContain "6개 일치 (2,000,000,000원) - 2개"
        output shouldContain "총 수익률은 200000000.0%입니다."
    }

    "입력값이 유효하지 않은 경우 에러 메시지를 출력하고 재입력을 받는다" {
        // given
        // 1. 구입 금액 잘못 입력 (abc) -> 1000 입력
        // 2. 당첨 번호 잘못 입력 (1,2,3) -> 1,2,3,4,5,6 입력
        // 3. 보너스 번호 잘못 입력 (6, 당첨 번호와 중복) -> 7 입력
        val input = "abc\n1000\n1,2,3\n1,2,3,4,5,6\n6\n7\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 6) }
        val controller = LottoController(machine)

        // when
        controller.run()

        // then
        val output = outputStream.toString()
        output shouldContain "[ERROR] 구입 금액은 숫자여야 합니다."
        output shouldContain "[ERROR] 로또 번호는 6개여야 합니다."
        output shouldContain "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
        output shouldContain "1개를 구매했습니다."
        output shouldContain "6개 일치 (2,000,000,000원) - 1개"
    }
})
