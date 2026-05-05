package lotto.domain

import lotto.domain.enums.Rank
import java.math.BigDecimal
import java.math.RoundingMode

data class WinningResult(val ranks: List<Rank>) {
    fun calculateProfitRate(purchaseAmount: PurchaseAmount): Double {
        val totalPrize = calculateTotalPrize()
        val profitRate = totalPrize.toBigDecimal()
            .divide(purchaseAmount.amount.toBigDecimal(), 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
        return profitRate.toDouble()
    }

    fun countByRank(rank: Rank): Int {
        return ranks.count { it == rank }
    }

    private fun calculateTotalPrize(): Int {
        return ranks.sumOf { it.prize }
    }
}
