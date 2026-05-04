package lotto.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class LottoRankTest : StringSpec({
    "6개 일치하면 1등이다" {
        LottoRank.of(6, false) shouldBe LottoRank.FIRST
    }

    "5개 일치하고 보너스 번호 일치하면 2등이다" {
        LottoRank.of(5, true) shouldBe LottoRank.SECOND
    }

    "5개 일치하면 3등이다" {
        LottoRank.of(5, false) shouldBe LottoRank.THIRD
    }

    "4개 일치하면 4등이다" {
        LottoRank.of(4, false) shouldBe LottoRank.FOURTH
    }

    "3개 일치하면 5등이다" {
        LottoRank.of(3, false) shouldBe LottoRank.FIFTH
    }

    "2개 이하 일치하면 낙첨이다" {
        LottoRank.of(2, false) shouldBe LottoRank.NONE
        LottoRank.of(0, false) shouldBe LottoRank.NONE
    }
})