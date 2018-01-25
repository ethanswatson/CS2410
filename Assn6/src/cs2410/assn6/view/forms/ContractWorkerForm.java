package cs2410.assn6.view.forms;

import cs2410.assn6.control.Controller;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class ContractWorkerForm extends SmartyForm {
    /**
     * Contracts Field
     */
    private LabeledTextField contractsField;

    /**
     * Pay field
     */
    private LabeledTextField payField;

    /**
     * Controller for accessing data
     */
    private Controller controller;

    /**
     * Constructor
     * @param controller controller to use
     */
    public ContractWorkerForm(Controller controller) {
        this.controller = controller;

        contractsField = new LabeledTextField("Contracts:");
        payField = new LabeledTextField("Pay Per Contract:");

        fieldPane.getChildren().addAll(contractsField, payField);

//         setFieldSize();
    }

    /**
     * Event Handler for save button
     */
    @Override
    protected void save() {
        String name = nameField.getText();
        String math = mathField.getText();
        String says = sayField.getText();
        int iq = Integer.parseInt(iqField.getText());
        int contracts = Integer.parseInt(contractsField.getText());
        double pay = Double.parseDouble(payField.getText());

        controller.createNewContractWorker(name, math, says, iq, contracts, pay);
    }
}
