import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String trainingPath;
    private static int learningConstant;
    private static Data trainingData = new Data();

    public static void main(String[] args) {
        System.out.println("Witaj w programie.");
        System.out.println("Proszę podaj ścieżkę do pliku treningowego:");

        Scanner scanner = new Scanner(System.in);
        trainingPath = scanner.nextLine();
        trainingData.loadData(trainingPath);
        System.out.println("Podaj wartość stałej uczenia");
        learningConstant = scanner.nextInt();

        List<Observation> list = trainingData.getObservationList();
        for (Observation o :list) {
            System.out.println(o.toString());
        }
    }

    public static void perceptron(){

    }
}
