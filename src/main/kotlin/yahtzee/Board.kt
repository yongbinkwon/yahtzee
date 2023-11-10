package yahtzee

import yahtzee.combination.*
import yahtzee.combination.single.Single

class Board(
    private val dice: List<Die>
) {
    init {
        require(dice.size == 4) { "Exactly 4 dice on the board please" }
    }

    private val board: Map<Die, Int> = dice.fold(mutableMapOf()) { acc, diceValue ->
        acc[diceValue] = (acc[diceValue] ?: 0) + 1
        acc
    }

    fun combinations(): Set<Combination> {
        if (board.keys.size == 4) return Straight().let { it.subset() + it + Chance(dice) }
        return allCombinations()
    }

    private fun allCombinations() = board.entries.fold(mutableSetOf<Combination>()) { acc, (dice, frequency) ->
        val combinations = combinationPerDie(dice, frequency)
        if (combinations.any(acc::contains)) throw RuntimeException("duplicate combinations")
        acc.apply { addAll(combinations) }
    } + Chance(dice)

    private fun combinationPerDie(die: Die, frequency: Int) = when (frequency) {
        1, 2 -> setOf(Single.single(die, frequency))
        3 -> ThreeOfAKind(dice).let { it.subset() + it }
        4 -> Yahtzee(die).let { it.subset() + it }
        else -> throw IllegalArgumentException("Number of $die's is $frequency when it should be in range [1, 4]")
    }
}