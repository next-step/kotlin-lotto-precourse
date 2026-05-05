import kotlin.random.Random;

object LottoGenerator {

    fun generate(amount: Int): List<Lotto> {
        val count = amount / 1000
        return List(count) { Lotto(generateNumbers()) }
    }

    private fun generateNumbers(): Set<Int> {
        return generateSequence { Random.nextInt(1, 46) }
            .distinct()
            .take(6)
            .toSet()
    }
}