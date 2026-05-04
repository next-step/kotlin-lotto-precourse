package lotto.view

import lotto.domain.LOTTO_MAX_NUMBER
import lotto.domain.LOTTO_MIN_NUMBER
import lotto.domain.LOTTO_PRICE
import lotto.domain.Lotto

object InputHandler {

    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        while (true) {
            val input = readlnOrNull() ?: continue
            val amount = input.toIntOrNull()
            when {
                amount == null -> println("[ERROR] 구입 금액은 숫자여야 합니다.")
                amount <= 0 -> println("[ERROR] 구입 금액은 양수여야 합니다.")
                amount % LOTTO_PRICE != 0 -> println("[ERROR] 구입 금액은 ${LOTTO_PRICE}원 단위여야 합니다.")
                else -> return amount
            }
        }
    }

    fun readWinningNumbers(): List<Int> {
        println("\n당첨 번호를 입력해 주세요.")
        while (true) {
            val input = readlnOrNull() ?: continue
            val numbers = input.split(",").map { it.trim().toIntOrNull() }
            if (numbers.any { it == null }) {
                println("[ERROR] 당첨 번호는 숫자여야 합니다.")
                continue
            }
            val validNumbers = numbers.filterNotNull()
            runCatching { Lotto(validNumbers) }
                .onSuccess { return validNumbers }
                .onFailure { println(it.message) }
        }
    }

    fun readBonusNumber(winningNumbers: List<Int>): Int {
        println("\n보너스 번호를 입력해 주세요.")
        while (true) {
            val input = readlnOrNull() ?: continue
            when (val number = input.toIntOrNull()) {
                null -> println("[ERROR] 보너스 번호는 숫자여야 합니다.")
                !in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER -> println("[ERROR] 보너스 번호는 ${LOTTO_MIN_NUMBER}부터 ${LOTTO_MAX_NUMBER} 사이의 숫자여야 합니다.")
                in winningNumbers -> println("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.")
                else -> return number
            }
        }
    }
}
