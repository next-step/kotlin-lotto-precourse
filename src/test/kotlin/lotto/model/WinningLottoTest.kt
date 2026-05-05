package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WinningLottoTest {

    private fun lottoOf(vararg values: Int): Lotto =
        Lotto(values.map { LottoNumber(it) })

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    fun `throws exception when bonus number duplicates winning numbers`() {
        val winning = lottoOf(1, 2, 3, 4, 5, 6)
        val bonus = LottoNumber(6)
        assertThatThrownBy { WinningLotto(winning, bonus) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("[ERROR]")
    }

    @Test
    @DisplayName("일치하는 당첨 번호 개수를 정확히 계산한다")
    fun `counts matched numbers correctly`() {
        val winning = WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), LottoNumber(7))
        val target = lottoOf(1, 2, 3, 10, 11, 12)
        val result = winning.match(target)
        assertThat(result.matchCount).isEqualTo(3)
    }

    @Test
    @DisplayName("타겟 로또에 보너스 번호가 있으면 bonusMatched가 true이다")
    fun `detects bonus match`() {
        val winning = WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), LottoNumber(7))
        val target = lottoOf(1, 2, 3, 4, 5, 7)
        val result = winning.match(target)
        assertThat(result.bonusMatched).isTrue()
    }

    @Test
    @DisplayName("타겟 로또에 보너스 번호가 없으면 bonusMatched가 false이다")
    fun `detects no bonus match`() {
        val winning = WinningLotto(lottoOf(1, 2, 3, 4, 5, 6), LottoNumber(7))
        val target = lottoOf(1, 2, 3, 4, 5, 8)
        val result = winning.match(target)
        assertThat(result.bonusMatched).isFalse()
    }
}
