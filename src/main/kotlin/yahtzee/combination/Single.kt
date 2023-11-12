package yahtzee.combination

import yahtzee.Die

fun singleDieCombination(die: Die) = when(die) {
    Die.one() -> Ones
    Die.two() -> Twos
    Die.three() -> Threes
    Die.four() -> Fours
    else -> throw IllegalArgumentException("Die is $die but should be in range [1, 4]")
}
private fun sumOfOneDie(dice: List<Die>, countedDie: Die) = countedDie*dice.frequencyOfDie(countedDie)
private fun List<Die>.frequencyOfDie(die: Die) = count { it==die }

object Ones: Combination() {
    override fun toString() = "ones"
    override fun score(dice: List<Die>) = sumOfOneDie(dice, Die.one())
}

object Twos: Combination() {
    override fun toString() = "twos"
    override fun score(dice: List<Die>) = sumOfOneDie(dice, Die.two())
}

object Threes: Combination() {
    override fun toString() = "threes"
    override fun score(dice: List<Die>) = sumOfOneDie(dice, Die.three())
}

object Fours: Combination() {
    override fun toString() = "fours"
    override fun score(dice: List<Die>) = sumOfOneDie(dice, Die.four())
}