import java.io.BufferedReader
import java.io.FileReader
import java.util.stream.Collectors

fun main(vararg args: String) {
    val validCount = BufferedReader(FileReader("input/passphrases.txt"))
        .lines()
        .map { passPhrase -> passPhraseIsValid(passPhrase) }
        .filter { isValid -> isValid }
        .count()
    println("Valid passphrases: $validCount")

    val validCountWithAnagrams = BufferedReader(FileReader("input/passphrases.txt"))
        .lines()
        .map { passPhrase -> passPhraseIsValidWithAnagrams(passPhrase) }
        .filter { isValid -> isValid }
        .count()
    println("Valid passphrases (with anagrams): $validCountWithAnagrams")
}

fun passPhraseIsValid(passPhrase: String): Boolean {
    val wordOccurrences = passPhrase.split(" ")
        .stream()
        .collect(
            Collectors.groupingBy(
                { word -> word },
                Collectors.counting<String>()
            )
        )
    val maxWordOccurrence = wordOccurrences.values.max() ?: 0
    return maxWordOccurrence <= 1
}

fun passPhraseIsValidWithAnagrams(passPhrase: String): Boolean {
    val wordOccurrences = passPhrase.split(" ")
        .stream()
        .collect(
            Collectors.groupingBy(
                { word -> word.split("").sorted().reduce { s1, s2 -> s1 + s2 } },
                Collectors.counting<String>()
            )
        )
    val maxWordOccurrence = wordOccurrences.values.max() ?: 0
    return maxWordOccurrence <= 1
}