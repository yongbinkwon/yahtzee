package yahtzee.game

import yahtzee.Die

class PlayerInput {
    companion object {
        private val SERIES_OF_WHITESPACE_REGEX = Regex("(\\s+)")
        private const val REROLL_INPUT_OPTION = "reroll"
    }

    private fun readInput() = readlnOrNull()?.replace(SERIES_OF_WHITESPACE_REGEX, "")?.lowercase() ?: ""

    fun playerSelectedCombination(numberOfOptions: Int, isFinalSubRound: Boolean): Int? {
        var input: String
        val inputOptions = (1..numberOfOptions).map(Int::toString)
        do {
            println(selectCombinationMessage(isFinalSubRound))
            input = readInput()
            if (input == REROLL_INPUT_OPTION && !isFinalSubRound) return null
        } while (input !in inputOptions)
        return input.toInt()
    }

    private fun selectCombinationMessage(isFinalSubRound: Boolean) =
        "Select one of the numbered combinations above".let {
            if (isFinalSubRound) it else "$it or roll again by typing '$REROLL_INPUT_OPTION'"
        }

    fun diceToReroll(dice: List<Die>): List<Int> {
        var input: List<String>
        val inputOptions = (1..4).map(Int::toString)
        do {
            println(rerollMessage(dice))
            input = (readlnOrNull()?.trim() ?: "").split(" ")
        } while (!inputOptions.containsAll(input))
        return input.map(String::toInt)
    }

    private fun rerollMessage(dice: List<Die>) =
        "select which dice out of ${dice.joinToString()} to reroll, dice are numbered 1-4. " +
                "If you want to reroll multiple separate with space"
}