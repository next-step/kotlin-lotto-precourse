package lotto.controller

import lotto.model.LottoMachine
import lotto.model.LottoResult
import lotto.model.Rank
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val machine: LottoMachine,
) {
    fun run() {
        val money = inputView.readMoney()
        val lottos = machine.issue(money.lottoCount())
        outputView.printLottos(lottos)
        val winning = readWinningLotto()
        val result = LottoResult(lottos.map { Rank.of(winning.match(it)) })
        outputView.printResult(result, money)
    }

    private fun readWinningLotto(): WinningLotto {
        val numbers = inputView.readWinningLotto()
        while (true) {
            try {
                return WinningLotto(numbers, inputView.readBonusNumber())
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
