package cs2410.assn6.view.forms;

import cs2410.assn6.control.Controller;

/**
 * Form for adding a Hobbit
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class HobbitForm extends Form {
    /**
     * Carrot field
     */
    private LabeledTextField carrotField;

    /**
     * Controller for accessing data
     */
    private Controller controller;

    /**
     * Constructor
     * @param controller controller to use
     */
    public HobbitForm(Controller controller) {
        this.controller = controller;

        carrotField = new LabeledTextField("Carrots:");

        fieldPane.getChildren().add(carrotField);

//         setFieldSize();
    }

    /**
     * Event handler for save button
     */
    @Override
    protected void save() {
        String name = nameField.getText();
        String math = mathField.getText();
        String says = sayField.getText();
        int carrots = Integer.parseInt(carrotField.getText());

        controller.createNewHobbit(name, math, says, carrots);
    }
}
