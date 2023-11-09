package yahtzee.combination

import yahtzee.Dice


abstract class Combination(
    private val diceForScoring: List<Dice>
) {
    protected open val score = diceForScoring.fold(0) { acc, dice ->  dice + acc}
    open fun subset(): Set<Combination> = setOf()
    operator fun plus(other: Combination) = score + other.score

    override fun equals(other: Any?) = this === other || this sameAs other

    private infix fun sameAs(other: Any?) =
        other is Combination && this::class==other::class && this sameDice other

    private infix fun sameDice(other: Combination) = diceCounts() == other.diceCounts()
    private fun diceCounts() = diceForScoring.groupingBy { it }.eachCount()

    override fun hashCode(): Int {
        var result = score.hashCode()
        result = 31 * result + this::class.hashCode()
        return result
    }
}