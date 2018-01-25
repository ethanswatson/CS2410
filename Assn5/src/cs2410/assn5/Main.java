package cs2410.assn5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Main extends Application {
    private Pane drawPane;
    ToolPane toolPane;
    Rectangle tmpRect;
    double tmpX;
    double tmpY;
    double origX;
    double origY;
    Ellipse tmpEll;
    Path tmpPath;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane mainPane = new Pane();
        toolPane = new ToolPane();
        drawPane = new Pane();
        drawPane.setPrefSize(500, 500);
        drawPane.setLayoutX(0);
        drawPane.setLayoutY(50);
        initDrawPane(drawPane);
        mainPane.getChildren().addAll(toolPane, drawPane);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDrawPane(Pane pane) {
        pane.setOnMousePressed(event -> {
            tmpX = event.getX();
            tmpY = event.getY();
            if (!toolPane.editBtnSelected()) {
                if (toolPane.rectBtnSelected()) {
                    tmpRect = new Rectangle();
                    tmpRect.setX(tmpX);
                    tmpRect.setY(tmpY);
                    tmpRect.setStroke(toolPane.getStrokePickerValue());
                    tmpRect.setFill(toolPane.getFillPickerValue());
                    tmpRect.setStrokeWidth(toolPane.getStrokeSizeValue());
                    initShape(tmpRect);
                    pane.getChildren().add(tmpRect);
                } else if (toolPane.ellBtnSelected()) {
                    tmpEll = new Ellipse();
                    tmpEll.setStroke(toolPane.getStrokePickerValue());
                    tmpEll.setFill(toolPane.getFillPickerValue());
                    tmpEll.setStrokeWidth(toolPane.getStrokeSizeValue());
                    initShape(tmpEll);
                    pane.getChildren().add(tmpEll);
                } else if (toolPane.freeBtnSelected()) {
                    tmpPath = new Path();
                    tmpPath.setStroke(toolPane.getStrokePickerValue());
                    tmpPath.setStrokeWidth(toolPane.getStrokeSizeValue());
                    tmpPath.getElements().add(new MoveTo(tmpX, tmpY));
                    initShape(tmpPath);
                    pane.getChildren().add(tmpPath);
                }
            }
        });

        pane.setOnMouseDragged(event -> {
            double width = event.getX() - tmpX;
            double height = event.getY() - tmpY;
            if (toolPane.rectBtnSelected()) {
                tmpRect.setWidth(width);
                tmpRect.setHeight(height);
            } else if (toolPane.ellBtnSelected()) {

                tmpEll.setCenterX(width / 2 + tmpX);
                tmpEll.setCenterY(height / 2 + tmpY);
                tmpEll.setRadiusX(width / 2);
                tmpEll.setRadiusY(height / 2);
            } else if (toolPane.freeBtnSelected()) {
                tmpPath.getElements().add(new LineTo(event.getX(), event.getY()));
            }
        });

        pane.setOnMouseReleased(event -> {
            if (toolPane.rectBtnSelected()) {
                tmpRect = null;
            } else if (toolPane.ellBtnSelected()) {
                tmpEll = null;
            } else if (toolPane.freeBtnSelected()) {
                tmpPath = null;
            }
        });
    }

    private void initShape(Shape shape) {
        shape.setOnMousePressed(event -> {
            origX = event.getX();
            origY = event.getY();
            toolPane.setFillPickerValue((Color) shape.getFill());
            toolPane.setStrokePickerValue((Color) shape.getStroke());

            if (toolPane.eraseBtnSelected()) {
                drawPane.getChildren().remove(shape);
            }
        });

        shape.setOnMouseDragged(event -> {
            if (toolPane.editBtnSelected()) {
                shape.setTranslateX(shape.getTranslateX() + event.getX() - origX);
                shape.setTranslateY(shape.getTranslateY() + event.getY() - origY);
            }
        });
    }
}
