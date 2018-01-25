package cs2410.assn7;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * The menu bar for the program
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class CustomMenuBar extends MenuBar {
    /**
     * The File menu
     */
    private Menu file;

    /**
     * The Help menu
     */
    private Menu help;

    /**
     * The open MenuItem
     */
    private MenuItem open;

    /**
     * The close MenuItem
     */
    private MenuItem close;

    /**
     * The exit MenuItem
     */
    private MenuItem exit;

    /**
     * /**
     * The documentation MenuItem
     */
    private MenuItem documentation;

    /**
     * The about MenuItem
     */
    private MenuItem about;

    /**
     * FileChooser for picking video
     */
    private FileChooser fileChooser;

    /**
     * Callback for updating player
     */
    private Callback update;

    /**
     * The primaryStage
     */
    private Stage primaryStage;

    /**
     * constructor
     * @param callback update callback
     * @param stage primaryStage for FileChooser
     */
    public CustomMenuBar(Callback callback, Stage stage) {
        update = callback;
        primaryStage = stage;
        initFileChooser();
        initFileMenu();
        initHelpMenu();
        getMenus().addAll(file, help);
    }

    /**
     * Initialize FileChooser
     */
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Select a video to play...");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4"));
    }

    /**
     * Initialize The File Menu
     */
    private void initFileMenu() {
        file = new Menu("File");
        open = new MenuItem("Open");
        close = new MenuItem("Close");
        exit = new MenuItem("Exit");

        file.getItems().addAll(open, close, exit);

        open.setOnAction(this::openFile);
        close.setOnAction(event -> {
            // Update the video player to nothing
            update.execute(null);
        });
        exit.setOnAction(event -> {
            System.exit(0);
        });
    }

    /**
     * Initialize the help meni
     */
    private void initHelpMenu() {
        help = new Menu("Help");
        documentation = new MenuItem("Documentation");
        about = new MenuItem("About");
        help.getItems().addAll(documentation, about);
    }

    /**
     * Open a new file
     * @param event the action event
     */
     private void openFile(ActionEvent event) {
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        update.execute(selectedFile);
    }

}
