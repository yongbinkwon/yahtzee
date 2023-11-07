package yahtzee.combination

class Single(
    diceValue: Int
): Combination(diceValue, diceValue) {
    override fun toString() = "Single"
}