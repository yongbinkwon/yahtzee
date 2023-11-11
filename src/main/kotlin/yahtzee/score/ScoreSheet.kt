package yahtzee.score

import yahtzee.combination.*
import yahtzee.combination.single.Fours
import yahtzee.combination.single.Ones
import yahtzee.combination.single.Threes
import yahtzee.combination.single.Twos

class ScoreSheet(private val playerName: String) {
    private fun initializeRowInSheet(combination: Combination) = combination to null
    private val sheet = mutableMapOf<Combination, ScoreSheetEntry?>(
        initializeRowInSheet(Ones),
        initializeRowInSheet(Twos),
        initializeRowInSheet(Threes),
        initializeRowInSheet(Fours),
        initializeRowInSheet(ThreeOfAKind),
        initializeRowInSheet(Yahtzee),
        initializeRowInSheet(Straight),
        initializeRowInSheet(Chance)
    )

    fun addScoreToSheet(row: Combination, entry: ScoreSheetEntry) {
        sheet[row]?.run {
            sheet[row] = entry
        } ?: throw IllegalStateException("combination $row is already in score sheet")
    }

    fun nonFilledRows() = sheet.entries.filter { it.value == null }.map { it.key }
    fun filledOut() = sheet.entries.none { it.value == null }

    /*
    fun results() = Result(playerName, sheet.values.filterNotNull().toList(), totalScore())
    infix fun versus(other: ScoreSheet) {
        when(totalScore().compareTo(other.totalScore())) {
            -1 -> println("${other.playerName} wins")
            0 -> println("draw")
            1 -> println("$playerName wins")
        }
    }
    private fun totalScore() = sheet.values.sumOf { it?.score() ?: 0 }

     */
}