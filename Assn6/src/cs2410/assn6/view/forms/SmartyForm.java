package cs2410.assn6.view.forms;

/**
 * Form for adding a Smarty
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public abstract class SmartyForm extends Form {
    /**
     * IQ field
     */
    protected LabeledTextField iqField;

    /**
     * Constructor
     */
    public SmartyForm() {
        iqField = new LabeledTextField("IQ:");
        fieldPane.getChildren().add(iqField);
    }

    /**
     * Event handler for save button. To be implemented in child classes.
     */
    @Override
    protected abstract void save();
}
