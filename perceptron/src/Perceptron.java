import java.util.Random;

public class Perceptron {

    private String language;
    private double[] weights;
    private double threshold;
    private final double alpha = 0.01;

    public Perceptron() {
        this.weights = new double[26];
        this.threshold = 0.8;

        // losowanie wag
        Random random = new Random();
        for (int i = 0; i < weights.length; i++) {
            this.weights[i] = random.nextDouble();
        }
    }

    private double activationFunction(double net) {
        if (net >= this.threshold) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    // metoda obliczająca iloczyn skalarny wektorów - suma iloczynów wektora wag i wektora wejściowego
    private double dotProduct(double[] vector1, double[] vector2) {
        double sum = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            sum += vector1[i] * vector2[i];
        }
        return sum;
    }

    public void trainPerceptron(double [] inputs, double d) {
        inputs = LetterUtils.normalize(inputs);
        this.weights = LetterUtils.normalize(this.weights);
        double dotProduct = dotProduct(inputs, this.weights);
        double y = activationFunction(dotProduct);

        if (y != d) {
            // aktualizujemy wagi
            for (int i = 0; i < this.weights.length; i++) {
                double newWeight = weights[i] + (d - y) * alpha + inputs[i];
                this.weights[i] = newWeight;
            }
            this.threshold = threshold - (d - y) * alpha;
            trainPerceptron(inputs, d);
        }
    }

    // metoda obliczająca wynik
    public double calculateOutput(double[] inputs) {
        return dotProduct(inputs, this.weights) - this.threshold;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
