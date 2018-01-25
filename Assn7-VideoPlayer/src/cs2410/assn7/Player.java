package cs2410.assn7;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

/**
 * The video player, with controls
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Player extends BorderPane {
    /**
     * The media to play
     */
    private Media video;

    /**
     * The media view
     */
    private MediaView mediaView;

    /**
     * The media player
     */
    private MediaPlayer mediaPlayer;

    /**
     * Button to play/pause video
     */
    private Button playPauseBtn;

    /**
     * Button to stop video
     */
    private Button stopBtn;

    /**
     * Slider to adjust volume
     */
    private Slider volumeSlider;

    /**
     * Slider to adjust time
     */
    private Slider timelineSlider;

    /**
     * Flag for video playing
     */
    private boolean playing;

    /**
     * duration of the video
     */
    private Duration duration;

    /**
     * Constructor
     */
    public Player() {
        playing = false;
        mediaView = new MediaView();
        mediaView.setFitWidth(1280);
        mediaView.setFitHeight(720);
        mediaPlayer = null;
        initButtons();
        initSliders();

        Label volumeLabel = new Label("Volume:");
        Label timeLabel = new Label("Time:");

        HBox controls = new HBox();
        controls.getChildren().addAll(playPauseBtn, stopBtn, volumeLabel, volumeSlider, timeLabel, timelineSlider);
        controls.setSpacing(5);
        controls.setPadding(new Insets(5));


        setCenter(mediaView);
        setBottom(controls);

        setPrefSize(1280, 720);

    }

    /**
     * Updates the player with a new video
     * @param file the video file. Null if no video
     */
    public void updateVideo(File file) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (file != null) {
            video = new Media(file.toURI().toString());
            initMediaPlayer();
            mediaView.setMediaPlayer(mediaPlayer);
        } else {
            video = null;
            mediaPlayer = null;
            mediaView.setMediaPlayer(null);
        }
    }

    /**
     * initialize buttons
     */
    private void initButtons() {
        playPauseBtn = new Button("Play");
        stopBtn = new Button("Stop");

        playPauseBtn.setMinWidth(50);
        stopBtn.setMinWidth(50);

        playPauseBtn.setOnAction(event -> {
            if (mediaPlayer != null) {
                if (playing) {
                    mediaPlayer.pause();
                    playPauseBtn.setText("Play");
                } else {
                    mediaPlayer.play();
                    playPauseBtn.setText("Pause");
                }

                playing = !playing;
            }
        });

        stopBtn.setOnAction(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                if (playing) {
                    playing = false;
                    playPauseBtn.setText("Play");
                }
            }
        });
    }

    /**
     * initialize sliders
     */
    private void initSliders() {
        volumeSlider = new Slider();
        timelineSlider = new Slider();

        HBox.setHgrow(timelineSlider, Priority.ALWAYS);

        volumeSlider.setMin(0);
        volumeSlider.setMax(100);

        timelineSlider.setMin(0);
        timelineSlider.setMax(100);

        volumeSlider.valueProperty().addListener(observable -> {
            if (volumeSlider.isValueChanging() && mediaPlayer != null) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
            }
        });
    }

    /**
     * Initialize the player
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer(video);
        mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);

        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
        });

        timelineSlider.valueProperty().addListener(observable -> {
            if (timelineSlider.isValueChanging() && mediaPlayer != null) {
                mediaPlayer.seek(duration.multiply(timelineSlider.getValue() / 100.0));
            }
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!timelineSlider.isValueChanging() && mediaPlayer != null) {
                timelineSlider.setValue((mediaPlayer.getCurrentTime().toMillis() / duration.toMillis()) * 100);
            }
        });
    }
}
