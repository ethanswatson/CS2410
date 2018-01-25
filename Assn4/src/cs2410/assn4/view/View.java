package cs2410.assn4.view;

import cs2410.assn4.controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * The GUI
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class View extends Application {
    /**
     * Y pos of buttons
     */
    private static final double BUTTON_Y = 433.125;

    /**
     * Width of buttons
     */
    private static final double BUTTON_WIDTH = 67.5;

    /**
     * Height of buttons
     */
    private static final double BUTTON_HEIGHT = 33.75;

    /**
     * Controller object for generating images
     */
    private Controller controller;

    /**
     * The main scene
     */
    private Scene scene;

    /**
     * Pane to contain all of our elements
     */
    private Pane pane;

    /**
     * ImageView to show the images
     */
    private ImageView imageView;

    /**
     * Label to show the captions
     */
    private Label caption;

    /**
     * Button to go to the previous image
     */
    private Button prevButton;

    /**
     * Button to add a new image
     */
    private Button addButton;

    /**
     * Button to delete an image
     */
    private Button delButton;

    /**
     * Button to go to next image
     */
    private Button nextButton;

    /**
     * Dialog box to get user input
     */
    private TextInputDialog input;

    /**
     * Alert to show error messages
     */
    private Alert alert;

    /**
     * Constructor
     */
    public View() {
        controller = new Controller();
        pane = new Pane();
        setUpDialogs();
        setUpView();
        setUpCaption();
        setUpButtons();
        pane.getChildren().addAll(caption, imageView, prevButton, addButton, delButton, nextButton);
        scene = new Scene(pane, 700, 500);
    }

    /**
     * Sets up dialog boxes
     */
    private void setUpDialogs() {
        input = new TextInputDialog();
        input.setTitle("Add Image");
        input.setHeaderText(null);
        alert = new Alert(Alert.AlertType.ERROR);
    }

    /**
     * Sets up the label containing the caption
     */
    private void setUpCaption() {
        caption = new Label(controller.getCaption());
        caption.setTextAlignment(TextAlignment.CENTER);
        caption.setAlignment(Pos.CENTER);
        caption.setStyle("-fx-font: 24 arial");
        caption.setPrefSize(700, 50);

    }

    /**
     * Sets up the ImageView and sets it to the first image in the list
     */
    private void setUpView() {
        imageView = new ImageView();
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);

        imageView.setLayoutY(50);
        imageView.setImage(controller.currentImage());
    }

    /**
     * Sets up the buttons, including their event handlers
     */
    private void setUpButtons() {
        prevButton = new Button("Prev");
        addButton = new Button("Add");
        delButton = new Button("Del");
        nextButton = new Button("Next");

        prevButton.setLayoutX(200);
        prevButton.setLayoutY(BUTTON_Y);
        prevButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        prevButton.setOnAction(this::prevImage);

        addButton.setLayoutX(277.5);
        addButton.setLayoutY(BUTTON_Y);
        addButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        addButton.setOnAction(this::addImage);

        delButton.setLayoutX(355);
        delButton.setLayoutY(BUTTON_Y);
        delButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        delButton.setOnAction(this::delImage);

        nextButton.setLayoutX(432.5);
        nextButton.setLayoutY(BUTTON_Y);
        nextButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        nextButton.setOnAction(this::nextImage);

        if (caption.getText().equals("")){
            enableButtons(false);
        }
    }

    /**
     * Event handler for prevButton
     * @param event The event
     */
    private void prevImage(ActionEvent event) {
        imageView.setImage(controller.prevImage());
        caption.setText(controller.getCaption());
    }

    /**
     * Event handler for addButton
     * @param event The event
     */
    private void addImage(ActionEvent event) {
        input.setContentText("Enter a URL:");
        Optional<String> url = input.showAndWait();
        input.getEditor().clear();
        if (url.isPresent()) {
            input.setContentText("Enter a caption: ");
            Optional<String> capt = input.showAndWait();
            input.getEditor().clear();

            if (capt.isPresent()) {
                imageView.setImage(controller.addImage(url.get(), capt.get()));
                caption.setText(capt.get());
                enableButtons(true);
                return;
            }
        }
        alert.setHeaderText("No image added.");
        alert.showAndWait();
    }

    /**
     * Event handler for delButton
     * @param event The event
     */
    private void delImage(ActionEvent event) {
        imageView.setImage(controller.delImage());
        caption.setText(controller.getCaption());

        if (controller.getCaption().equals("")) {
            enableButtons(false);
        }
    }

    /**
     * Event handler for nextButton
     * @param event The event
     */
    private void nextImage(ActionEvent event) {
        imageView.setImage(controller.nextImage());
        caption.setText(controller.getCaption());
    }

    /**
     * Enables or disables buttons
     * @param enabled true for buttons enabled, false for buttons disabled
     */
    private void enableButtons(boolean enabled) {
        prevButton.setDisable(!enabled);
        delButton.setDisable(!enabled);
        nextButton.setDisable(!enabled);
    }


    /**
     * Start method to launch GUI
     * @param primaryStage Primary Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new View();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Viewer");
        primaryStage.setOnCloseRequest(event -> {controller.quit();});
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
