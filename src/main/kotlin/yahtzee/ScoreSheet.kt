package yahtzee

import yahtzee.combination.*
import yahtzee.combination.single.Fours
import yahtzee.combination.single.Ones
import yahtzee.combination.single.Threes
import yahtzee.combination.single.Twos
import kotlin.reflect.KClass

class ScoreSheet {
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

    fun addScoreToSheet(combination: Combination) {
        sheet[combination::class]?.run {
            sheet[combination::class] = combination
        } ?: throw IllegalStateException("combination ${combination::class.simpleName} is already in score sheet")
    }

    fun nonFilledRows() = sheet.entries.filter { it.value == null }.map { it.key }
    fun filledOut() = sheet.entries.none { it.value == null }
}

private typealias CombinationType = KClass<out Combination>