object OutputView {

    fun printLottos(lottos: List<Lotto>) {
        println("${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it) }
    }

    fun printResult(result: Map<Int, Int>, purchase: Int) {
        println("당첨 통계")
        println("---")

        println("3개 일치 (5,000원) - ${result.getOrDefault(5, 0)}개")
        println("4개 일치 (50,000원) - ${result.getOrDefault(4, 0)}개")
        println("5개 일치 (1,500,000원) - ${result.getOrDefault(3, 0)}개")
        println("5개+보너스 (30,000,000원) - ${result.getOrDefault(2, 0)}개")
        println("6개 일치 (2,000,000,000원) - ${result.getOrDefault(1, 0)}개")

        val profit = LottoResult.calculateProfit(result, purchase)
        println("총 수익률은 ${"%.1f".format(profit)}%입니다.")
    }
}