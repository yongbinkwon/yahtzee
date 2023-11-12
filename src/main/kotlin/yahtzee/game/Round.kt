package yahtzee.game

import yahtzee.Board
import yahtzee.Die
import yahtzee.combination.Combination
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreCard
import yahtzee.score.ScoreCardEntry

class Round(
    private val scoreCard: ScoreCard
) {
    companion object {
        private const val REROLL_INPUT_OPTION = "reroll"
    }
    fun startRound(): ScoreCardEntry {
        var dice = List(4) { Die() }
        repeat(3) { numberOfRerolls ->
            if (numberOfRerolls != 0) { dice = reroll(dice) }
            println(rollMessage(dice))
            startSubRound(dice, numberOfRerolls == 2)?.let { return it }
        }
        throw IllegalStateException("no combos from round")
    }

    private fun reroll(dice: List<Die>): List<Die> {
        val options = (1..4).map(Int::toString)
        val rerolledDice = PlayerInput(options).getMultiplePlayerInputs(rerollMessage(dice)).map(String::toInt)
        return dice.mapIndexed { index, die -> if ((index + 1) in rerolledDice) die.roll() else die }
    }

    private fun rerollMessage(dice: List<Die>) =
        "select which dice out of ${dice.joinToString()} to reroll, dice are numbered 1-4. " +
                "If you want to reroll multiple separate with space"

    private fun startSubRound(dice: List<Die>, isFinalSubRound: Boolean): ScoreCardEntry? {
        val board = Board(dice)
        val potentialEntries = potentialEntries(scoreCard.nonFilledRows(), board.combinations(), dice)
        potentialEntries.printEntries()
        val inputOptions = selectCombinationInputOptions(potentialEntries.size, isFinalSubRound)
        return PlayerInput(inputOptions).getPlayerInput(selectCombinationMessage(isFinalSubRound))
            .takeUnless { it == REROLL_INPUT_OPTION }?.let { potentialEntries[it.toInt() - 1] }
    }

    private fun rollMessage(dice: List<Die>) = "You rolled the dice and got ${dice.joinToString()}"

    private fun selectCombinationInputOptions(numberOfPotentialEntries: Int, isFinalSubRound: Boolean) =
        (1..numberOfPotentialEntries).map(Int::toString).let {
            if (isFinalSubRound) it else it + REROLL_INPUT_OPTION
        }

    private fun selectCombinationMessage(isFinalSubRound: Boolean) = "Select one of the numbered combinations above".let {
        if (isFinalSubRound) it else "$it or roll again by typing '$REROLL_INPUT_OPTION'"
    }

    private fun potentialEntries(
        combinationsNotFilled: Set<Combination>,
        combinationsOnBoard: Set<Combination>,
        dice: List<Die>
    ) =
        combinationsNotFilled.map { notFilledCombination ->
            combinationsOnBoard.firstOrNull { it == notFilledCombination }?.let {
                ScoreCardEntry(it, it.score(dice))
            } ?: BlankEntry(notFilledCombination)
        }

    private fun List<ScoreCardEntry>.printEntries() {
        println("these are your selectable entries")
        forEachIndexed { index, entry ->
            println("${index + 1}: $entry")
        }
    }
}