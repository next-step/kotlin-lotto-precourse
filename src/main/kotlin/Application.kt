import lotto.controller.LottoController
import lotto.util.RandomLottoNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val controller = LottoController(
        inputView = InputView(),
        outputView = OutputView(),
        numberGenerator = RandomLottoNumberGenerator(),
    )
    controller.run()
}
