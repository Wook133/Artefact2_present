package deVilliers.ConsoleMenu;

import java.util.Scanner;

public class Menu implements Runnable {
    private Pairs<String, Runnable> choices;
    private int choice;

    public Menu () {
        choices = new Pairs<>();
    }

    @Override
    public void run() {
        do {
            displayChoices();
            obtainChoice();
            processChoice();
        } while(choice < choices.size());
    }

    public void addChoice(String title, Runnable run) {
        choices.set(title, run);
    }

    protected void displayChoices() {
        for(int i = 0; i < choices.size(); i++) {
            System.out.print((i + 1) + ") " + choices.getByIndex(i).getKey());
            if(choices.getByIndex(i).getValue() instanceof  Menu)
                System.out.println(" (sub-menu)");
            else
                System.out.println();
        }
        System.out.println((choices.size() + 1) + ") Exit");
    }

    protected void obtainChoice() {
        do {
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.next()) - 1;
        } while(choice < 0 || choice > choices.size());
    }

    protected void processChoice() {
        if(choice < choices.size()) {
            choices.getByIndex(choice).getValue().run();
        }
    }
}
