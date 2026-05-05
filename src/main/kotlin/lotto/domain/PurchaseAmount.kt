package lotto.domain

data class PurchaseAmount(val amount: Int) {
    init {
        require(amount > 0 && amount % LOTTO_PRICE == 0) {
            "[ERROR] 잘못된 금액입니다."
        }
    }

    fun getLottoPurchaseCount(): Int = amount / LOTTO_PRICE

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
