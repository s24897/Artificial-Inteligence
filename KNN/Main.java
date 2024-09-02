import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        ArrayList<Iris> trainingIrisList = null;
        ArrayList<Iris> testingIrisList = null;
        Scanner scan = new Scanner(System.in);

        //podawanie parametru k
        System.out.println("Podaj parametr K: ");
        int k = 0;

        boolean done = false;
        while (!done) {
            try {
                k = scan.nextInt();
                done = true;
            } catch (Exception e) {
                System.out.println("Niepoprawne dane!!! Wprowadz liczbe calkowita");
                scan.nextLine();
            }
        }


        //podawanie pliku treningowego
        System.out.println("Podaj nazwe pliku z danymi treningowymi: ");
        String trainingFileName = "";
        done=false;
        while(!done){
            try{
                ;
                trainingFileName = scan.next();
                trainingIrisList = FileUtils.getIrisList(trainingFileName);
                done = true;
            } catch(FileNotFoundException e){
                System.out.println("Niepoprawna nazwa pliku. Upewnij sie ze podana nazwa jest poprawna i plik znajduje sie w folderze \"Data\"");
                scan.nextLine();

            }
        }

//
//        for(int i=0;i<30;i++){
//            System.out.println(trainingIrisList.get(i).getVectors());
//        }

        System.out.println("Wybierz opcje: ");
        System.out.println("1.Skorzystaj z pliku z danymi testowymi");
        System.out.println("2.Wprowadz wartosci testowane recznie");
        int choice = 0;

        done=false;
        while(!done){
            try{
                while(choice<1||choice>2) {
                    System.out.println("Podaj swoj wybor: ");
                    choice = scan.nextInt();
                    done = true;
                }
            } catch(Exception e){
                System.out.println("Niewlasiciwa wartosc. Podaj swoj wybor jeszcze raz");
                scan.nextLine();
            }
        }
        if (choice == 1) {
            //podawanie pliku testowego
            System.out.println("Podaj nazwe pliku z danymi testowymi: ");
            String testingFileName="";
            done=false;
            while(!done){
                try{
                    ;
                    testingFileName = scan.next();
                    testingIrisList = FileUtils.getIrisList(testingFileName);
                    done = true;
                } catch(FileNotFoundException e){
                    System.out.println("Niepoprawna nazwa pliku. Upewnij sie ze podana nazwa jest poprawna i plik znajduje sie w folderze \"Data\"");
                    scan.nextLine();
                }
            }
            KNNAlg.KNNAlgorithm(testingIrisList, trainingIrisList, k);
        } else {
            //podawanie danych do konsoli i zapis danych do ArrayList podobnie jak w klasie FileUtils
            for (; ; ) {
                ArrayList<Iris> irisArrayList = new ArrayList<>();
                ArrayList<Double> consoleIrisParameters = new ArrayList<>();
                System.out.println("Podaj parametry wektora oddzielone znakiem \";\", lub wpisz \"END\" aby zakonczyc podawanie wektorow");
                String irisParameters = scan.next();
                if (irisParameters.equals("END") || irisParameters.equals("end") || irisParameters.equals("End")) {
                    break;
                } else {
                    String[] temp = irisParameters.split(";");
                    for (int i = 0; i < temp.length - 1; i++) {
                        consoleIrisParameters.add(Double.parseDouble(temp[i]));
                    }
                    irisArrayList.add(new Iris(consoleIrisParameters, temp[temp.length - 1]));
                    testingIrisList = irisArrayList;
                    KNNAlg.KNNAlgorithm(testingIrisList, trainingIrisList, k);
                }
            }

        }

    }
}
