package cs2410.assn8.config;

import javafx.scene.image.Image;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Images {
    private Images() {}

    private static final String PREFIX = "file:data/images/";

    public static final Image ONE_IMAGE = new Image(PREFIX + "1.png");
    public static final Image TWO_IMAGE = new Image(PREFIX + "2.png");
    public static final Image THREE_IMAGE = new Image(PREFIX + "3.png");
    public static final Image FOUR_IMAGE = new Image(PREFIX + "4.png");
    public static final Image FIVE_IMAGE = new Image(PREFIX + "5.png");
    public static final Image SIX_IMAGE = new Image(PREFIX + "6.png");
    public static final Image SEVEN_IMAGE = new Image(PREFIX + "7.png");
    public static final Image EIGHT_IMAGE = new Image(PREFIX + "8.png");

    public static final Image FLAG_IMAGE = new Image(PREFIX + "flag.png");
    public static final Image QUESTION_IMAGE = new Image(PREFIX + "question.png");
    public static final Image BOMB_IMAGE = new Image(PREFIX + "bomb.png");
}
