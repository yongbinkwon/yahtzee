package yahtzee.combination


abstract class Combination(
    private val diceValue: Int = 0,
    protected val score: Int
) {
    open fun result() = "$this of $diceValue: $score points"
    operator fun plus(other: Combination) = score+other.score
}