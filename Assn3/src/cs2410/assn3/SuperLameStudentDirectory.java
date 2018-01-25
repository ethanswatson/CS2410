package cs2410.assn3;

import cs2410.assn3.directory.Directory;

/**
 * Parent class for CommandDirectory and GUIDirectory containing common run method
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public abstract class SuperLameStudentDirectory {
    /**
     * The directory to be used
     */
    protected Directory directory;

    /**
     * Flag to quit program
     */
    protected boolean quit = false;

    /**
     * Runs the program
     */
    protected void run() {
        while (!quit) {
            int selection = showMenu();

            if (quit) {
                break;
            }

            switch (selection) {
                case 1:
                    listDirectory();
                    break;
                case 2:
                    addPerson();
                    break;
                case 3:
                    listAverageAge();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    invalidInput();
            }
        }

        quit();
    }

    /**
     * Display menu and get selection
     * @return User's menu selection
     */
    protected abstract int showMenu();

    /**
     * Shows directory
     */
    protected abstract void listDirectory();

    /**
     * Adds a person to the directory
     */
    protected abstract void addPerson();

    /**
     * Displays the average age from the directory
     */
    protected abstract void listAverageAge();

    /**
     * Displays an error message if an invalid menu selection is entered
     */
    protected abstract void invalidInput();

    /**
     * Closes program and saves directory to file
     */
    protected abstract void quit();
}
