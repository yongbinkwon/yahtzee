package yahtzee.combination

class Triple(
    private val valueOfTripleDice: Int,
    override val score: Int
): Combination() {
    override fun result() = "Triple $valueOfTripleDice's: $score points"
    override fun subset() = listOf(Single(valueOfTripleDice, 3))
}