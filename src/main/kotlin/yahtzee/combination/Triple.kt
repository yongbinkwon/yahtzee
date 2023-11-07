package yahtzee.combination

class Triple(
    diceValue: Int
): Combination(diceValue, 3*diceValue) {
    override fun toString() = "Three of a kind"
}