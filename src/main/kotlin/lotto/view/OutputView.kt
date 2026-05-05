package lotto.view

import lotto.domain.Lottos
import lotto.domain.PurchaseAmount
import lotto.domain.WinningResult
import lotto.domain.enums.Rank

class OutputView {
    fun printPurchaseCount(count: Int) {
        println("\n${count}개를 구매했습니다.")
    }

    fun printLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto ->
            println(lotto.numbers.sorted())
        }
    }

    fun printWinningStatistics(winningResult: WinningResult, purchaseAmount: PurchaseAmount) {
        println("\n당첨 통계")
        println("---")
        printRankStatistics(winningResult)
        printProfitRate(winningResult, purchaseAmount)
    }

    private fun printRankStatistics(winningResult: WinningResult) {
        getRanksForStatistics().forEach { rank ->
            println(formatRankStatistic(rank, winningResult))
        }
    }

    private fun getRanksForStatistics(): List<Rank> {
        return Rank.entries.filter { it != Rank.NONE }.reversed()
    }

    private fun formatRankStatistic(rank: Rank, winningResult: WinningResult): String {
        val formattedPrize = formatPrize(rank.prize)
        val count = winningResult.countByRank(rank)
        return "${rank.getDescription()} (${formattedPrize}원) - ${count}개"
    }

    private fun formatPrize(prize: Int): String {
        return String.format("%,d", prize)
    }

    private fun printProfitRate(winningResult: WinningResult, purchaseAmount: PurchaseAmount) {
        val profitRate = winningResult.calculateProfitRate(purchaseAmount)
        println("총 수익률은 ${profitRate}%입니다.")
    }
}
