import model.Language;
import model.LanguageFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileLetterCount {

    private static void createLanguageIfNotExists(List<Language> languages, LanguageFile file) {
        Optional<Language> languageOptional = languages.stream().filter(item -> item.getLabel().equals(file.getLanguage())).findFirst();
        if (languageOptional.isEmpty()) {
            Language language = new Language();
            language.setLabel(file.getLanguage());
            languages.add(language);
        }
    }

    private static void setLetterFrequencyForEveryLanguage(List<LanguageFile> languageFiles, List<Language> languages) {
        System.out.println("Wektor wejsciowy jezykow: ");
        for (Language language : languages) {
            language.setLetterFrequency(languageFiles);
            System.out.println(language.getLabel() + ": " + language.getLetterFrequency());
        }
    }

    private static void normalizeInputVectorForEveryLanguage(List<Language> languages) {
        System.out.println("Znormalizowany wektor wejsciowy jezykow: ");
        for (Language language : languages) {
            double[] normalizedVector = LetterUtils.normalize(language.getLetterFrequency());
            System.out.println(language.getLabel() + " : " + Arrays.toString(normalizedVector));
            language.setNormalizedVector(normalizedVector);
        }
    }

    public static void main(String[] args) throws IOException {
        List<LanguageFile> languageFiles = FileUtils.getLanguageFiles("./dane");
        List<Language> languages = new ArrayList<>();

        for (LanguageFile file : languageFiles) {
            createLanguageIfNotExists(languages, file);
            String content = FileUtils.getFileContent(file.getFilePath());
            file.setLetterFrequency(LetterUtils.calculateFrequency(content));
        }

        setLetterFrequencyForEveryLanguage(languageFiles, languages);
        normalizeInputVectorForEveryLanguage(languages);

        List<Perceptron> perceptrons = new ArrayList<>();

        for (Language language : languages) {
            Perceptron perceptron = new Perceptron();
            perceptron.setLanguage(language.getLabel());
            perceptron.trainPerceptron(language.getNormalizedVector(), 1.0);
            perceptrons.add(perceptron);
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj tekst do sprawdzenia: ");
            String inputText = new String(scanner.nextLine().getBytes(StandardCharsets.UTF_8)).toLowerCase();
            if (inputText.trim().length() != 0) {
                Map<Character, Integer> letterFrequency = LetterUtils.calculateFrequency(inputText);
                double[] input = LetterUtils.normalize(letterFrequency);

                double maxOutput = 0.0;
                String language = null;
                for (Perceptron perceptron : perceptrons) {
                    double output = perceptron.calculateOutput(input);
                    System.out.println(perceptron.getLanguage() + " : " + output);
                    if (maxOutput < output) {
                        maxOutput = output;
                        language = perceptron.getLanguage();
                    }
                }
                System.out.println("Tekst jest najprawdopodobniej w języku: " + language);
            }
        }
    }


}
