package cs2410.assn6.view.forms;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * An abstract field class to be inherited by specific fields
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public abstract class Form extends BorderPane {
    /**
     * Name field
     */
    protected LabeledTextField nameField;

    /**
     * Math field
     */
    protected LabeledTextField mathField;

    /**
     * Say field
     */
    protected LabeledTextField sayField;

    /**
     * Cancel button
     */
    private Button cancelButton;

    /**
     * Save button
     */
    private Button saveButton;

    /**
     * VBox to contain all fields
     */
    protected VBox fieldPane;

    /**
     * Confirmation dialog box
     */
    private Alert confirmationDialog;

    /**
     * Constructor
     */
    public Form() {
        nameField = new LabeledTextField("Name:");
        mathField = new LabeledTextField("Math:");
        sayField = new LabeledTextField("Say:");
        fieldPane = new VBox();
        fieldPane.getChildren().addAll(nameField, mathField, sayField);
        initButtons();
        HBox buttonPane = new HBox();
        buttonPane.setSpacing(5);
        buttonPane.getChildren().addAll(cancelButton, saveButton);
        AnchorPane buttonAnchor = new AnchorPane();
        AnchorPane.setRightAnchor(buttonPane, 10.0);
        AnchorPane.setBottomAnchor(buttonPane, 10.0);
        buttonAnchor.getChildren().add(buttonPane);

        setCenter(fieldPane);
        setBottom(buttonAnchor);

        confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText("Confirmation");
    }

    /**
     * Helper method to set up buttons
     */
    private void initButtons() {
        cancelButton = new Button("Cancel");
        saveButton = new Button("Save");

        cancelButton.setOnAction(this::clear);
        saveButton.setOnAction(event -> {
            String name = nameField.getText();
            confirmationDialog.setContentText("You are about to add " + name);
            Optional<ButtonType> selection = confirmationDialog.showAndWait();
            if (selection.get() == ButtonType.OK) {
                save();
                clear(event);
            }
        });
    }

    /**
     * Event handler for cancel button
     * @param event the event
     */
    private void clear(ActionEvent event) {
        for (Node node : fieldPane.getChildren()) {
            if (node instanceof LabeledTextField) {
                ((LabeledTextField) node).clear();
            }
        }
    }

    /**
     * Event handler for save button. To be implemented in child classes.
     */
    protected abstract void save();

    //    /**
//     * Gets the width of the largest field
//     * @return the width of the largest field
//     */
//    private double getLargestFieldWidth() {
//        double largest = 0;
//        for (Node node : fieldPane.getChildren()) {
//            if (node instanceof LabeledTextField) {
//                double tmp = ((LabeledTextField) node).getSize();
//                if (tmp > largest) {
//                    largest = tmp;
//                }
//            }
//        }
//
//        return largest;
//    }
//
//    /**
//     * Sets all fields to same width
//     */
//    protected void setFieldSize() {
//        double width = getLargestFieldWidth();
//        for (Node node : fieldPane.getChildren()) {
//            if (node instanceof LabeledTextField) {
//                ((LabeledTextField) node).setWidth(width);
//            }
//        }
//    }
}
