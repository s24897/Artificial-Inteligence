import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class KNNAlg implements Comparable<KNNAlg> {
    @Override
    public int compareTo(KNNAlg nextIris) {
        return Double.compare(this.distance, nextIris.distance);
    }

    private Iris test;
    private Iris train;
    private double distance;

    public KNNAlg(Iris test, Iris train, double distance) {
        this.test = test;
        this.train = train;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public Iris getTest() {
        return test;
    }

    public Iris getTrain() {
        return train;
    }

    //Metoda służąca do obliczania odległości między dwoma wektorami
    private static double calculateDistance(Iris iris1, Iris iris2) {
        double distanceBetweenTwoFactors = 0;
        for (int i = 0; i < iris1.getVectors().size(); i++) {
            distanceBetweenTwoFactors += Math.pow(iris1.getVectors().get(i) - iris2.getVectors().get(i), 2);
        }
        return distanceBetweenTwoFactors;
    }

    public static void KNNAlgorithm(ArrayList<Iris> testList, ArrayList<Iris> trainList, int kParameter) {

        double compatibleVectors;
        int maxIndex;
        String answ = "";
        ArrayList<Iris> tempTest = new ArrayList<>();
        ArrayList<Iris> tempTrain = new ArrayList<>();
        for (int i = 0; i < testList.size(); i++) {
            tempTest.add(testList.get(i));
            ArrayList<KNNAlg> distance = new ArrayList<>();
            for (int j = 0; j < trainList.size(); j++) {
                tempTrain.add(trainList.get(j));
                distance.add((new KNNAlg(tempTest.get(i), tempTrain.get(j), calculateDistance(tempTest.get(i), tempTrain.get(j)))));
            }
            Collections.sort(distance);
            compatibleVectors = 0;
            ArrayList<String> stringList = new ArrayList<>();
            HashSet<String> uniqueStringList = new HashSet<>();

            for (int j = 0; j < kParameter; j++) {
                if (distance.get(j).getTrain().getIrisName().equals(tempTest.get(i).getIrisName())) {
                    compatibleVectors++;
                }
                stringList.add(distance.get(j).getTrain().getIrisName());
                uniqueStringList.add(distance.get(j).getTrain().getIrisName());
            }

            maxIndex = 0;
            for (String str : uniqueStringList) {
                int temp = 0;
                for (int j = 0; j < stringList.size(); j++) {
                    if (str.equals(stringList.get(j))) {
                        temp++;
                    }
                }
                if (temp > maxIndex) {
                    maxIndex = temp;
                    answ = str;
                }
            }
            double acc = (compatibleVectors / kParameter) * 100;
            String o = String.format("Accuracy: %.3f", acc);
            System.out.println("Odpowiedz wedlug algorytmu KNN: " + answ);
            System.out.println("Poprawna odpowiedz: " + tempTest.get(i).getIrisName());
            System.out.println("Trafnosc algorytmu: " + o + "%");
            System.out.println();
        }

    }

}
