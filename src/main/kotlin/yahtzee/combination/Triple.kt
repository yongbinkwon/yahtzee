package yahtzee.combination

class Triple(
    diceValue: Int
): Combination(diceValue, 3*diceValue) {
    override fun subset() = listOf(Single(diceValue, 3))
    override fun toString() = "Three of a kind"
}