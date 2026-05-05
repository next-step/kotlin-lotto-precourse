package lotto.util

import lotto.model.Lotto
import lotto.model.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RandomNumberGeneratorTest {

    @Test
    @DisplayName("1부터 45 사이의 중복되지 않는 6개 번호를 생성한다")
    fun `generates six unique numbers within valid range`() {
        val generator = RandomNumberGenerator()
        val numbers = generator.generate()
        assertThat(numbers).hasSize(Lotto.SIZE)
        assertThat(numbers.toSet()).hasSize(Lotto.SIZE)
        assertThat(numbers).allMatch { it.value in LottoNumber.MIN..LottoNumber.MAX }
    }
}
