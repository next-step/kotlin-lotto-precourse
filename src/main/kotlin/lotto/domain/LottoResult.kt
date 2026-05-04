package lotto.domain

class LottoResult(
    purchasedLottos: List<Lotto>,
    winningLotto: WinningLotto,
    private val purchaseAmount: Int,
) {
    private val ranks: List<LottoRank> = purchasedLottos.map { winningLotto.match(it) }

    fun countByRank(rank: LottoRank): Int = ranks.count { it == rank }

    fun profitRate(): Double {
        val totalPrize = ranks.sumOf { it.prize }
        return totalPrize.toDouble() / purchaseAmount * 100
    }
}