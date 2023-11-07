package yahtzee.combination

class Straight: Combination(score = 20) {
    override fun result() = "Straight: $score points"
}