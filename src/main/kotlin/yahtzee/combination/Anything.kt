package yahtzee.combination

class Anything(
    private val diceValues: List<Int>
): Combination(score = diceValues.sum()) {
    override fun result() = "Sum of dice: $score points"
}