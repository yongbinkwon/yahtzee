package yahtzee

import yahtzee.Dice.Companion.randomDiceValue
import kotlin.random.Random

class Dice(
    private val seed: Random,
    private val value: Int = seed.randomDiceValue()
) {
    companion object {
        private fun Random.randomDiceValue() = nextInt(1, 5)
        fun List<Dice>.score() = Score(map(Dice::value))
    }

    constructor(value: Int): this(Random.Default, value)

    operator fun plus(other: Board) = other.apply { addDice(value) }

    fun roll() = Dice(seed, seed.randomDiceValue())
}