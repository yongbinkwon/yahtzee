package yahtzee.combination

import yahtzee.Die

object Yahtzee: Combination() {
    override fun toString() = "yahtzee"
    override fun score(dice: List<Die>) = 36
}