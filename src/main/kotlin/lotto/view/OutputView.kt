package lotto.view

import lotto.model.Lotto
import lotto.model.LottoResult
import lotto.model.Money
import lotto.model.Rank

class OutputView {

    fun printLottos(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(formatLotto(it)) }
    }

    fun printResult(result: LottoResult, money: Money) {
        println("\n당첨 통계\n---")
        DISPLAY_ORDER.forEach { rank -> println(formatRank(rank, result.countOf(rank))) }
        println("총 수익률은 ${"%.1f".format(result.yieldRate(money))}%입니다.")
    }

    private fun formatLotto(lotto: Lotto): String =
        lotto.numbers.map { it.value }.sorted().toString()

    private fun formatRank(rank: Rank, count: Int): String {
        val prize = "%,d".format(rank.prize)
        if (rank == Rank.SECOND) return "${rank.matchCount}개 일치, 보너스 볼 일치 (${prize}원) - ${count}개"
        return "${rank.matchCount}개 일치 (${prize}원) - ${count}개"
    }

    companion object {
        private val DISPLAY_ORDER = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)
    }
}
