package cs2410.assn7;

import java.io.File;

/**
 * Callback for updating video
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public interface Callback {
    /**
     * The callback method
     * @param file video file to update
     */
    void execute(File file);
}
