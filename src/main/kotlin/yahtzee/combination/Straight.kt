package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.*

object Straight: Combination() {
    override fun toString() = "straight"
    override fun score(dice: List<Die>) = 20
}