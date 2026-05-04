package lotto.view

import lotto.domain.LOTTO_PRICE

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

}
