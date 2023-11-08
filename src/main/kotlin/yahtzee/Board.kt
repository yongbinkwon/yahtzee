package yahtzee

import yahtzee.combination.Anything
import yahtzee.combination.Combination
import yahtzee.combination.Straight
import yahtzee.combination.Yahtzee

class Board {
    private val board = mutableMapOf<Int, Int>()

    fun score(): List<Combination> {
        val sumOfDice = Anything(board.values.toList())
        if (board.keys.size == 4) return listOf(sumOfDice, Straight())
        if (board.keys.size == 1) return listOf(sumOfDice, Yahtzee())
        board.flatMap {
            
        }
    }

    fun addDice(diceValue: Int) {
        board[diceValue] = (board[diceValue] ?: 0) + 1
    }

    operator fun plus(dice: Dice) = dice + this
}