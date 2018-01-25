package cs2410.assn6.view.forms;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class LabeledTextField extends HBox {
    /**
     * Default spacing between label and textfield
     */
    private static int DEFAULT_SPACING = 10;

    /**
     * Label for the field
     */
    private Label label;

    /**
     * The field
     */
    private TextField textField;

    /**
     * Constructor
     * @param label text for the label
     */
    public LabeledTextField(String label) {
        this.label = new Label(label);
        textField = new TextField();
        setSpacing(DEFAULT_SPACING);
        getChildren().addAll(this.label, textField);
    }

    /**
     * gets the contents of the TextField
     * @return the contents of the text field
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Clears text from the field
     */
    public void clear() {
        textField.clear();
    }

//    /**
//     * Get width of component
//     * @return width of component
//     */
//    public double getSize() {
//        return label.getWidth() + textField.getWidth();
//    }
//
//    /**
//     * Sets the width of the field
//     * @param width width to set
//     */
//    public void setWidth(double width) {
//        double difference = width - getSize();
//        setSpacing(DEFAULT_SPACING + difference);
//    }
}
