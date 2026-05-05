import kotlin.random.Random;

object LottoGenerator {

    fun generate(amount: Int): List<Lotto> {
        return List(amount) { Lotto(generateNumbers()) }
    }

    private fun generateNumbers(): Set<Int> {
        return generateSequence { Random.nextInt(1, 46) }
            .distinct()
            .take(6)
            .toSet()
    }
}