package yahtzee.combination

import yahtzee.Die

object Chance: Combination() {
    override fun toString() = "chance (sum of dice)"
    override fun score(dice: List<Die>) = sumOfDice(dice)
}