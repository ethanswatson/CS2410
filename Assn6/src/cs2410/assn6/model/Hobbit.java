package cs2410.assn6.model;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Hobbit implements Simpleton, PersonType {
    /**
     * The Hobbit's name
     */
    private String name;

    /**
     * Math problem the Hobbit can do
     */
    private String math;

    /**
     * What the Hobbit says
     */
    private String says;

    /**
     * How many carrots the Hobbit has picked
     */
    private int carrotsPicked;

    /**
     * The type of person
     */
    private final String PERSON_TYPE = "Hobbit";

    /**
     * Constructor for Hobbit
     * @param name The Hobbit's name
     * @param math Math problem the Hobbit can do
     * @param says What the Hobbit says
     * @param carrotsPicked How many carrots the Hobbit has picked
     */
    public Hobbit(String name, String math, String says, int carrotsPicked) {
        this.name = name;
        this.math = math;
        this.says = says;
        this.carrotsPicked = carrotsPicked;
    }

    /**
     * The type of person
     * @return The type of person
     */
    @Override
    public String getPersonType() {
        return PERSON_TYPE;
    }

    /**
     * The Hobbit's name
     * @return The Hobbit's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Math problem the Hobbit can do
     * @return Math problem the Hobbit can do
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * What the Hobbit says
     * @return What the Hobbit says
     */
    @Override
    public String saySomethingSmart() {
        return says;
    }

    /**
     * How many carrots the Hobbit has picked
     * @return How many carrots the Hobbit has picked
     */
    public int getCarrotsPicked() {
        return carrotsPicked;
    }
}
