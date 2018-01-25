package cs2410.assn8.component;

import cs2410.assn8.config.GameSettings;
import cs2410.assn8.config.Sounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Iterator;

/**
 * An individual tile in the grid
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Tile extends Pane {
    /**
     * Whether or not this tile is a bomb;
     */
    private boolean containsBomb;

    /**
     * The game board
     */
    private Board board;

    /**
     * The location of this tile on the board
     */
    private Location location;

    /**
     * Whether or not the tile is revealed
     */
    private boolean revealed;

    /**
     * Whether or not the tile is marked
     */
    private boolean marked;

    /**
     * Whether or not the tile is disabled
     */
    private boolean disabled;

    /**
     * Status of the tile
     */
    private TileStatus status;

    /**
     * Image view to display image
     */
    private ImageView imageView;

    /**
     * Constructor
     * @param board game board tile is located on
     * @param location location of tile on board
     */
    public Tile(Board board, Location location) {
        this.containsBomb = false;
        this.board = board;
        this.location = location;
        revealed = false;
        marked = false;
        disabled = false;
        initPane();
    }

    /**
     * Reveals the tile
     */
    public void reveal() {
        if (!revealed && !marked) {
            if (containsBomb) {
                setStatus(TileStatus.BOMB);
                if (GameSettings.audio) {
                    Sounds.BOOM.play();
                    Sounds.BOOM.seek(new Duration(0));
                }
                board.endGame(false);
            } else {
                int numBombNeighbors = board.getNumBombNeighbors(location);
                if (numBombNeighbors == 0) {
                    setStatus(TileStatus.BLANK);
                } else {
                    setNumberStatus(numBombNeighbors);
                }
                if (GameSettings.audio) {
                    Sounds.CLICK.play();
                    Sounds.CLICK.seek(new Duration(0));
                }
            }
            revealed = true;

            if (board.noUnrevealed() && !containsBomb) {
                board.endGame(true);
            }
        }
    }

    /**
     * Reveals the tile's neighbors
     */
    private void revealNeighbors() {
        Iterator<Tile> neighbors = board.getNeighbors(location);
        while (neighbors.hasNext()) {
            Tile current = neighbors.next();
            if (!current.isBomb() && !current.isMarked() && !current.revealed) {
                current.reveal();
                if (current.getStatus() == TileStatus.BLANK) {
                    current.revealNeighbors();
                }
            }
        }
    }

    /**
     * Toggles the marking on the tile
     */
    public void mark() {
        if (!revealed) {
            switch (status) {
                case UNREVEALED:
                    setStatus(TileStatus.FLAGGED);
                    marked = true;
                    board.decrementBombsRemaining();
                    break;
                case FLAGGED:
                    setStatus(TileStatus.POTENTIAL);
                    break;
                case POTENTIAL:
                    setStatus(TileStatus.UNREVEALED);
                    marked = false;
                    board.incrementBombsRemaining();
                    break;
            }

            if (GameSettings.audio) {
                Sounds.WHOOSH.play();
                Sounds.WHOOSH.seek(new Duration(0));
            }

            if (board.noUnrevealed()) {
                board.endGame(true);
            }
        }
    }

    /**
     * Disables the tile from being clicked on
     */
    public void disable() {
        disabled = true;
    }

    /**
     * Enables the tile
     */
    public void enable() {
        disabled = false;
    }

    /**
     * Resets the tile
     * @param containsBomb new bomb value
     */
    public void reset(boolean containsBomb) {
        this.containsBomb = containsBomb;
        setStatus(TileStatus.UNREVEALED);
        revealed = false;
        marked = false;
        disabled = false;
    }

    /**
     * whether or not tile is a bomb
     * @return true if bomb, false otherwise
     */
    public boolean isBomb() {
        return containsBomb;
    }

    /**
     * whether or not tile is revealed
     * @return true if revealed, false otherwise
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     * whether or not tile is marked
     * @return true if marked, false otherwise
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Returns the status
     * @return the status of the tile
     */
    public TileStatus getStatus() {
        return status;
    }

    /**
     * Initializes GUI components
     */
    private void initPane() {
        this.setPrefSize(25,25);
        imageView = new ImageView();
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        this.getChildren().add(imageView);
        this.setOnMouseClicked(e -> {
            if (!disabled) {
                MouseButton button = e.getButton();
                if (button == MouseButton.PRIMARY) {
                    if (!board.gameRunning()) {
                        board.generateBombs(location);
                    }
                    reveal();
                    if (status == TileStatus.BLANK) {
                        revealNeighbors();
                    }
                } else if (button == MouseButton.SECONDARY) {
                    mark();
                }
            }
        });

        setStatus(TileStatus.UNREVEALED);
    }

    /**
     * Sets the status
     * @param status new status
     */
    public void setStatus(TileStatus status) {
        this.status = status;
        this.setStyle("-fx-background-color: " + status.getBackgroundColor());
        imageView.setImage(status.getDisplay());
    }

    /**
     * Sets the status for number status types
     * @param num number of neighboring bombs
     */
    private void setNumberStatus(int num) {
        switch (num) {
            case 1:
                setStatus(TileStatus.ONE);
                break;
            case 2:
                setStatus(TileStatus.TWO);
                break;
            case 3:
                setStatus(TileStatus.THREE);
                break;
            case 4:
                setStatus(TileStatus.FOUR);
                break;
            case 5:
                setStatus(TileStatus.FIVE);
                break;
            case 6:
                setStatus(TileStatus.SIX);
                break;
            case 7:
                setStatus(TileStatus.SEVEN);
                break;
            case 8:
                setStatus(TileStatus.EIGHT);
                break;
            default:
                System.out.println("Invalid Number: " + num);
        }
    }
}
