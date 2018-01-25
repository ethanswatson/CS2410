package cs2410.assn8.view;

import cs2410.assn8.component.Board;
import cs2410.assn8.controller.GameBoardController;
import cs2410.assn8.controller.HeaderBarController;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Main extends Application {
    private GameBoardController gameBoardController;
    private HeaderBarController headerBarController;
    private IntegerProperty numBombsRemainingProperty;
    private IntegerProperty timeProperty;
    private Board board;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cs2410/assn8/view/gameboard.fxml"));
        Parent root = loader.load();
        gameBoardController = loader.getController();
        initGame();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Minesweeperish");
        primaryStage.setResizable(false);
        primaryStage.show();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Features");
        alert.setHeaderText(null);
        alert.setContentText("i. 15 pts - Sound Feature. Fully Functional");
        alert.showAndWait();

        primaryStage.setOnCloseRequest(event -> {
            board.cancelTimer();
        });
    }

    private void initGame() {
        board = new Board();
        numBombsRemainingProperty = board.getNumBombsRemainingProperty();
        timeProperty = board.getTimeProperty();
        gameBoardController.setGrid(board);
        headerBarController = gameBoardController.getHeaderBarController();
        headerBarController.setBombLabelProperty(numBombsRemainingProperty);
        headerBarController.setTimeLabelProperty(timeProperty);
        headerBarController.setStartBtnEventHandler(e -> {
            board.reset();
        });
    }
}
