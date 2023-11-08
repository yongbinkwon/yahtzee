package yahtzee.combination

class Yahtzee(
    diceValue: Int
): Combination(diceValue, 36) {
    override fun subset() = listOf(Single(diceValue, 4), Triple(diceValue))
    override fun result() = "Yahtzee!! That's $score points :O"
}