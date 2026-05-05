object LottoResult {

    private val rewards = mapOf(
        6 to 2_000_000_000,
        5 to 1_500_000,
        4 to 50_000,
        3 to 5_000
    )

    fun calculate(
        lottos: List<Lotto>,
        winning: Set<Int>,
        bonus: Int
    ): Map<Int, Int> {
        val result = mutableMapOf<Int, Int>().withDefault { 0 }

        lottos.forEach {
            val match = it.matchCount(winning)
            val rank = getRank(match, it.contains(bonus))
            if (rank != 0) result[rank] = result.getValue(rank) + 1
        }

        return result
    }

    private fun getRank(match: Int, bonus: Boolean): Int {
        if (match == 6) return 1
        if (match == 5 && bonus) return 2
        if (match == 5) return 3
        if (match == 4) return 4
        if (match == 3) return 5
        return 0
    }

    fun calculateProfit(result: Map<Int, Int>, purchase: Int): Double {
        val total = result.entries.sumOf { (rank, count) ->
            getReward(rank) * count
        }
        return total.toDouble() / purchase * 100
    }

    private fun getReward(rank: Int): Int {
        if (rank == 2) return 30_000_000
        return rewards[6 - (rank - 1)] ?: 0
    }
}