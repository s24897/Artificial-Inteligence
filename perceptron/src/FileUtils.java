import model.LanguageFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<LanguageFile> getLanguageFiles(String mainFolder) {
        List<LanguageFile> languageFiles = new ArrayList<>();
        File[] folders = new File(mainFolder).listFiles(File::isDirectory); // Pobranie wszystkich folderów w podanej lokalizacji
        if (folders == null) {
            System.out.println("Nie znaleziono folderów.");
            return languageFiles;
        }
        for (File folder : folders) {
            File[] files = folder.listFiles();
            if (files == null) {
                System.out.println("Nie znaleziono plików w folderze " + folder.getName() + ".");
                continue;
            }
            for (File file : files) {
                languageFiles.add(new LanguageFile(file.getPath(), folder.getName())); // Dodanie ścieżki do pliku i etykiety języka do listy
            }
        }
        return languageFiles;
    }

    public static String getFileContent(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line.toLowerCase()).append("\n");
            }
        }
        return contentBuilder.toString();
    }

}
