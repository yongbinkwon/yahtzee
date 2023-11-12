package yahtzee.game

import yahtzee.Board
import yahtzee.Die
import yahtzee.combination.Combination
import yahtzee.score.BlankEntry
import yahtzee.score.ScoreCard
import yahtzee.score.ScoreCardEntry
import kotlin.random.Random

class Round(
    private val scoreCard: ScoreCard
) {
    fun startRound(seed: Random = Random.Default): ScoreCardEntry {
        var dice = List(4) { Die(seed) }
        repeat(3) { numberOfRerolls ->
            if (numberOfRerolls != 0) { dice = reroll(dice) }
            println(rollMessage(dice))
            startSubRound(dice, numberOfRerolls == 2)?.let { return it }
        }
        throw IllegalStateException("no combos from round")
    }

    private fun reroll(dice: List<Die>): List<Die> {
        val rerolledDice = PlayerInput().diceToReroll(dice)
        return dice.mapIndexed { index, die -> if ((index + 1) in rerolledDice) die.roll() else die }
    }

    private fun startSubRound(dice: List<Die>, isFinalSubRound: Boolean): ScoreCardEntry? {
        val board = Board(dice)
        val potentialEntries = potentialEntries(scoreCard.nonFilledRows(), board.combinations(), dice)
        potentialEntries.printEntries()
        return PlayerInput().playerSelectedCombination(potentialEntries.size, isFinalSubRound)
            ?.let { potentialEntries[it - 1] }
    }

    private fun rollMessage(dice: List<Die>) = "You rolled the dice and got ${dice.joinToString()}"

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
        forEachIndexed { index, entry -> println("${index + 1}: $entry") }
    }
}