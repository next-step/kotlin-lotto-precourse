package lotto.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class WinningLottoTest : BehaviorSpec({

    Given("유효한 당첨 번호와 보너스 번호가 있을 때") {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 7

        When("WinningLotto를 생성하면") {
            val winningLotto = WinningLotto(numbers, bonusNumber)

            Then("정상적으로 생성된다") {
                winningLotto.bonusNumber shouldBe 7
            }
        }
    }

    Given("당첨 번호가 유효하지 않을 때") {
        When("번호가 6개가 아니면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5), 7)
                }
                ex.message shouldContain "[ERROR]"
            }
        }

        When("번호에 중복이 있으면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(listOf(1, 1, 2, 3, 4, 5), 7)
                }
                ex.message shouldContain "[ERROR]"
            }
        }

        When("번호가 범위를 벗어나면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(listOf(1, 2, 3, 4, 5, 46), 7)
                }
                ex.message shouldContain "[ERROR]"
            }
        }
    }

    Given("보너스 번호가 유효하지 않을 때") {
        val numbers = listOf(1, 2, 3, 4, 5, 6)

        When("보너스 번호가 1 미만이면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(numbers, 0)
                }
                ex.message shouldContain "[ERROR]"
            }
        }

        When("보너스 번호가 45 초과이면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(numbers, 46)
                }
                ex.message shouldContain "[ERROR]"
            }
        }

        When("보너스 번호가 당첨 번호와 중복되면") {
            Then("예외가 발생한다") {
                val ex = shouldThrow<IllegalArgumentException> {
                    WinningLotto(numbers, 3)
                }
                ex.message shouldContain "[ERROR]"
            }
        }
    }

    Given("로또 번호와 당첨 번호를 비교할 때") {
        val winningLotto = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)

        When("6개 모두 일치하면") {
            val lotto = Lotto(listOf(1, 2, 3, 4, 5, 6))
            Then("1등을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.FIRST
            }
        }

        When("5개 일치하고 보너스 번호도 일치하면") {
            val lotto = Lotto(listOf(1, 2, 3, 4, 5, 7))
            Then("2등을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.SECOND
            }
        }

        When("5개 일치하고 보너스 번호가 일치하지 않으면") {
            val lotto = Lotto(listOf(1, 2, 3, 4, 5, 8))
            Then("3등을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.THIRD
            }
        }

        When("4개 일치하면") {
            val lotto = Lotto(listOf(1, 2, 3, 4, 10, 11))
            Then("4등을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.FOURTH
            }
        }

        When("3개 일치하면") {
            val lotto = Lotto(listOf(1, 2, 3, 10, 11, 12))
            Then("5등을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.FIFTH
            }
        }

        When("2개 이하 일치하면") {
            val lotto = Lotto(listOf(1, 2, 10, 11, 12, 13))
            Then("낙첨을 반환한다") {
                winningLotto.match(lotto) shouldBe LottoRank.NONE
            }
        }
    }
})
