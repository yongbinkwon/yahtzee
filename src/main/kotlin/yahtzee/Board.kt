package yahtzee

import yahtzee.combination.*

class Board(
    dice: List<Int>
) {
    private val board: Map<Int, Int> = dice.fold(mutableMapOf()) { acc, diceValue ->
        acc[diceValue] = (acc[diceValue] ?: 0) + 1
        acc
    }

    fun score(): List<Combination> {
        val sumOfDice = Anything(board.values.toList())
        if (board.keys.size == 4) return listOf(sumOfDice, Straight())
        return board.flatMap { (diceValue, frequency) -> combinations(diceValue, frequency) } + sumOfDice
    }

    private fun combinations(diceValue: Int, numberOfDice: Int) = when(numberOfDice) {
        1, 2 -> listOf(Single(diceValue, numberOfDice))
        3 -> Triple(diceValue).let { it.subset() + it }
        4 -> Yahtzee(diceValue).let { it.subset() + it }
        else -> throw IllegalArgumentException("Number of dice is $numberOfDice when it should be in range [1, 4]")
    }
}