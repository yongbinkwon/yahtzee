package yahtzee.combination

class Yahtzee(
    private val diceValue: Int
): Combination() {
    override val score = 36
    override fun subset() = listOf(Single(diceValue,4), threeOfAKind())
    private fun threeOfAKind() = ThreeOfAKind(diceValue, diceValue*4)
    override fun toString() = "Yahtzee!! That's $score points :O"
}