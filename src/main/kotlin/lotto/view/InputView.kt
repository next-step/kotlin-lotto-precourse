package lotto.view

open class InputView {
    open fun readPurchaseAmount(): String {
        println("구입금액을 입력해 주세요.")
        return readLineOrThrow()
    }

    open fun readWinningNumbers(): String {
        println()
        println("당첨 번호를 입력해 주세요.")
        return readLineOrThrow()
    }

    open fun readBonusNumber(): String {
        println()
        println("보너스 번호를 입력해 주세요.")
        return readLineOrThrow()
    }

    private fun readLineOrThrow(): String {
        val line = readlnOrNull() ?: throw IllegalStateException("[ERROR] 입력을 읽을 수 없습니다.")
        return line.trim()
    }
}
