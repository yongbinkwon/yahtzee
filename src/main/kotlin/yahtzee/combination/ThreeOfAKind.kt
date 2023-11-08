package yahtzee.combination

class ThreeOfAKind(
    private val threeOfAKindDiceValue: Int,
    override val score: Int
): Combination() {
    override fun subset() = setOf(Single(threeOfAKindDiceValue, 3))
    override fun toString() = "Three $threeOfAKindDiceValue's: $score points"
}