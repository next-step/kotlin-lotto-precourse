package lotto.model

class LottoResult(ranks: List<Rank>) {
    private val countByRank: Map<Rank, Int> = Rank.entries.associateWith { rank ->
        ranks.count { it == rank }
    }

    fun countOf(rank: Rank): Int = countByRank[rank] ?: 0

    fun totalPrize(): Long = countByRank.entries.sumOf { (rank, count) -> rank.prize * count }

    fun yieldRate(money: Money): Double = totalPrize().toDouble() / money.amount * 100
}
