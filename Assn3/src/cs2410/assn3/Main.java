package cs2410.assn3;

import cs2410.assn3.command.CommandDirectory;
import cs2410.assn3.gui.GUIDirectory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Main extends Application{
    public static void main(String[] args) {
        int selection = menu();

        switch (selection) {
            case 1:
                // Launch Command Line
                CommandDirectory cd = new CommandDirectory();
                System.exit(0);
            case 2:
                // Launch GUI
                launch(args);
                break;
        }
    }

    public static int menu() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Welcome to the Super Lame Student Directory");
        System.out.println("Would you like to run the program as:");
        System.out.println("(1) Command Line");
        System.out.println("(2) GUI");
        System.out.print("Enter Selection (1-2): ");

        return inputScanner.nextInt();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIDirectory gd = new GUIDirectory();
    }
}
