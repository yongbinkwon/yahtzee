package yahtzee.combination


abstract class Combination(
    protected val diceValue: Int = 0,
    protected val score: Int
) {
    open fun result() = "$this of $diceValue: $score points"
    open fun subset(): List<Combination> = listOf()
    operator fun plus(other: Combination) = score + other.score
}