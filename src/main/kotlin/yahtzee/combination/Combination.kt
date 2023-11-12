package yahtzee.combination

import yahtzee.Die


sealed class Combination {
    abstract fun score(dice: List<Die> = listOf()): Int

    protected fun sumOfDice(dice: List<Die>) = dice.fold(0) { acc, die -> die + acc}
}