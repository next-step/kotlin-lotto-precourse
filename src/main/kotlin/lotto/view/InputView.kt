package lotto.view

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Money

class InputView {

    fun readMoney(): Money = readWithRetry {
        println("구입금액을 입력해 주세요.")
        Money(parseInt(readlnOrNull()))
    }

    fun readWinningLotto(): Lotto = readWithRetry {
        println("\n당첨 번호를 입력해 주세요.")
        val input = readlnOrNull() ?: throw IllegalArgumentException(EMPTY_INPUT)
        Lotto(input.split(",").map { LottoNumber(parseInt(it.trim())) })
    }

    fun readBonusNumber(): LottoNumber = readWithRetry {
        println("\n보너스 번호를 입력해 주세요.")
        LottoNumber(parseInt(readlnOrNull()))
    }

    private fun parseInt(input: String?): Int {
        requireNotNull(input) { EMPTY_INPUT }
        return input.toIntOrNull() ?: throw IllegalArgumentException(NOT_A_NUMBER)
    }

    private fun <T> readWithRetry(block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    companion object {
        private const val EMPTY_INPUT = "[ERROR] 입력값이 없습니다."
        private const val NOT_A_NUMBER = "[ERROR] 숫자를 입력해 주세요."
    }
}
