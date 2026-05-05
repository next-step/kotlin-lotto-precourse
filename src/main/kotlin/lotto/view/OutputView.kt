package lotto.view

import lotto.model.Lotto
import lotto.model.PurchaseAmount
import lotto.model.Rank
import lotto.model.WinningResult

class OutputView {
    fun printPurchasedLottos(lottos: List<Lotto>) {
        println()
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it.numbers) }
    }

    fun printWinningStatistics(result: WinningResult, amount: PurchaseAmount) {
        println()
        println("당첨 통계")
        println("---")
        printRankLine(Rank.FIFTH, result.countOf(Rank.FIFTH))
        printRankLine(Rank.FOURTH, result.countOf(Rank.FOURTH))
        printRankLine(Rank.THIRD, result.countOf(Rank.THIRD))
        printRankLine(Rank.SECOND, result.countOf(Rank.SECOND))
        printRankLine(Rank.FIRST, result.countOf(Rank.FIRST))
        printProfitRate(result.profitRate(amount))
    }

    fun printError(message: String) {
        println(message)
    }

    private fun printRankLine(rank: Rank, count: Int) {
        val prize = String.format("%,d", rank.prize)
        val bonusText = if (rank == Rank.SECOND) ", 보너스 볼 일치" else ""
        println("${rank.matchCount}개 일치${bonusText} (${prize}원) - ${count}개")
    }

    private fun printProfitRate(rate: Double) {
        val formatted = String.format("%.1f", rate)
        println("총 수익률은 ${formatted}%입니다.")
    }
}
