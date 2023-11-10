package yahtzee.game

import yahtzee.Board
import yahtzee.Die
import yahtzee.ScoreSheet
import yahtzee.combination.Combination
import yahtzee.combination.blank.Blank

class YahtzeeGame {
    companion object {
        private const val REROLL_STRING = "reroll"
    }

    fun round(scoreSheet: ScoreSheet) {
        var numberOfRolls = 0
        var dice = List(4) { Die() }
        numberOfRolls += 1
        println(rollMessage(dice))
        var combination = startSubRound(dice, scoreSheet, false)
        while (numberOfRolls < 3 && combination == null) {
            dice = reroll(dice)
            numberOfRolls += 1
            println(rollMessage(dice))
            val finalSubRound = numberOfRolls == 3
            combination = startSubRound(dice, scoreSheet, finalSubRound)
        }
        scoreSheet.addScoreToSheet(combination ?: throw IllegalStateException("no combos from round"))
    }

    private fun rollMessage(dice: List<Die>) = "You rolled the dice and got ${dice.joinToString()}"

    private fun reroll(dice: List<Die>): List<Die> {
        println("You have the dice ${dice.joinToString()}")
        val options = (1..4).map(Int::toString)
        val rerolledDice = PlayerInput(options).getMultiplePlayerInputs(rerollMessage(dice)).map(String::toInt)
        return dice.mapIndexed { index, die -> if ((index+1) in rerolledDice) die.roll() else die  }
    }

    private fun rerollMessage(dice: List<Die>) =
        "select which dice out of ${dice.joinToString()} to reroll, dice are numbered 1-4. If you want to reroll multiple separate with space"

    private fun startSubRound(dice: List<Die>, scoreSheet: ScoreSheet, finalSubRound: Boolean): Combination? {
        val board = Board(dice)
        val combinationsNotFilled = scoreSheet.nonFilledRows()
        val combinationsOnBoard = board.combinations()
        val possibleCombinations = combinationsNotFilled.map { combinationType ->
            combinationsOnBoard.firstOrNull { it::class == combinationType } ?: Blank.blank(combinationType)
        }
        println("these are your possible combinations")
        println(possibleCombinations.map { it.result() })
        val inputOptions = (1..possibleCombinations.size).map(Int::toString).let {
            if (finalSubRound) it else it + REROLL_STRING
        }
        val message = "Select one of the numbered combinations above".let {
            if (finalSubRound) it else "$it or roll again by typing '$REROLL_STRING'"
        }
        val selectedCombination = PlayerInput(inputOptions).getPlayerInput(message)
        if (selectedCombination == REROLL_STRING) { return null }
        return possibleCombinations[selectedCombination.toInt() - 1]
    }
}