package cs2410.assn6.model;

/**
 * Represents an hourly worker
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class HourlyWorker extends Smarty {
    /**
     * Number of hours the worker has worked
     */
    private int hoursWorked;

    /**
     * The worker's hourly wage
     */
    private double hourlyWage;

    /**
     * The type of person
     */
    private final String PERSON_TYPE = "Hourly Worker";

    /**
     * Constructor for HourlyWorker
     * @param name The worker's name
     * @param math The math problem the worker can do
     * @param says What the worker says
     * @param iq The worker's IQ
     * @param hoursWorked Number of hours the worker has worked
     * @param hourlyWage The worker's hourly wage
     */
    public HourlyWorker(String name, String math, String says, int iq, int hoursWorked, double hourlyWage) {
        super(name, math, says, iq);
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
    }

    /**
     * Number of hours the worker has worked
     * @return Number of hours the worker has worked
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * The worker's income
     * @return The worker's income
     */
    @Override
    public double getIncome() {
        return hourlyWage * hoursWorked;
    }

    /**
     * Returns the type of person
     * @return the type of person
     */
    @Override
    public String getPersonType() {
        return PERSON_TYPE;
    }
}
