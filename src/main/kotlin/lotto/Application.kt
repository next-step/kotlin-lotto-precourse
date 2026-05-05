package lotto

import lotto.controller.LottoController
import lotto.model.LottoMachine
import lotto.util.RandomNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LottoController(
        inputView = InputView(),
        outputView = OutputView(),
        machine = LottoMachine(RandomNumberGenerator()),
    ).run()
}
