package lotto.model

class Money(val amount: Int) {
    init {
        require(amount > 0) {
            "[ERROR] 구입 금액은 0보다 커야 합니다."
        }
        require(amount % LOTTO_PRICE == 0) {
            "[ERROR] 구입 금액은 ${LOTTO_PRICE}원 단위여야 합니다."
        }
    }

    fun lottoCount(): Int = amount / LOTTO_PRICE

    companion object {
        const val LOTTO_PRICE = 1_000
    }
}
