package yahtzee.combination

class Pair(
    diceValue: Int
): Combination(diceValue, 2*diceValue) {
    override fun toString() = "Pair"
}