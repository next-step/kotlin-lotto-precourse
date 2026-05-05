package lotto.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `로또 번호를 생성한다`() {
        // given
        val generator = LottoGenerator()

        // when
        val lotto = generator.generate()

        // then
        assertThat(lotto.numbers).hasSize(6)
    }

    @Test
    fun `생성된 로또 번호는 1부터 45 사이의 숫자다`() {
        // given
        val generator = LottoGenerator()

        // when
        val lotto = generator.generate()

        // then
        assertThat(lotto.numbers).allMatch { it in 1..45 }
    }

    @Test
    fun `생성된 로또 번호는 중복되지 않는다`() {
        // given
        val generator = LottoGenerator()

        // when
        val lotto = generator.generate()

        // then
        assertThat(lotto.numbers).doesNotHaveDuplicates()
    }

    @Test
    fun `로또 번호를 여러 번 생성할 수 있다`() {
        // given
        val generator = LottoGenerator()

        // when
        val lotto1 = generator.generate()
        val lotto2 = generator.generate()
        val lotto3 = generator.generate()

        // then
        assertThat(lotto1.numbers).hasSize(6)
        assertThat(lotto2.numbers).hasSize(6)
        assertThat(lotto3.numbers).hasSize(6)
    }
}
