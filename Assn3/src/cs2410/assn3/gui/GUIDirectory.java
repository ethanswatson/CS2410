package cs2410.assn3.gui;

import cs2410.assn3.SuperLameStudentDirectory;
import cs2410.assn3.directory.Directory;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Runs program in GUI mode
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class GUIDirectory extends SuperLameStudentDirectory {
    /**
     * Input dialog box for addPerson
     */
    TextInputDialog addPersonInput;

    /**
     * Input dialog box for menu
     */
    TextInputDialog menuInput;

    /**
     * Directory dialog box
     */
    Alert dir;

    /**
     * Age Alert box
     */
    Alert age;

    /**
     * Error dialog box
     */
    Alert error;

    /**
     * Constructs a GUIDialog object and runs the program
     */
    public GUIDirectory() {
        directory = new Directory();
        addPersonInput = new TextInputDialog();
        addPersonInput.setHeaderText(null);
        addPersonInput.setOnCloseRequest(e -> {
            addPersonInput.close();
        });
        menuInput = new TextInputDialog();
        menuInput.setHeaderText(null);
        dir = new Alert(Alert.AlertType.INFORMATION);
        dir.setHeaderText(null);
        dir.getDialogPane().getStylesheets().add("resources/custom.css");
        dir.getDialogPane().setPrefSize(600, 300);
        age = new Alert(Alert.AlertType.INFORMATION);
        error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Error");
        run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int showMenu() {
        menuInput.setTitle("Menu");
        menuInput.setContentText(directory.getMenu());
        Optional<String> selection = menuInput.showAndWait();
        menuInput.getEditor().clear();

        if (selection.isPresent()) {
            return Integer.parseInt(selection.get());
        } else {
            quit = true;
            return -1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void listDirectory() {
        dir.setTitle("Directory");
        dir.setContentText(directory.toString());
        dir.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addPerson() {
        boolean close = false;
        addPersonInput.setTitle("Add person");
        addPersonInput.setContentText("First Name: ");
        Optional<String> fName = addPersonInput.showAndWait();
        addPersonInput.getEditor().clear();
        addPersonInput.setContentText("Last Name: ");
        Optional<String> lName = addPersonInput.showAndWait();
        addPersonInput.getEditor().clear();
        addPersonInput.setContentText("Age: ");
        Optional<String> ageStr = addPersonInput.showAndWait();
        addPersonInput.getEditor().clear();
        addPersonInput.setContentText("Major Code: ");
        Optional<String> major = addPersonInput.showAndWait();
        addPersonInput.getEditor().clear();
        addPersonInput.setContentText("ID Number: ");
        Optional<String> id = addPersonInput.showAndWait();
        addPersonInput.getEditor().clear();

        if (fName.isPresent() && lName.isPresent() && ageStr.isPresent() && major.isPresent() && id.isPresent()) {
            int age = Integer.parseInt(ageStr.get());
            directory.addStudent(fName.get(), lName.get(), age, major.get(), id.get());
            addPersonInput.close();
        } else {
            error.setHeaderText("You left one or more fields blank!");
            error.showAndWait();
            addPersonInput.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void listAverageAge() {
        age.setTitle("Average Age");
        age.setHeaderText("The average age is: " + directory.getAverageAge());
        age.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void invalidInput() {
        error.setHeaderText("Invalid input. Try again");
        error.showAndWait();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void quit() {
        directory.saveDirectory();
    }
}
