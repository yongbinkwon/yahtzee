package yahtzee.combination.single

import yahtzee.Die
import yahtzee.combination.Combination

abstract class Single(
    private val countedDie: Die
): Combination() {
    companion object {
        fun single(die: Die) = when(die) {
            Die.one() -> Ones
            Die.two() -> Twos
            Die.three() -> Threes
            Die.four() -> Fours
            else -> throw IllegalArgumentException("Die is $die but should be in range [1, 4]")
        }
    }
    override fun score(dice: List<Die>) = countedDie*dice.frequencyOfDie(countedDie)
    private fun List<Die>.frequencyOfDie(die: Die) = count { it==die }
}