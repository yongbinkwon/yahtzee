# Yahtzee
This is an implementation of yahtzee with 4 dice. Pairs are not valid, and yahtzee gives 36 points

To play yahtzee with default names for players:
* run shell script with "./play_yahtzee.sh"
* run "./gradlew run -q --console=plain" in terminal
* run main() in Main.kt directly (though IDE for example)

To specify custom player names:
* run shell script with "./play_yahtzee.sh $1 $2" where $1 and $2 are p1 and p2 names
* run "./gradlew run -q --console=plain --args "$1 $2"" in terminal where $1 and $2 are p1 and p2 names

Have fun >:)
