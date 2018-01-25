package cs2410.assn6.model;

/**
 * Simpleton interface to be used by types of people
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public interface Simpleton {
    /**
     * gets the person's name
     * @return The person's name
     */
    String getName();

    /**
     * gets the math problem the person can do
     * @return the math problem the person can do
     */
    String doMath();

    /**
     * gets what the person says
     * @return what the person says
     */
    String saySomethingSmart();
}
