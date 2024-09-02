import java.util.ArrayList;

public class Iris {
    private ArrayList<Double> vectors;
    private String irisName;

    public Iris(ArrayList<Double> vectors, String irisName){
        this.vectors=vectors;
        this.irisName=irisName;
    }
    public ArrayList<Double>getVectors(){return vectors;}
    public String getIrisName(){return irisName;}

}
