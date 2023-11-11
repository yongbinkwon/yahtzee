package yahtzee.combination

import yahtzee.Die

object Yahtzee: Combination() {
    override val combinationDescription = "yahtzee"
    override fun score(dice: List<Die>) = 36
}