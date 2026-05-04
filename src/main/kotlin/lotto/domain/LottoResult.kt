package lotto.domain

class LottoResult(
    lottos: List<Lotto>,
    winningNumbers: List<Int>,
    bonusNumber: Int,
    private val purchaseAmount: Int,
) {
    private val ranks: List<LottoRank> = lottos.map { lotto ->
        LottoRank.of(lotto.countMatch(winningNumbers), lotto.contains(bonusNumber))
    }

    fun countByRank(rank: LottoRank): Int = ranks.count { it == rank }

    fun profitRate(): Double {
        val totalPrize = ranks.sumOf { it.prize }
        return totalPrize.toDouble() / purchaseAmount * 100
    }
}