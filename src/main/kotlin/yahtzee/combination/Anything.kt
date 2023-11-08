package yahtzee.combination

class Anything(
    override val score: Int
): Combination() {
    override fun result() = "Sum of dice: $score points"
}