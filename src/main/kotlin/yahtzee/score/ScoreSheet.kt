package yahtzee.game

import yahtzee.combination.*
import yahtzee.combination.single.Fours
import yahtzee.combination.single.Ones
import yahtzee.combination.single.Threes
import yahtzee.combination.single.Twos

class ScoreSheet(private val playerName: String) {
    private fun initializeRowInSheet(combinationType: CombinationType) = combinationType to null
    private val sheet = mutableMapOf<CombinationType, Combination?>(
        initializeRowInSheet(Ones::class),
        initializeRowInSheet(Twos::class),
        initializeRowInSheet(Threes::class),
        initializeRowInSheet(Fours::class),
        initializeRowInSheet(ThreeOfAKind::class),
        initializeRowInSheet(Yahtzee::class),
        initializeRowInSheet(Straight::class),
        initializeRowInSheet(Chance::class)
    )

    fun addScoreToSheet(entry: ScoreSheetEntry) {
        println(sheet)
        sheet[entry.field]?.run {
            sheet[entry.field] = entry.combination
        } ?: throw IllegalStateException("combination ${entry.field::class.simpleName} is already in score sheet")
    }

    fun nonFilledRows() = sheet.entries.filter { it.value == null }.map { it.key }
    fun filledOut() = sheet.entries.none { it.value == null }

    fun results() = Result(playerName, sheet.values.filterNotNull().toList(), totalScore())
    infix fun versus(other: ScoreSheet) {
        when(totalScore().compareTo(other.totalScore())) {
            -1 -> println("${other.playerName} wins")
            0 -> println("draw")
            1 -> println("$playerName wins")
        }
    }
    private fun totalScore() = sheet.values.sumOf { it?.score() ?: 0 }
}