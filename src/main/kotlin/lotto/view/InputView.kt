package lotto.view

object InputView {

    fun readPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        return readln().trim().toIntOrNull()
            ?: throw IllegalArgumentException("[ERROR] 숫자를 입력해 주세요.")
    }
}
