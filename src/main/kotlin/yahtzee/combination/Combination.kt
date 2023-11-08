package yahtzee.combination


abstract class Combination {
    protected abstract val score: Int
    abstract fun result(): String
    open fun subset(): List<Combination> = listOf()
    operator fun plus(other: Combination) = score + other.score

    override fun equals(other: Any?) = this === other || this sameAs other

    private infix fun sameAs(other: Any?) =
        other is Combination && this::class==other::class && score==other.score
}