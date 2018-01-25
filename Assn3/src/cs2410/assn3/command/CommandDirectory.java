package cs2410.assn3.command;

import cs2410.assn3.SuperLameStudentDirectory;
import cs2410.assn3.directory.Directory;

import java.util.Scanner;

/**
 * Run command line program
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class CommandDirectory extends SuperLameStudentDirectory{
    /**
     * Scanner to get input from console
     */
    private Scanner inputScanner;

    /**
     * Constructs CommandDirectory and runs program
     */
    public CommandDirectory() {
        directory = new Directory();
        inputScanner = new Scanner(System.in);

        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int showMenu() {
        System.out.print(directory.getMenu());
        System.out.print(": ");
        return inputScanner.nextInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void listDirectory() {
        System.out.println(directory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addPerson() {
        System.out.print("First Name: ");
        String firstName = inputScanner.next();
        System.out.print("Last Name: ");
        String lastName = inputScanner.next();
        System.out.print("Age: ");
        int age = inputScanner.nextInt();
        System.out.print("Major Code: ");
        String major = inputScanner.next();
        System.out.print("ID Number: ");
        String id = inputScanner.next();

        directory.addStudent(firstName, lastName, age, major, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void listAverageAge() {
        System.out.println("The average age is " + directory.getAverageAge());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void invalidInput() {
        System.out.println("Invalid input. Try again");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void quit() {
        System.out.println("Quitting");
        directory.saveDirectory();
    }

}
