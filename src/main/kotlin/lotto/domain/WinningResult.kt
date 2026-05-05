package lotto.domain

import lotto.domain.enums.Rank
import java.math.BigDecimal
import java.math.RoundingMode

data class WinningResult(val ranks: List<Rank>) {
    fun calculateProfitRate(purchaseAmount: PurchaseAmount): Double {
        val totalPrize = calculateTotalPrize()
        val profitRate = (totalPrize.toBigDecimal() / purchaseAmount.amount.toBigDecimal()) * BigDecimal(100)
        return profitRate.setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    private fun calculateTotalPrize(): Int {
        return ranks.sumOf { it.prize }
    }
}
