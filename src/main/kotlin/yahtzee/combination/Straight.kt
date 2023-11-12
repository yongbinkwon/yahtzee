package yahtzee.combination

import yahtzee.Die

object Straight: Combination() {
    override fun toString() = "straight"
    override fun score(dice: List<Die>) = 20
}