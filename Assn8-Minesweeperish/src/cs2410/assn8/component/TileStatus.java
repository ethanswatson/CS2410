package cs2410.assn8.component;

import cs2410.assn8.config.Colors;
import cs2410.assn8.config.Images;
import javafx.scene.image.Image;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public enum TileStatus {
    UNREVEALED(null, Colors.UNTURNED_COLOR),
    BLANK(null, Colors.TURNED_COLOR),
    FLAGGED(Images.FLAG_IMAGE, Colors.UNTURNED_COLOR),
    CORRECT_FLAG(Images.BOMB_IMAGE, Colors.CORRECT_FLAG_COLOR),
    INCORRECT_FLAG(Images.BOMB_IMAGE, Colors.INCORRECT_FLAG_COLOR),
    POTENTIAL(Images.QUESTION_IMAGE, Colors.UNTURNED_COLOR),
    ONE(Images.ONE_IMAGE, Colors.TURNED_COLOR),
    TWO(Images.TWO_IMAGE, Colors.TURNED_COLOR),
    THREE(Images.THREE_IMAGE, Colors.TURNED_COLOR),
    FOUR(Images.FOUR_IMAGE, Colors.TURNED_COLOR),
    FIVE(Images.FIVE_IMAGE, Colors.TURNED_COLOR),
    SIX(Images.SIX_IMAGE, Colors.TURNED_COLOR),
    SEVEN(Images.SEVEN_IMAGE, Colors.TURNED_COLOR),
    EIGHT(Images.EIGHT_IMAGE, Colors.TURNED_COLOR),
    BOMB(Images.BOMB_IMAGE, Colors.BOMB_COLOR);

    private Image display;
    private String backgroundColor;

    TileStatus(Image display, String backgroundColor) {
        this.display = display;
        this.backgroundColor = backgroundColor;
    }

    public Image getDisplay() {
        return display;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }
}
