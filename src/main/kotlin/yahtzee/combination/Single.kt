package yahtzee.combination

class Single(
    private val diceValue: Int,
    private val frequency: Int
): Combination() {
    override val score = diceValue*frequency
    override fun toString() = "$frequency counts of $diceValue's: $score points"
}