package lotto.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class LottoMachineTest : StringSpec({
    "지정한 개수만큼 로또를 생성한다" {
        val machine = LottoMachine()
        machine.generate(5) shouldHaveSize 5
    }

    "고정 번호 생성기를 주입하면 결정적 결과를 반환한다" {
        val machine = LottoMachine(numberGenerator = { listOf(1, 2, 3, 4, 5, 6) })
        val lottos = machine.generate(3)

        lottos shouldHaveSize 3
        lottos.forEach { it.toString() shouldBe "[1, 2, 3, 4, 5, 6]" }
    }

    "생성된 로또 번호는 1부터 45 사이이다" {
        val machine = LottoMachine()
        val lottos = machine.generate(10)

        lottos.forEach { lotto ->
            lotto.countMatch((LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).toList())
                .shouldBeInRange(LOTTO_NUMBER_COUNT..LOTTO_NUMBER_COUNT)
        }
    }
})
