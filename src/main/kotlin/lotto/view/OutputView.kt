package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.Rank

object OutputView {

    fun printLottos(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it.getNumbers()) }
    }

    fun printResult(result: LottoResult) {
        println("\n당첨 통계")
        println("---")
        listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST).forEach { rank ->
            val count = result.rankCounts.getOrDefault(rank, 0)
            println("${rank.description()} - ${count}개")
        }
        println("총 수익률은 ${"%.1f".format(result.profitRate)}%입니다.")
    }

    private fun Rank.description(): String = when (this) {
        Rank.SECOND -> "5개 일치, 보너스 볼 일치 (${prize.formatWon()})"
        else -> "${matchCount}개 일치 (${prize.formatWon()})"
    }

    private fun Long.formatWon(): String = "%,d원".format(this)
}
