package cs2410.assn6.view;

import cs2410.assn6.control.Controller;
import cs2410.assn6.view.forms.ContractWorkerForm;
import cs2410.assn6.view.forms.HobbitForm;
import cs2410.assn6.view.forms.HourlyWorkerForm;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The main GUI
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class GUI extends Application {
    private Controller controller;
    private ToolBar toolBar;
    private BorderPane mainPane;
    private HobbitForm hobbitForm;
    private HourlyWorkerForm hourlyWorkerForm;
    private ContractWorkerForm contractWorkerForm;
    private StackPane displayPane;
    private Text displayText;



    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        toolBar = new ToolBar(this::update);
        mainPane = new BorderPane();
        hobbitForm = new HobbitForm(controller);
        hourlyWorkerForm = new HourlyWorkerForm(controller);
        contractWorkerForm = new ContractWorkerForm(controller);
        mainPane.setPrefSize(400, 300);
        BorderPane.setAlignment(toolBar, Pos.CENTER);
        BorderPane.setMargin(toolBar, new Insets(5, 20, 5, 55));
        mainPane.setTop(toolBar);
        displayPane = new StackPane();
        displayText = new Text();
        StackPane.setAlignment(displayText, Pos.CENTER);
        displayPane.getChildren().add(displayText);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update(DisplayOption option) {
        switch (option) {
            case HOURLY_WORKER:
                mainPane.setCenter(hourlyWorkerForm);
                break;
            case CONTRACT_WORKER:
                mainPane.setCenter(contractWorkerForm);
                break;
            case HOBBIT:
                mainPane.setCenter(hobbitForm);
                break;
            case MATH:
                displayText.setText(controller.getMath());
                mainPane.setCenter(displayPane);
                break;
            case INCOME:
                displayText.setText(controller.getIncome());
                mainPane.setCenter(displayPane);
                break;
            case HOURS:
                displayText.setText(controller.getHours());
                mainPane.setCenter(displayPane);
                break;
            case IQ:
                displayText.setText(controller.getIQ());
                mainPane.setCenter(displayPane);
                break;
            case SAY:
                displayText.setText(controller.getSay());
                mainPane.setCenter(displayPane);
                break;
            case CARROTS:
                displayText.setText(controller.getCarrots());
                mainPane.setCenter(displayPane);
                break;
            case CONTRACTS:
                displayText.setText(controller.getContracts());
                mainPane.setCenter(displayPane);
                break;
        }
    }
}
