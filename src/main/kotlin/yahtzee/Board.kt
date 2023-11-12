package yahtzee

import yahtzee.combination.*

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

    fun combinations() = frequencyBasedCombinations() + (if (board.keys.size == 4) setOf(Straight) else setOf()) + Chance

    private fun frequencyBasedCombinations() = board.entries.fold(mutableSetOf<Combination>()) { acc, (die, frequency) ->
        val combinations = combinationsPerDie(die, frequency)
        if (combinations.any(acc::contains)) throw RuntimeException("duplicate combinations")
        acc.apply { addAll(combinations) }
    }

    private fun combinationsPerDie(die: Die, frequency: Int) = listOf(1, 3, 4)
        .filter { it <= frequency }
        .map { combination(die, it)}

    private fun combination(die: Die, frequencyOfDie: Int) = when(frequencyOfDie) {
        1 -> singleDieCombination(die)
        3 -> ThreeOfAKind
        4 -> Yahtzee
        else -> throw IllegalArgumentException("Number of $die's is $frequencyOfDie when it should be in [1, 3, 4]")
    }
}