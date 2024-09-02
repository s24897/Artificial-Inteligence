package model;

import java.util.Map;

public class LanguageFile {

    private final String filePath;
    private final String language;
    private Map<Character, Integer> letterFrequency;

    public LanguageFile(String filePath, String language) {
        this.filePath = filePath;
        this.language = language;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLetterFrequency(Map<Character, Integer> letterFrequency) {
        this.letterFrequency = letterFrequency;
    }

    public Map<Character, Integer> getLetterFrequency() {
        return letterFrequency;
    }
}
