package yahtzee

class Score(
    private val dice: List<Int>
) {
    init {
        require(dice.size==4) { "More than 4 dice were thrown" }
    }

    fun result() = groupDice()

    private fun groupDice() =
        dice.fold(mutableMapOf<Int, Int>()) { acc, value ->
            acc[value] = (acc[value] ?: 0) + 1
            acc
        }

}