package lotto.domain

class LottoMachine(
    private val numberGenerator: () -> List<Int> = {
        (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).shuffled().take(LOTTO_NUMBER_COUNT)
    },
) {
    fun generate(count: Int): List<Lotto> = List(count) { Lotto(numberGenerator()) }
}
