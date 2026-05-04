package lotto.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeExactly
import io.kotest.matchers.shouldBe

class LottoResultTest : StringSpec({
    "당첨 통계를 올바르게 계산한다" {
        val lottos = listOf(
            Lotto(listOf(1, 2, 3, 4, 5, 6)),
            Lotto(listOf(1, 2, 3, 4, 5, 7)),
            Lotto(listOf(1, 2, 3, 4, 8, 9)),
        )
        val result = LottoResult(lottos, listOf(1, 2, 3, 4, 5, 6), 7, 3000)

        result.countByRank(LottoRank.FIRST) shouldBe 1
        result.countByRank(LottoRank.SECOND) shouldBe 1
        result.countByRank(LottoRank.FOURTH) shouldBe 1
    }

    "수익률을 올바르게 계산한다" {
        val lottos = listOf(Lotto(listOf(1, 2, 3, 10, 20, 30)))
        val result = LottoResult(lottos, listOf(1, 2, 3, 4, 5, 6), 7, 1000)

        result.profitRate() shouldBeExactly 500.0
    }

    "전체 낙첨이면 수익률은 0이다" {
        val lottos = listOf(
            Lotto(listOf(10, 20, 30, 40, 41, 42)),
            Lotto(listOf(11, 21, 31, 39, 40, 42)),
        )
        val result = LottoResult(lottos, listOf(1, 2, 3, 4, 5, 6), 7, 2000)

        result.profitRate() shouldBeExactly 0.0
        LottoRank.entries.filter { it != LottoRank.NONE }
            .forEach { result.countByRank(it) shouldBe 0 }
    }

    "1등 당첨 시 수익률을 올바르게 계산한다" {
        val lottos = listOf(Lotto(listOf(1, 2, 3, 4, 5, 6)))
        val result = LottoResult(lottos, listOf(1, 2, 3, 4, 5, 6), 7, 1000)

        result.countByRank(LottoRank.FIRST) shouldBe 1
        result.profitRate() shouldBeExactly 200_000_000.0
    }
})
