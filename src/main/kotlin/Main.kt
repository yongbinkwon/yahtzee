import yahtzee.game.YahtzeeGame

fun main(args: Array<String>) {
    if (args.size == 2) {
        YahtzeeGame().startGame(args[0], args[1])
    } else {
        val defaultPlayerName1 = "bin"
        val defaultPlayerName2 = "kristian"
        println("didn't get two arguments for p1 and p2 name, " +
                "going with default names $defaultPlayerName1 (wii) and $defaultPlayerName2 (boo)")
        YahtzeeGame().startGame(defaultPlayerName1, defaultPlayerName2)
    }

}