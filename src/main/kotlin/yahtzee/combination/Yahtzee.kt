package yahtzee.combination

class Yahtzee: Combination(score = 36) {
    override fun result() = "Yahtzee!! That's $score points :O"
}