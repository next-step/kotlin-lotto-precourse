package lotto.view

object InputView {

    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        return readln().trim().toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.")
    }

    fun readWinningNumbers(): List<Int> {
        println("\n당첨 번호를 입력해 주세요.")
        return readln().trim().split(",").map {
            it.trim().toIntOrNull()
                ?: throw IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.")
        }
    }

    fun readBonusNumber(): Int {
        println("\n보너스 번호를 입력해 주세요.")
        return readln().trim().toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.")
    }
}
