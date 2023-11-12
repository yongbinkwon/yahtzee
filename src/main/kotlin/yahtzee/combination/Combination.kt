package yahtzee.combination

import yahtzee.Die


abstract class Combination {
    abstract fun score(dice: List<Die> = listOf()): Int

    protected fun sumOfDice(dice: List<Die>) = dice.fold(0) { acc, die -> die + acc}
}