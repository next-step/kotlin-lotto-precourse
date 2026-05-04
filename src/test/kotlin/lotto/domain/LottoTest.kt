package lotto.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class LottoTest : StringSpec({
    "6개 번호로 로또를 생성한다" {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        lotto.toString() shouldBe "[1, 2, 3, 4, 5, 6]"
    }

    "번호가 6개가 아니면 예외가 발생한다" {
        val ex = shouldThrow<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5)) }
        ex.message shouldContain "[ERROR]"
    }

    "번호에 중복이 있으면 예외가 발생한다" {
        val ex = shouldThrow<IllegalArgumentException> { Lotto(listOf(1, 1, 2, 3, 4, 5)) }
        ex.message shouldContain "[ERROR]"
    }

    "번호가 1 미만이면 예외가 발생한다" {
        val ex = shouldThrow<IllegalArgumentException> { Lotto(listOf(0, 1, 2, 3, 4, 5)) }
        ex.message shouldContain "[ERROR]"
    }

    "번호가 45 초과이면 예외가 발생한다" {
        val ex = shouldThrow<IllegalArgumentException> { Lotto(listOf(1, 2, 3, 4, 5, 46)) }
        ex.message shouldContain "[ERROR]"
    }

    "당첨 번호와 일치하는 개수를 반환한다" {
        val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
        lotto.countMatch(listOf(1, 2, 3, 7, 8, 9)) shouldBe 3
    }

    "번호가 정렬된 순서로 저장된다" {
        val lotto = Lotto(listOf(6, 5, 4, 3, 2, 1))
        lotto.toString() shouldBe "[1, 2, 3, 4, 5, 6]"
    }
})