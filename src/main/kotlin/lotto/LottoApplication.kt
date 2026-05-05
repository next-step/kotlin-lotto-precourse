package lotto

import lotto.controller.LottoController
import lotto.domain.service.LottoGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LottoController(
        inputView = InputView(),
        outputView = OutputView(),
        lottoGenerator = LottoGenerator(),
    ).run()
}