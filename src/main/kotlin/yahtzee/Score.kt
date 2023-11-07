package yahtzee

class Score(
    private val diceValues: List<Int>
) {
    init {
        require(diceValues.size==4) { "More than 4 dice were thrown" }
    }

    fun result() = groupDice()

    private fun groupDice() =
        diceValues.fold(mutableMapOf<Int, Int>()) { acc, value ->
            acc[value] = (acc[value] ?: 0) + 1
            acc
        }

}