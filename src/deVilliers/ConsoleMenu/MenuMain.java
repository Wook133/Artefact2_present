package deVilliers.ConsoleMenu;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMain {
    public static ArrayList<Integer> ints = new ArrayList<Integer>();

    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        mainMenu.addChoice("Clear List", () -> Clear());
        mainMenu.addChoice("Display List", () -> Display());
        mainMenu.addChoice("Add Number to List", () -> AddNumber());

        Menu listStatistics = new Menu();
        mainMenu.addChoice("List statistics", listStatistics);

        listStatistics.addChoice("Mode", () -> Mode());
        listStatistics.addChoice("Mean", () -> {Mean();
        });
        listStatistics.addChoice("List primes", () -> Primes());
        listStatistics.addChoice("List prime factors", () -> PrimeFactors());

        mainMenu.run();
    }

    public static void Clear() {
        System.out.println("Clear");
    }

    public static void Display() {
        System.out.println("Display list");
    }

    public static void AddNumber() {
        System.out.println("Add number");
    }

    public static void Mean() {
        System.out.println("Mean");
    }

    public static void Mode() {
        System.out.println("Mode");
    }

    public static void Primes() {
        System.out.println("Primes");
    }

    public static void PrimeFactors() {
        System.out.println("Prime Factors");
    }
}
