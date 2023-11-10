package yahtzee.game

class PlayerInput(
    private val inputOptions: List<String>
) {
    companion object {
        private val SERIES_OF_WHITESPACE_REGEX = Regex("(\\s+)")
    }

    private fun readInput() = readlnOrNull()?.replace(SERIES_OF_WHITESPACE_REGEX, "")?.lowercase() ?: ""

    fun getPlayerInput(message: String): String {
        var input: String
        do {
            println(message)
            input = readInput()
        } while (input !in inputOptions)
        return input
    }

    fun getMultiplePlayerInputs(message: String): List<String> {
        var input: List<String>
        do {
            println(message)
            input = (readlnOrNull()?.trim() ?: "").split(" ")
        } while (!inputOptions.containsAll(input))
        return input
    }
}