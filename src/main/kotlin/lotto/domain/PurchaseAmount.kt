package lotto.domain

class PurchaseAmount(val value: Int) {

    init {
        require(value > 0) { "[ERROR] 구입 금액은 1,000원 이상이어야 합니다." }
        require(value % LOTTO_PRICE == 0) { "[ERROR] 구입 금액은 1,000원 단위여야 합니다." }
    }

    fun lottoCount(): Int = value / LOTTO_PRICE

    companion object {
        private const val LOTTO_PRICE = 1_000
    }
}
