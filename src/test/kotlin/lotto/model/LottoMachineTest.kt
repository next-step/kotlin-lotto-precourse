package lotto.model

import lotto.util.NumberGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    @DisplayName("요청한 개수만큼 로또를 발행한다")
    fun `issues the requested number of lottos`() {
        val machine = LottoMachine(FixedNumberGenerator(listOf(1, 2, 3, 4, 5, 6)))
        val lottos = machine.issue(3)
        assertThat(lottos).hasSize(3)
    }

    @Test
    @DisplayName("발행된 각 로또는 생성기가 만든 번호로 구성된다")
    fun `each issued lotto contains numbers from the generator`() {
        val expectedNumbers = listOf(1, 2, 3, 4, 5, 6).map { LottoNumber(it) }
        val machine = LottoMachine(FixedNumberGenerator(listOf(1, 2, 3, 4, 5, 6)))
        val lottos = machine.issue(1)
        assertThat(lottos[0].numbers).isEqualTo(expectedNumbers)
    }

    private class FixedNumberGenerator(values: List<Int>) : NumberGenerator {
        private val numbers = values.map { LottoNumber(it) }
        override fun generate(): List<LottoNumber> = numbers
    }
}
