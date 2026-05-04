package lotto.view

import lotto.domain.Lotto

object OutputView {

    fun printLottos(lottos: List<Lotto>) {
        println("\n${lottos.size}개를 구매했습니다.")
        lottos.forEach { println(it.getNumbers()) }
    }
}
