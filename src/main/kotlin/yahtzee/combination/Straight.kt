package yahtzee.combination

class Straight: Combination() {
    override val score = 20
    override fun result() = "Straight: $score points"
}