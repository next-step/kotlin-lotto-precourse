package lotto.model

class WinningResult(private val ranks: Map<Rank, Int>) {
    fun countOf(rank: Rank): Int = ranks.getOrDefault(rank, 0)

    fun profitRate(amount: PurchaseAmount): Double {
        val totalPrize = ranks.entries.sumOf { it.key.prize.toLong() * it.value }
        return totalPrize * 100.0 / amount.value
    }

    companion object {
        fun of(tickets: List<Lotto>, winningLotto: Lotto, bonus: BonusNumber): WinningResult {
            val ranks = tickets.map { evaluate(it, winningLotto, bonus) }
            val grouped = ranks.groupingBy { it }.eachCount()
            return WinningResult(grouped)
        }

        private fun evaluate(ticket: Lotto, winningLotto: Lotto, bonus: BonusNumber): Rank {
            val matchCount = ticket.countMatches(winningLotto)
            val matchBonus = ticket.contains(bonus.value)
            return Rank.of(matchCount, matchBonus)
        }
    }
}
