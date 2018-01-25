package cs2410.assn8.component;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Iterator<Location> getNeighborLocations() {
        ArrayList<Location> locations = new ArrayList<>(8);
        locations.add(new Location(x - 1, y - 1));
        locations.add(new Location(x, y - 1));
        locations.add(new Location(x + 1, y - 1));
        locations.add(new Location(x - 1, y));
        locations.add(new Location(x + 1, y));
        locations.add(new Location(x - 1, y + 1));
        locations.add(new Location(x, y + 1));
        locations.add(new Location(x + 1, y + 1));

        return locations.iterator();
    }
}
