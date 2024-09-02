import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtils {
    public static ArrayList<Iris> getIrisList(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./Data/" + fileName))) {

            String line;
            ArrayList<Iris> irisList = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                String[] temperoryIrisData = line.split(";");
                ArrayList<Double> vectors = new ArrayList<>();
                for (int i = 0; i < temperoryIrisData.length - 1; i++) {
                    vectors.add(Double.parseDouble(temperoryIrisData[i]));
                }
                irisList.add(new Iris(vectors, temperoryIrisData[temperoryIrisData.length - 1]));
            }
            return irisList;
        }
    }
}
