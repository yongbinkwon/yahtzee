package yahtzee

import yahtzee.Dice.Companion.randomDiceValue
import kotlin.random.Random

class Dice(
    private val seed: Random,
    private val value: Int = seed.randomDiceValue()
) {
    companion object {
        private fun Random.randomDiceValue() = nextInt(1, 5)
    }

    constructor(value: Int): this(Random.Default, value)

    fun roll() = Dice(seed, seed.randomDiceValue())
}