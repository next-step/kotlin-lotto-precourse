package lotto.model

import lotto.model.LottoConstants.LOTTO_PRICE

@JvmInline
value class PurchaseAmount(val value: Int) {
    init {
        require(value > 0) { "[ERROR] 구입 금액은 0보다 커야 합니다." }
        require(value % LOTTO_PRICE == 0) { "[ERROR] 구입 금액은 ${LOTTO_PRICE}원 단위여야 합니다." }
    }

    fun lottoCount(): Int = value / LOTTO_PRICE

    companion object {
        fun from(input: String): PurchaseAmount {
            val amount = input.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.")
            return PurchaseAmount(amount)
        }
    }
}
