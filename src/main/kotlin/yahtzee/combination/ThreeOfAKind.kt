package yahtzee.combination

import yahtzee.Die

object ThreeOfAKind: Combination() {
    override fun toString() = "three of a kind"
    override fun score(dice: List<Die>) = sumOfDice(dice)
}