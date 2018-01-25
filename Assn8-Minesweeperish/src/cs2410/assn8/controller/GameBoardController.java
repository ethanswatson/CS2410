package cs2410.assn8.controller;

import cs2410.assn8.component.Board;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class GameBoardController {
    @FXML
    private TilePane headerBar;

    @FXML
    private HeaderBarController headerbarController;

    @FXML
    private Pane pane;

    public void setGrid(Board board) {
        pane.getChildren().add(board);
    }

    public HeaderBarController getHeaderBarController() {
        return headerbarController;
    }
}
