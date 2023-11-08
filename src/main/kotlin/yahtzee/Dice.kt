package yahtzee

import kotlin.random.Random

class Dice(
    private val seed: Random,
    private val value: Int = seed.randomDiceValue()
) {
    companion object {
        private fun Random.randomDiceValue() = nextInt(1, 5)
        fun board(dice: List<Dice>) = Board(*dice.map(Dice::value).toIntArray())
    }
    constructor(value: Int): this(Random.Default, value)

    init {
        require(value in 1..4) { "value of dice must be in range [1, 4]" }
    }

    fun roll() = Dice(seed, seed.randomDiceValue())
}