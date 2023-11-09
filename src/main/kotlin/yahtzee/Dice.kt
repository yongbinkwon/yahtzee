package yahtzee

import kotlin.random.Random

class Dice(
    private val seed: Random,
    private val value: Int = seed.randomDiceValue()
) {
    companion object {
        val ONE = Dice(1)
        val TWO = Dice(2)
        val THREE = Dice(3)
        val FOUR = Dice(4)
        private fun Random.randomDiceValue() = nextInt(1, 5)
        fun board(dice: List<Dice>) = Board(*dice.map(Dice::value).toIntArray())
    }
    constructor(value: Int): this(Random.Default, value)

    init {
        require(value in 1..4) { "value of dice must be in range [1, 4]" }
    }
    operator fun plus(other: Int) = value + other

    fun roll() = Dice(seed, seed.randomDiceValue())

    override fun toString() = "$value"
    override fun equals(other: Any?) = this === other || (other is Dice && value==other.value)
    override fun hashCode() = value.hashCode()
}