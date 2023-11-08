package yahtzee.combination

class Chance(
    override val score: Int
): Combination() {
    override fun toString() = "Chance (sum of all dice): $score points"
}