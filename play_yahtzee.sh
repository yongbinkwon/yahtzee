if [ "$#" -ge 2 ]; then
  ./gradlew run -q --console=plain --args "$1 $2"
else
  ./gradlew run -q --console=plain
fi