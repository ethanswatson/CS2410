package cs2410.assn6.model;

/**
 * Represents a contract worker
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class ContractWorker extends Smarty {
    /**
     * Number of contracts the worker has completed
     */
    private int contractsCompleted;

    /**
     * How much money the worker receives upon completion of each contract
     */
    private double payPerContract;

    /**
     * The type of person
     */
    private final String PERSON_TYPE = "Contract Worker";

    /**
     * Constructor for ContractWorker
     * @param name The worker's name
     * @param math The math problem the worker can do
     * @param says What the worker says
     * @param iq The worker's IQ
     * @param contractsCompleted Number of contracts the worker has completed
     * @param payPerContract How much money the worker receives upon completion of each contract
     */
    public ContractWorker(String name, String math, String says, int iq, int contractsCompleted, double payPerContract) {
        super(name, math, says, iq);
        this.contractsCompleted = contractsCompleted;
        this.payPerContract = payPerContract;
    }

    /**
     * Number of contracts the worker has completed
     * @return Number of contracts the worker has completed
     */
    public int getContractsCompleted() {
        return contractsCompleted;
    }

    /**
     * The worker's income
     * @return The worker's income
     */
    @Override
    public double getIncome() {
        return payPerContract * contractsCompleted;
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
