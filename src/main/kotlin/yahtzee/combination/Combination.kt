package yahtzee.combination


abstract class Combination(
    protected val diceValue: Int = 0,
    protected val score: Int
) {
    companion object {
        fun allCombination(groupedDiceValues: Map<Int, Int>) =

            groupedDiceValues.let {
                val sumOfDice = Anything(it.values.toList())
                if (it.keys.size == 4) return@let listOf(Straight(), sumOfDice)
                if (it.keys.size == )
                return@let listOf(sumOfDice)
            }
    }

    open fun result() = "$this of $diceValue: $score points"
    open fun subset(): List<Combination> = listOf()
    operator fun plus(other: Combination) = score + other.score
}