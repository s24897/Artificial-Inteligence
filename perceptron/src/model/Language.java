package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Language {

    private String label;
    private Map<Character, Integer> letterFrequency;
    private double[] normalizedVector;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Map<Character, Integer> getLetterFrequency() {
        return letterFrequency;
    }

    public void setLetterFrequency(List<LanguageFile> languageFiles) {
        Map<Character, Integer> letterFrequency = new HashMap<>();
        for (LanguageFile file : languageFiles) {
            if (file.getLanguage().equals(this.label)) {
                Map<Character, Integer> fileFrequency = file.getLetterFrequency();
                for (Map.Entry<Character, Integer> entry : fileFrequency.entrySet()) {
                    char letter = entry.getKey();
                    int frequency = entry.getValue();
                    letterFrequency.put(letter, letterFrequency.getOrDefault(letter, 0) + frequency);
                }
            }
        }
        this.letterFrequency = letterFrequency;
    }

    public void setNormalizedVector(double[] normalizedVector) {
        this.normalizedVector = normalizedVector;
    }

    public double[] getNormalizedVector() {
        return normalizedVector;
    }

}
