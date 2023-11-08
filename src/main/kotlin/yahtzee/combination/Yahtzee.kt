package yahtzee.combination

class Yahtzee(
    private val diceValue: Int
): Combination() {
    override val score = 36
    override fun subset() = listOf(Single(diceValue,4), triple())
    private fun triple() = Triple(diceValue, List(4) { diceValue })
    override fun result() = "Yahtzee!! That's $score points :O"
}