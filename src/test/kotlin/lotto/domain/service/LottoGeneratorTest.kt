package lotto.domain.service

import lotto.domain.PurchaseAmount
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

    @Test
    fun `구매 금액에 따라 로또를 생성한다`() {
        // given
        val generator = LottoGenerator()
        val purchaseAmount = PurchaseAmount(5000)

        // when
        val lottos = generator.generateLottos(purchaseAmount)

        // then
        assertThat(lottos.lottos).hasSize(5)
    }

    @Test
    fun `생성된 모든 로또는 유효한 번호를 가진다`() {
        // given
        val generator = LottoGenerator()
        val purchaseAmount = PurchaseAmount(3000)

        // when
        val lottos = generator.generateLottos(purchaseAmount)

        // then
        lottos.lottos.forEach { lotto ->
            assertThat(lotto.numbers).hasSize(6)
            assertThat(lotto.numbers).allMatch { it in 1..45 }
            assertThat(lotto.numbers).doesNotHaveDuplicates()
        }
    }

    @Test
    fun `1000원으로 1개의 로또를 생성한다`() {
        // given
        val generator = LottoGenerator()
        val purchaseAmount = PurchaseAmount(1000)

        // when
        val lottos = generator.generateLottos(purchaseAmount)

        // then
        assertThat(lottos.lottos).hasSize(1)
    }
}
