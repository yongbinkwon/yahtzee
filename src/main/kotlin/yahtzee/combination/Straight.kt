package yahtzee.combination

class Straight: Combination() {
    override val score = 20
    override fun subset() = setOf(
        Single(1, 1),
        Single(2, 1),
        Single(3, 1),
        Single(4, 1)
    )
    override fun toString() = "Straight: $score points"
}