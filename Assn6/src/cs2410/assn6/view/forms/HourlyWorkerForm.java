package cs2410.assn6.view.forms;

import cs2410.assn6.control.Controller;

/**
 * Form for adding an hourly worker
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class HourlyWorkerForm extends SmartyForm {
    /**
     * Hours field
     */
    private LabeledTextField hoursField;

    /**
     * Wage field
     */
    private LabeledTextField wageField;

    /**
     * Controller for accessing data
     */
    private Controller controller;

    /**
     * Constructor
     * @param controller controller to use
     */
    public HourlyWorkerForm(Controller controller) {
        this.controller = controller;

        hoursField = new LabeledTextField("Hours:");
        wageField = new LabeledTextField("Wage:");

        fieldPane.getChildren().addAll(hoursField, wageField);

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
        int iq = Integer.parseInt(iqField.getText());
        int hours = Integer.parseInt(hoursField.getText());
        double wage = Double.parseDouble(wageField.getText());

        controller.createNewHourlyWorker(name, math, says, iq, hours, wage);
    }
}
