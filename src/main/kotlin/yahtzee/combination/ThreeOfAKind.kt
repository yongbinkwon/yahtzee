package yahtzee.combination

import yahtzee.Die
import yahtzee.combination.single.*

class ThreeOfAKind: Combination() {
    override val combinationDescription = "three of a kind"
    override fun score(dice: List<Die>) = sumOfDice(dice)
}