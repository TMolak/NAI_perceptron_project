import java.util.InputMismatchException;
import java.util.List;
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
        System.out.println("Nieprawidłowa wartość. Podaj liczbę całkowitą.");
        scanner.next();


    }
}
