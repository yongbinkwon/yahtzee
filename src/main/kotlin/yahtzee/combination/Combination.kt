package yahtzee.combination

import yahtzee.Die


abstract class Combination(
    private val diceForScoring: List<Die>
) {
    open fun score() = diceForScoring.fold(0) { acc, dice ->  dice + acc}
    open fun subset(): Set<Combination> = setOf()
    operator fun plus(other: Combination) = score() + other.score()

    override fun equals(other: Any?) = this === other || this sameAs other

    private infix fun sameAs(other: Any?) =
        other is Combination && this::class==other::class && this sameDice other

    private infix fun sameDice(other: Combination) = diceCounts() == other.diceCounts()
    private fun diceCounts() = diceForScoring.groupingBy { it }.eachCount()

    override fun hashCode(): Int {
        var result = this::class.hashCode()
        result = 31 * result + diceCounts().hashCode()
        return result
    }
}