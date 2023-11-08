package yahtzee.combination

class ThreeOfAKind(
    private val threeOfAKindDiceValue: Int,
    override val score: Int
): Combination() {
    override fun toString() = "Three $threeOfAKindDiceValue's: $score points"
    override fun subset() = listOf(Single(threeOfAKindDiceValue, 3))
}