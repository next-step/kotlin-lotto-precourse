package lotto.domain

class LottoResult(lottos: List<Lotto>, winningLotto: WinningLotto, purchaseAmount: PurchaseAmount) {

    val rankCounts: Map<Rank, Int> = lottos
        .map { winningLotto.rank(it) }
        .groupingBy { it }
        .eachCount()

    val profitRate: Double = run {
        val totalPrize = rankCounts.entries.sumOf { (rank, count) -> rank.prize * count }
        totalPrize.toDouble() / purchaseAmount.value * 100
    }
}
