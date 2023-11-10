import yahtzee.ScoreSheet
import yahtzee.game.YahtzeeGame

fun main() {
    val scoreSheet = ScoreSheet()
    val yahtzeeGame = YahtzeeGame().round(scoreSheet)
}