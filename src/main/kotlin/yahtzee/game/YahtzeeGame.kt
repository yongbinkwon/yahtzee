package yahtzee.game

import yahtzee.Board
import yahtzee.Die
import yahtzee.combination.Combination
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreCard
import yahtzee.score.ScoreCardEntry

class YahtzeeGame {
    companion object {
        private const val REROLL_STRING = "reroll"
    }

    fun startGame(player1: String, player2: String) {
        var scoreCard1 = ScoreCard(playerName = player1)
        var scoreCard2 = ScoreCard(playerName = player2)
        while (!(scoreCard1.filledOut() && scoreCard2.filledOut())) {
            println("$scoreCard1's turn")
            scoreCard1 = round(scoreCard1)
            println("$scoreCard2's turn")
            scoreCard2 = round(scoreCard2)
        }
        val result1 = scoreCard1.result()
        val result2 = scoreCard2.result()

        println("$result1\n")
        println("$result2\n")
        println(result1 versus result2)
    }

    private fun round(scoreCard: ScoreCard): ScoreCard {
        var numberOfRolls = 0
        var dice = List(4) { Die() }
        numberOfRolls += 1
        println(rollMessage(dice))
        var scoreCardEntry = startSubRound(dice, scoreCard, false)
        while (numberOfRolls < 3 && scoreCardEntry == null) {
            dice = reroll(dice)
            numberOfRolls += 1
            println(rollMessage(dice))
            val finalSubRound = numberOfRolls == 3
            scoreCardEntry = startSubRound(dice, scoreCard, finalSubRound)
        }
        return (scoreCardEntry ?: throw IllegalStateException("no combos from round")) addTo scoreCard
    }

    private fun rollMessage(dice: List<Die>) = "You rolled the dice and got ${dice.joinToString()}"

    private fun reroll(dice: List<Die>): List<Die> {
        println("You have the dice ${dice.joinToString()}")
        val options = (1..4).map(Int::toString)
        val rerolledDice = PlayerInput(options).getMultiplePlayerInputs(rerollMessage(dice)).map(String::toInt)
        return dice.mapIndexed { index, die -> if ((index + 1) in rerolledDice) die.roll() else die }
    }

    private fun rerollMessage(dice: List<Die>) =
        "select which dice out of ${dice.joinToString()} to reroll, dice are numbered 1-4. If you want to reroll multiple separate with space"

    private fun startSubRound(dice: List<Die>, scoreCard: ScoreCard, finalSubRound: Boolean): ScoreCardEntry? {
        val board = Board(dice)
        val potentialEntries = potentialEntries(scoreCard.nonFilledRows(), board.combinations(), dice)
        println("these are your selectable entries")
        potentialEntries.printEntries()
        val inputOptions = (1..potentialEntries.size).map(Int::toString).let {
            if (finalSubRound) it else it + REROLL_STRING
        }
        val message = "Select one of the numbered combinations above".let {
            if (finalSubRound) it else "$it or roll again by typing '$REROLL_STRING'"
        }
        val selectedCombination = PlayerInput(inputOptions).getPlayerInput(message)
        if (selectedCombination == REROLL_STRING) {
            return null
        }
        return potentialEntries[selectedCombination.toInt() - 1]
    }

    private fun potentialEntries(combinationsNotFilled: Set<Combination>, combinationsOnBoard: Set<Combination>, dice: List<Die>) =
        combinationsNotFilled.map { notFilledCombination ->
            combinationsOnBoard.firstOrNull { it == notFilledCombination }?.let {
                ScoreCardEntry(it, it.score(dice))
            } ?: BlankEntry(notFilledCombination)
        }

    private fun List<ScoreCardEntry>.printEntries() {
        forEachIndexed { index, entry ->
            println("${index+1}: $entry")
        }
    }
}