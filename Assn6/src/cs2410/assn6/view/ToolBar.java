package cs2410.assn6.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 *  Tool Bar for the GUI
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class ToolBar extends HBox {
    /**
     * ComboBox containing options of what to diaplay
     */
    private ComboBox<DisplayOption> comboBox;

    /**
     * Button for adding an hourly worker
     */
    private Button hourlyButton;

    /**
     * Button for adding a contract worker
     */
    private Button contractButton;

    /**
     * Button for adding a hobbit
     */
    private Button hobbitButton;

    /**
     * Callback for updating other parts of GUI when ToolBar is changed
     */
    private Callback update;

    /**
     * Screen to display
     */
    private DisplayOption toDisplay;

    /**
     * Constructor
     * @param update callback for updating other GUI components
     */
    public ToolBar(Callback update) {
        this.update = update;
        toDisplay = DisplayOption.NONE;
        initComboBox();
        initButtons();

        setSpacing(5);
        getChildren().addAll(comboBox, hourlyButton, contractButton, hobbitButton);
    }

    /**
     * Helper method to initialize combo box
     */
    private void initComboBox() {
        ObservableList<DisplayOption> options = FXCollections.observableArrayList(
                DisplayOption.MATH,
                DisplayOption.INCOME,
                DisplayOption.HOURS,
                DisplayOption.IQ,
                DisplayOption.SAY,
                DisplayOption.CARROTS,
                DisplayOption.CONTRACTS
        );

        comboBox = new ComboBox<>(options);

        comboBox.setOnAction(event -> {
            toDisplay = comboBox.getValue();
            if (toDisplay != null) {
                update.callback(toDisplay);
            }
        });
    }

    /**
     * Helper method to initialize buttons
     */
    private void initButtons() {
        hourlyButton = new Button("Hourly");
        contractButton = new Button("Contract");
        hobbitButton = new Button("Hobbit");

        hourlyButton.setOnAction(event -> {
            toDisplay = DisplayOption.HOURLY_WORKER;
            update.callback(toDisplay);
            comboBox.getSelectionModel().clearSelection();
        });

        contractButton.setOnAction(event -> {
            toDisplay = DisplayOption.CONTRACT_WORKER;
            update.callback(toDisplay);
            comboBox.getSelectionModel().clearSelection();
        });

        hobbitButton.setOnAction(event -> {
            toDisplay = DisplayOption.HOBBIT;
            update.callback(toDisplay);
            comboBox.getSelectionModel().clearSelection();
        });
    }
}
