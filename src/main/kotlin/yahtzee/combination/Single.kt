package yahtzee.combination

class Single(
    diceValue: Int,
    frequency: Int
): Combination(diceValue, diceValue*frequency) {
    override fun toString() = "Single"
}