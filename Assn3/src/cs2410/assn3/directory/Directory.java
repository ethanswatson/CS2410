package cs2410.assn3.directory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds the Student directory and operations relating to it
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Directory {
    /**
     * The location of the data file
     */
    private static final String filePath = "data/directory.txt";

    /**
     * List of all students in the directory
     */
    private ArrayList<Student> students;

    /**
     * Scanner that will read info from the file
     */
    private Scanner fileInput;

    /**
     * PrintWriter that will write data to the file
     */
    private PrintWriter fileOutput;

    /**
     * Constructs directory object and loads from file
     */
    public Directory() {
        students = new ArrayList<>();

        try {
            fileInput = new Scanner(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        loadDirectory();
    }

    /**
     * Adds a student to the directory
     * @param firstName first name
     * @param lastName last name
     * @param age age
     * @param major major code
     * @param id id number
     */
    public void addStudent(String firstName, String lastName, int age, String major, String id) {
        students.add(new Student(firstName, lastName, age, major, id));
    }


    /**
     * Calculates the average student age
     * @return the average age of all students in the directory
     */
    public double getAverageAge() {
        int sum = 0;
        for (int i = 0; i < students.size(); i++) {
            sum += students.get(i).getAge();
        }

        double average = (double) sum / students.size();
        // Round to one decimal place
        average = Math.round(average * 10) / 10.0;
        return average;
    }

    /**
     * Represent Directory as a string
     * @return directory string
     */
    @Override
    public String toString() {
        String result = "";
        result += String.format("%-15s %-15s %8s %-6s %-8s\n", "First Name", "Last Name", "Age", "Major", "ID");
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            result += String.format("%-15s %-15s %8d %-6s %-8s\n", current.getFirstName(), current.getLastName(),
                    current.getAge(), current.getMajor(), current.getId());
        }
        return result;
    }

    /**
     * Creates a menu for the program
     * @return The menu
     */
    public String getMenu() {
        String menu = "Directory Menu\n";
        menu += "(1) List Directory\n";
        menu += "(2) Add Person\n";
        menu += "(3) List Average Age\n";
        menu += "(4) Quit Program\n";
        menu += "Enter Selection (1-4)";

        return menu;
    }

    /**
     * Saves student directory to file
     */
    public void saveDirectory() {
        try {
            fileOutput = new PrintWriter(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            fileOutput.print(current.getFirstName() + " " + current.getLastName() + " " + current.getAge() + " " +
                             current.getMajor() + " " + current.getId());

            // Add newline if not last element
            if (i != students.size() - 1) {
                fileOutput.println();
            }
        }

        fileOutput.close();
    }

    /**
     * Loads student directory from file
     */
    private void loadDirectory() {
        while (fileInput.hasNextLine()) {
            String firstName = fileInput.next();
            String lastName = fileInput.next();
            int age = Integer.parseInt(fileInput.next());
            String major = fileInput.next();
            String id = fileInput.next();

            students.add(new Student(firstName, lastName, age, major, id));
        }

        fileInput.close();
    }
}
