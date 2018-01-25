package cs2410.assn8.controller;

import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class HeaderBarController {
    @FXML
    private Button startBtn;

    @FXML
    private Label bombLabel;

    @FXML
    private Label timeLabel;

    public void setBombLabelProperty(IntegerProperty numBombs) {
        bombLabel.textProperty().bindBidirectional(numBombs, new NumberStringConverter());
    }

    public void setTimeLabelProperty(IntegerProperty time) {
        timeLabel.textProperty().bindBidirectional(time, new NumberStringConverter());
    }

    public void setStartBtnEventHandler(EventHandler<ActionEvent> event) {
        startBtn.setOnAction(event);
    }
}
