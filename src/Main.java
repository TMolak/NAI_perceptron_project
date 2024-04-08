
import java.util.Scanner;

public class Main {
    private static String trainingPath;
    private static String testingPath;
    private static double learningRate;
    private static int epochs;


    public static void main(String[] args) {
        System.out.println("Witaj w programie.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Proszę podaj ścieżkę do pliku treningowego:");
        trainingPath = scanner.nextLine();

        System.out.println("Proszę podaj ścieżkę do pliku testowego:");
        testingPath = scanner.nextLine();

        System.out.println("Podaj wartość stałej uczenia:");
        learningRate = scanner.nextDouble();


        System.out.println("Podaj liczbę epok:");

        epochs = scanner.nextInt();


        Perceptron perceptron = new Perceptron(learningRate, trainingPath, testingPath, epochs);

        perceptron.train(perceptron.trainingList);

        System.out.println("Dokladnosc " + perceptron.testAccuracy(perceptron.testList));

        String wybor;
        do {
            System.out.println("Co chcesz zrobić?");
            System.out.println("a) Klasyfikacja własnej obserwacji podanej przez użytkownika");
            System.out.println("b) Wyjście");
            wybor = scanner.nextLine();
            switch (wybor) {
                case "a":
                    perceptron.classifyFromConsole();
                    break;
                case "b":
                    System.out.println("Wychodzę z programu.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
                    break;
            }
        } while (!wybor.equals("b"));

    }
}
