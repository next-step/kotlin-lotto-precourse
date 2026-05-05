package lotto.view

import lotto.domain.Lotto
import lotto.domain.PurchaseAmount
import lotto.domain.WinningNumbers

class InputView {
    fun readPurchaseAmount(): PurchaseAmount {
        while (true) {
            try {
                println("구입금액을 입력해 주세요.")
                val input = readln().toIntOrNull()
                    ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
                return PurchaseAmount(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readLotto("\n당첨 번호를 입력해 주세요.")
        val bonusNumber = readBonusNumber(winningLotto)
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun readLotto(message: String): Lotto {
        while (true) {
            try {
                println(message)
                val input = readln()
                val numbers = input.split(",")
                    .map { it.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_NUMBER) }
                return Lotto(numbers)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun readBonusNumber(winningLotto: Lotto): Int {
        while (true) {
            try {
                println("\n보너스 번호를 입력해 주세요.")
                val input = readln().toIntOrNull()
                    ?: throw IllegalArgumentException(ERROR_NOT_NUMBER)
                WinningNumbers(winningLotto, input)
                return input
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    companion object {
        private const val ERROR_NOT_NUMBER = "[ERROR] 숫자가 아닙니다."
    }
}