package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.RepeatedTest

class LottoTest {

    @RepeatedTest(100)
    fun `자동 생성된 로또는 1~45 범위의 중복 없는 6개 번호를 가진다`() {
        val lotto = Lotto.generate()
        val numbers = lotto.getNumbers()

        assertThat(numbers).hasSize(6)
        assertThat(numbers).doesNotHaveDuplicates()
        assertThat(numbers).allMatch { it in 1..45 }
    }
}
