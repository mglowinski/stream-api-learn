package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Paths.get("names.txt")).collect(Collectors.toList());

        List<String> wordsWithVowel = getVowelsFromWords(lines);
        wordsWithVowel.forEach(System.out::println);

        List<Integer> vowelsCount = countVowels(lines);
        vowelsCount.forEach(System.out::println);

        String test = "maciej samoa education";
        System.out.println(getVowelsCountFromWord(test));

        List<String> wordsContainingAllVowels = getWordsContainingAllVowels(test);
        wordsContainingAllVowels.forEach(System.out::println);
    }

    private static List<String> getWordsWithVowel(List<String> words) {
        return words.stream()
                .filter(StreamAPI::isWordHasAnyVowel)
                .collect(Collectors.toList());
    }

    private static boolean isWordHasAnyVowel(String word) {
        List<String> vowels = Arrays.asList("a", "i", "o", "e", "u");
        return vowels.stream()
                .anyMatch(word::contains);
    }

    private static List<String> getVowelsFromWords(List<String> words) {
        return words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .filter(StreamAPI::isWordHasAnyVowel)
                .collect(Collectors.toList());
    }

    private static List<Integer> countVowels(List<String> words) {
        return words.stream()
                .map(word -> word.split(""))
                .map(StreamAPI::getVowelsFromWord)
                .map(vowelsStream -> vowelsStream.collect(Collectors.toList()))
                .map(List::size)
                .collect(Collectors.toList());
    }

    private static Stream<String> getVowelsFromWord(String[] characters) {
        List<String> vowels = Arrays.asList("a", "i", "o", "e", "u");
        return Stream.of(characters).filter(vowels::contains);
    }

    private static long getVowelsCountFromWord(String word) {
        return word.chars()
                .filter(charWord -> charWord == 'a' || charWord == 'e' || charWord == 'i'
                        || charWord == 'o' || charWord == 'u' || charWord == 'y').count();
    }

    private static List<String> getWordsContainingAllVowels(String sentence) {
        List<String> vowels = Arrays.asList("a", "i", "o", "e", "u");

        String[] split = sentence.split(" ");
        return Arrays.stream(split)
                .filter(word -> vowels.stream().allMatch(word::contains))
                .collect(Collectors.toList());
    }

}
