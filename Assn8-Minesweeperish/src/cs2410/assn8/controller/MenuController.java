package cs2410.assn8.controller;

import cs2410.assn8.config.GameSettings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class MenuController {
    @FXML
    private CheckMenuItem audioMenuItem;

    @FXML
    public void updateAudio(ActionEvent event) {
        if (audioMenuItem.isSelected()) {
            GameSettings.audio = true;
        } else {
            GameSettings.audio = false;
        }
    }
}
