package cs2410.assn8.config;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Sounds {
    private Sounds() {}

    private static final String PREFIX = "data/audio/";

    public static final MediaPlayer BOOM = new MediaPlayer(new Media(new File(PREFIX + "boom.mp3").toURI().toString()));
    public static final MediaPlayer CLICK = new MediaPlayer(new Media(new File(PREFIX + "click.mp3").toURI().toString()));
    public static final MediaPlayer WHOOSH = new MediaPlayer(new Media(new File(PREFIX + "whoosh.mp3").toURI().toString()));
    public static final MediaPlayer YAY = new MediaPlayer(new Media(new File(PREFIX + "yay.mp3").toURI().toString()));
    public static final MediaPlayer BOO = new MediaPlayer(new Media(new File(PREFIX + "boo.mp3").toURI().toString()));
}
