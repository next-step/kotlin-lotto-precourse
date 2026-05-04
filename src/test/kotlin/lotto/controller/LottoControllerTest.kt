package lotto.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import lotto.domain.LottoMachine
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LottoControllerTest : BehaviorSpec({
    val standardOut = System.out
    val outputStream = ByteArrayOutputStream()

    beforeTest {
        System.setOut(PrintStream(outputStream))
    }

    afterTest {
        System.setOut(standardOut)
        outputStream.reset()
    }

    Given("로또 게임을 시작할 때") {
        val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 6) }
        val controller = LottoController(machine)

        When("정상적인 금액과 번호를 입력하면") {
            val input = "2000\n1,2,3,4,5,6\n7\n"
            System.setIn(ByteArrayInputStream(input.toByteArray()))

            controller.run()

            Then("구매 결과와 당첨 통계가 출력되어야 한다") {
                val output = outputStream.toString()
                output shouldContain "2개를 구매했습니다."
                output shouldContain "6개 일치 (2,000,000,000원) - 2개"
                output shouldContain "총 수익률은 200000000.0%입니다."
            }
        }

        When("잘못된 값을 입력했다가 정상적인 값을 입력하면") {
            // 1. 구입 금액 잘못 입력 (abc) -> 1000 입력
            // 2. 당첨 번호 잘못 입력 (1,2,3) -> 1,2,3,4,5,6 입력
            // 3. 보너스 번호 잘못 입력 (6, 당첨 번호와 중복) -> 7 입력
            val input = "abc\n1000\n1,2,3\n1,2,3,4,5,6\n6\n7\n"
            System.setIn(ByteArrayInputStream(input.toByteArray()))

            controller.run()

            Then("에러 메시지를 출력하고 최종 결과를 보여준다") {
                val output = outputStream.toString()
                output shouldContain "[ERROR] 구입 금액은 숫자여야 합니다."
                output shouldContain "[ERROR] 로또 번호는 6개여야 합니다."
                output shouldContain "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."
                output shouldContain "1개를 구매했습니다."
                output shouldContain "6개 일치 (2,000,000,000원) - 1개"
            }
        }
    }
})
