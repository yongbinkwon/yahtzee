package yahtzee

import yahtzee.combination.*

class Board(
    vararg dice: Int
) {
    init {
        require(dice.size == 4) { "Exactly 4 dice on the board please" }
    }

    private val board: Map<Int, Int> = dice.fold(mutableMapOf()) { acc, diceValue ->
        acc[diceValue] = (acc[diceValue] ?: 0) + 1
        acc
    }

    fun combinations(): Set<Combination> {
        val sumOfDice = board.entries.sumOf { (diceValue, frequency) -> diceValue*frequency }
        if (board.keys.size == 4) return Straight().let { it.subset() + it + Chance(sumOfDice) }
        return allCombinations(sumOfDice)
    }

    private fun allCombinations(sumOfDice: Int) =
        board.entries.fold(mutableSetOf<Combination>()) { acc, (diceValue, frequency) ->
            val combinations = combinationPerDiceValue(diceValue, frequency, sumOfDice)
            if (combinations.any(acc::contains)) throw RuntimeException("duplicate combinations")
            acc.apply { addAll(combinations) }
        } + Chance(sumOfDice)

    private fun combinationPerDiceValue(diceValue: Int, numberOfDice: Int, sumOfDice: Int) = when(numberOfDice) {
        1, 2 -> setOf(Single(diceValue, numberOfDice))
        3 -> ThreeOfAKind(diceValue, sumOfDice).let { it.subset() + it }
        4 -> Yahtzee(diceValue).let { it.subset() + it }
        else -> throw IllegalArgumentException("Number of dice is $numberOfDice when it should be in range [1, 4]")
    }
}