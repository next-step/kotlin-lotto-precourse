package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoRank
import lotto.domain.LottoResult

object OutputHandler {
    fun printLottos(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it) }
    }

    fun printResult(result: LottoResult) {
        println("\n당첨 통계")
        println("---")
        listOf(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST)
            .forEach { rank -> println(rank.toDisplayString(result.countByRank(rank))) }
        println("총 수익률은 ${"%.1f".format(result.profitRate())}%입니다.")
    }

    private fun LottoRank.toDisplayString(count: Int): String {
        val prizeStr = "%,d".format(prize)
        return when (this) {
            LottoRank.SECOND -> "5개 일치, 보너스 볼 일치 (${prizeStr}원) - ${count}개"
            else -> "${matchCount}개 일치 (${prizeStr}원) - ${count}개"
        }
    }
}
