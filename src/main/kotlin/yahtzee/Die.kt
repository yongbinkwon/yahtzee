package yahtzee

import kotlin.random.Random

class Die(
    private val seed: Random = Random.Default,
    private val value: Int = seed.randomDiceValue()
) {
    companion object {
        fun one() = Die(1)
        fun two() = Die(2)
        fun three() = Die(3)
        fun four() = Die(4)
        private fun Random.randomDiceValue() = nextInt(1, 5)
    }
    constructor(value: Int): this(Random.Default, value)

    init {
        require(value in 1..4) { "value of die must be in range [1, 4]" }
    }
    operator fun plus(addend: Int) = value + addend
    operator fun times(frequency: Int) = value * frequency

    fun roll() = Die(seed, seed.randomDiceValue())

    override fun toString() = "$value"
    override fun equals(other: Any?) = this === other || (other is Die && value==other.value)
    override fun hashCode() = value.hashCode()
}