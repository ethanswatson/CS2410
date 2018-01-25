package cs2410.assn6.model;

/**
 * Describe this class
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public abstract class Smarty implements Simpleton, PersonType {
    /**
     * The person's name
     */
    private String name;

    /**
     * The math problem the person can do
     */
    private String math;

    /**
     * What the person says
     */
    private String says;

    /**
     * The person's IQ
     */
    private int iq;

    /**
     * The type of person
     */
    private final String PERSON_TYPE = "Smarty";

    /**
     * Constructor for smarty
     * @param name The person's name
     * @param math The math problem the person can do
     * @param says What the person says
     * @param iq The person's IQ
     */
    public Smarty(String name, String math, String says, int iq) {
        this.name = name;
        this.math = math;
        this.says = says;
        this.iq = iq;
    }

    /**
     * get the type of person
     * @return the type of person
     */
    @Override
    public String getPersonType() {
        return PERSON_TYPE;
    }

    /**
     * The person's name
     * @return The person's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * The math problem the person can do
     * @return The math problem the person can do
     */
    @Override
    public String doMath() {
        return math;
    }

    /**
     * What the person says
     * @return What the person says
     */
    @Override
    public String saySomethingSmart() {
        return says;
    }

    /**
     * The person's IQ
     * @return The person's IQ
     */
    public int getIQ() {
        return iq;
    }

    /**
     * get the person's icome
     * @return the person's income
     */
    public abstract double getIncome();
}
