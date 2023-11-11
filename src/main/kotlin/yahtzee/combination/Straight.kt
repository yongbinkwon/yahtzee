package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.*

object Straight: Combination() {
    override val combinationDescription = "straight"
    override fun score(dice: List<Die>) = 20
}