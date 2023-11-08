package yahtzee.combination


abstract class Combination {
    protected abstract val score: Int
    abstract fun result(): String
    open fun subset(): List<Combination> = listOf()
    operator fun plus(other: Combination) = score + other.score
}