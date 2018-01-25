package cs2410.assn8.component;

import cs2410.assn8.config.GameSettings;
import cs2410.assn8.config.Sounds;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.*;

/**
 * Sets up the board and controls gameplay
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Board extends GridPane {
    /**
     * Boolean Array of all grid locations. If a value is true there is a bomb in that location
     */
    private boolean[][] bombLocations;

    /**
     * List of all of the tiles
     */
    private ArrayList<Tile> tiles;

    /**
     * Number of tiles
     */
    private int numSquares;

    /**
     * Number of tiles that should be bombs
     */
    private int numBombs;

    /**
     * Property that contains the number of bombs remaining to update the label
     */
    private IntegerProperty numBombsRemainingProperty;

    /**
     * Alert to display win/lose status
     */
    private Alert alert;

    /**
     * Timer for keeping track of time
     */
    private Timer timer;

    /**
     * Property to update the game timer
     */
    private IntegerProperty timeProperty;

    /**
     * Constructor
     */
    public Board() {
        bombLocations = null;
        numSquares = GameSettings.ROWS * GameSettings.COLS;
        numBombs = (int)(numSquares * GameSettings.BOMB_PERCENTAGE);
        numBombsRemainingProperty = new SimpleIntegerProperty(numBombs);
        timeProperty = new SimpleIntegerProperty(0);
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Game Ended");

        timer = new Timer();

        tiles = new ArrayList<>(numSquares);
        for (int i = 0; i < GameSettings.ROWS; i++) {
            for (int j = 0; j < GameSettings.COLS; j++) {
                tiles.add(new Tile(this, new Location(i, j)));
            }
        }

        Iterator<Tile> iter = tiles.iterator();

        for (int i = 0; i < GameSettings.ROWS; i++) {
            for (int j = 0; j < GameSettings.COLS; j++) {
                Tile tile = iter.next();
                GridPane.setConstraints(tile, i, j);
                this.getChildren().add(tile);
            }
        }

        this.setGridLinesVisible(true);
        this.setHgap(1);
        this.setVgap(1);

    }

    /**
     * Resets the game board and all tiles
     */
    public void reset() {
        timer.cancel();
        bombLocations = null;
        for(Tile t : tiles) {
            t.reset(false);
        }
        enableTiles();
        numBombsRemainingProperty.set(numBombs);
        timeProperty.set(0);
    }

    /**
     * Given a location, returns the number of neighboring cells that are bombs
     * @param location location of the cell to check
     * @return The number of bombs next to the cell
     */
    public int getNumBombNeighbors(Location location) {
        Iterator<Location> neighboringLocations = location.getNeighborLocations();
        int numBombNeighbors = 0;
        while (neighboringLocations.hasNext()) {
            Location current = neighboringLocations.next();
            if (isValidLocation(current) && isBomb(current)) {
                numBombNeighbors++;
            }
        }

        return numBombNeighbors;
    }


    /**
     * Returns all of the tile's neighbors
     * @param location location of tile
     * @return Iterator of neighbors.
     */
    public Iterator<Tile> getNeighbors(Location location) {
        Iterator<Location> neighboringLocations = location.getNeighborLocations();
        ArrayList<Tile> tmp = new ArrayList<>();

        while (neighboringLocations.hasNext()) {
            Location current = neighboringLocations.next();
            if (isValidLocation(current)) {
                tmp.add(getTileAtLocation(current));
            }
        }

        return tmp.iterator();
    }

    /**
     * Checks if there are unrevealed and unmarked tiles on the board
     * @return true if there are no unrevealed tiles, false if there are
     */
    public boolean noUnrevealed() {
        for (Tile t : tiles) {
            if (t.getStatus() == TileStatus.UNREVEALED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the game is running
     * @return true if game is running, false otherwise
     */
    public boolean gameRunning() {
        return bombLocations != null;
    }

    /**
     * Returns number of bombs remaining property to update label
     * @return number of bombs remaining property
     */
    public IntegerProperty getNumBombsRemainingProperty() {
        return numBombsRemainingProperty;
    }

    public IntegerProperty getTimeProperty() {
        return timeProperty;
    }

    /**
     * Add one to the number of bombs remaining
     */
    public void incrementBombsRemaining() {
        numBombsRemainingProperty.set(numBombsRemainingProperty.get() + 1);
    }

    /**
     * Subtract one from the number of bombs remaining
     */
    public void decrementBombsRemaining() {
        numBombsRemainingProperty.set(numBombsRemainingProperty.get() - 1);
    }

    /**
     * Cancels Timer
     */
    public void cancelTimer() {
        timer.cancel();
    }

    /**
     * Ends the game
     */
    public void endGame(boolean win) {
        disableTiles();
        timer.cancel();
        if (win) {
            alert.setContentText("You won in " + timeProperty.get() + " seconds!!!");
            if (GameSettings.audio) {
                Sounds.YAY.play();
                Sounds.YAY.seek(new Duration(0));
            }
            alert.showAndWait();
        } else {
            for (Tile t : tiles) {
                if (t.isMarked()) {
                    if (t.isBomb()) {
                        t.setStatus(TileStatus.CORRECT_FLAG);
                    } else {
                        t.setStatus(TileStatus.INCORRECT_FLAG);
                    }
                } else if (t.isBomb()) {
                    t.setStatus(TileStatus.BOMB);
                }
            }
            alert.setContentText("You Lose :(");
            if (GameSettings.audio) {
                Sounds.BOO.play();
                Sounds.BOO.seek(new Duration(0));
            }
            alert.showAndWait();
        }
    }

    /**
     * Generates the list of bombs
     * @param firstClicked location of the first tile clicked
     */
    public void generateBombs(Location firstClicked) {
        ArrayList<Boolean> tempArr = new ArrayList<>(numSquares);

        for (int i = 0; i < numSquares; i++) {
            if (i < numBombs) {
                tempArr.add(true);
            } else {
                tempArr.add(false);
            }
        }

        Collections.shuffle(tempArr);

        // Make sure first location clicked is never a bomb
        while (tempArr.get(indexConverter(firstClicked.getX(), firstClicked.getY()))) {
            Collections.shuffle(tempArr);
        }

        Iterator<Boolean> iter = tempArr.iterator();

        bombLocations = new boolean[GameSettings.ROWS][GameSettings.COLS];
        for (int i = 0; i < GameSettings.ROWS; i++) {
            for (int j = 0; j < GameSettings.COLS; j++) {
                bombLocations[i][j] = iter.next();
            }
        }

        for (int i = 0; i < GameSettings.ROWS; i++) {
            for (int j = 0; j < GameSettings.COLS; j++) {
                getTileAtLocation(i, j).reset(bombLocations[i][j]);
            }
        }

        startTimer();
    }

    /**
     * Starts the timer
     */
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timeProperty.set(timeProperty.get() + 1);
                });
            }
        }, 1000, 1000);
    }

    /**
     * Checks if a location is part of the game board
     * @param location location to check
     * @return true if valid, false otherwise
     */
    private boolean isValidLocation(Location location) {
        int x = location.getX();
        int y = location.getY();

        return x >= 0 && x < GameSettings.ROWS && y >= 0 && y < GameSettings.COLS;
    }

    /**
     * Given a location, checks if the cell is a bomb
     * @param location location to check
     * @return true if bomb, false otherwise
     */
    private boolean isBomb(Location location) {
        return bombLocations[location.getX()][location.getY()];
    }

    /**
     * Gets a tile from a location
     * @param location location of tile
     * @return tile at location
     */
    private Tile getTileAtLocation(Location location) {
        return getTileAtLocation(location.getX(), location.getY());
    }

    /**
     * Gets a tile from a location
     * @param x pos of tile
     * @param y pos of tile
     * @return tile at location
     */
    private Tile getTileAtLocation(int x, int y) {
        return tiles.get(indexConverter(x, y));
    }

    /**
     * Converts a 2D index to a 1D index
     * @param x x index
     * @param y y index
     * @return 1D index
     */
    private int indexConverter(int x, int y) {
        return x * GameSettings.ROWS + y;
    }

    /**
     * Disables all tiles from being clicked
     */
    private void disableTiles() {
        for (Tile t : tiles) {
            t.disable();
        }
    }

    /**
     * Enables tiles
     */
    private void enableTiles() {
        for (Tile t : tiles) {
            t.enable();
        }
    }
}
