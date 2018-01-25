package cs2410.assn7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

/**
 * The driver for the program
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class GUI extends Application {
    /**
     * The video player
     */
    private Player player;

    /**
     * The menu bar
     */
    private CustomMenuBar menuBar;

    /**
     * Run the program
     * @param primaryStage stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        player = new Player();
        menuBar = new CustomMenuBar(this::updateVideo, primaryStage);
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(menuBar);
        mainPane.setCenter(player);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Updates the player with a new video
     * @param file the video player
     */
    private void updateVideo(File file) {
        player.updateVideo(file);
    }
}
