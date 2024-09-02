import java.util.HashMap;
import java.util.Map;

public class LetterUtils {

    public static Map<Character, Integer> calculateFrequency(String text) {
        Map<Character, Integer> letterFrequency = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            letterFrequency.put(c, 0); // Inicjalizacja wartości 0 dla każdej litery
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (letterFrequency.containsKey(c)) {
                letterFrequency.put(c, letterFrequency.get(c) + 1); // Zwiększenie liczby wystąpień litery o 1
            }
        }
        return letterFrequency;
    }


    public static double[] normalize(Map<Character, Integer> letterFrequency) {
        int[] input = letterFrequency.values().stream()
                .mapToInt(Integer::intValue)
                .toArray();

        double length = 0.0;
        for (Integer element : input) {
            length += element * element;
        }
        length = Math.sqrt(length);

        double[] normalizedOutput = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            normalizedOutput[i] = input[i] / length;
        }
        return normalizedOutput;
    }


    public static double[] normalize(double[] input) {
        double length = 0.0;
        for (Double element : input) {
            length += element * element;
        }
        length = Math.sqrt(length);

        double[] normalizedOutput = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            normalizedOutput[i] = input[i] / length;
        }
        return normalizedOutput;
    }
}
