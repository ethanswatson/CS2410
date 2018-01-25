package cs2410.assn6.control;

import cs2410.assn6.model.*;

import java.util.ArrayList;

/**
 * ArrayList of people
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Controller {
    /**
     * ArrayList to hold all people
     */
    private ArrayList<Simpleton> people;

    /**
     * Constructor for controller
     */
    public Controller() {
        people = new ArrayList<>();
        people.add(new HourlyWorker("Joe", "2 * 2 = 4", "I like dogs.", 100, 50, 10));
        people.add(new HourlyWorker("Emily", "3 * 5 = 15", "Animals are cool.", 110, 40, 12));
        people.add(new ContractWorker("Dave", "10 / 5 = 2", "I want to go to the moon.", 150, 20, 500));
        people.add(new ContractWorker("Tom", "100 / 20 = 5", "I like to look at the stars.", 150, 20, 500));
        people.add(new Hobbit("Frodo", "1 + 1 = 2", "Carrots taste good.", 20));
        people.add(new Hobbit("Sam", "10 + 31 = 41", "I love farming.", 34));
    }

    /**
     * Adds a new hobbit to the list
     * @param name name
     * @param math math problem
     * @param says what they say
     * @param carrotsPicked how many carrots picked
     */
    public void createNewHobbit(String name, String math, String says, int carrotsPicked) {
        people.add(new Hobbit(name, math, says, carrotsPicked));
    }

    /**
     * Add a new hourly worker to the list
     * @param name name
     * @param math math
     * @param says says
     * @param iq iq
     * @param hoursWorked hours
     * @param hourlyWage wage
     */
    public void createNewHourlyWorker(String name, String math, String says, int iq, int hoursWorked, double hourlyWage) {
        people.add(new HourlyWorker(name, math, says, iq, hoursWorked, hourlyWage));
    }

    /**
     * add a new contract worker to the list
     * @param name name
     * @param math math
     * @param says says
     * @param iq iq
     * @param contractsCompleted contracts
     * @param payPerContract pay
     */
    public void createNewContractWorker(String name, String math, String says, int iq, int contractsCompleted, double payPerContract) {
        people.add(new ContractWorker(name, math, says, iq, contractsCompleted, payPerContract));
    }

    /**
     * String of all people's math problems
     * @return String of all people's math problems
     */
    public String getMath() {
        String math = "";
        for (Simpleton person : people) {
            math += person.getName() + ", " + ((PersonType) person).getPersonType() + ": " + person.doMath() + "\n";
        }

        return math;
    }

    /**
     * String of all Smartys' income
     * @return String of all Smartys' income
     */
    public String getIncome() {
        String income = "";
        for (Simpleton person : people) {
            if (person instanceof Smarty) {
                Smarty smarty = (Smarty) person;
                income += smarty.getName() + ", " + smarty.getPersonType() + ": $" + smarty.getIncome() + " is my income\n";
            }
        }

        return income;
    }

    /**
     * String of all hourly workers' hours
     * @return String of all hourly workers' hours
     */
    public String getHours() {
        String hours = "";
        for (Simpleton person : people) {
            if (person instanceof HourlyWorker) {
                HourlyWorker worker = (HourlyWorker) person;
                hours += worker.getName() + ", " + worker.getPersonType() + ": " + worker.getHoursWorked() + " hours worked\n";
            }
        }

        return hours;
    }

    /**
     * String of all Smartys' IQ
     * @return String of all Smartys' IQ
     */
    public String getIQ() {
        String iq = "";
        for (Simpleton person : people) {
            if (person instanceof Smarty) {
                Smarty smarty = (Smarty) person;
                iq += smarty.getName() + ", " + smarty.getPersonType() + ": My IQ is " + smarty.getIncome() + "\n";
            }
        }

        return iq;
    }

    /**
     * String of what all people say
     * @return String of what all people say
     */
    public String getSay() {
        String say = "";
        for (Simpleton person : people) {
            say += person.getName() + ", " + ((PersonType) person).getPersonType() + ": " + person.saySomethingSmart() + "\n";
        }

        return say;
    }

    /**
     * String of how many carrots each Hobbit has picked
     * @return String of how many carrots each Hobbit has picked
     */
    public String getCarrots() {
        String carrots = "";
        for (Simpleton person : people) {
            if (person instanceof Hobbit) {
                Hobbit hobbit = (Hobbit) person;
                carrots += hobbit.getName() + ", " + ((PersonType) hobbit).getPersonType() + ": " + hobbit.getCarrotsPicked() + " carrots picked\n";
            }
        }

        return carrots;
    }

    /**
     * String of how many contracts each ContractWorker has completed
     * @return String of how many contracts each ContractWorker has completed
     */
    public String getContracts() {
        String contracts = "";
        for (Simpleton person : people) {
            if (person instanceof ContractWorker) {
                ContractWorker worker = (ContractWorker) person;
                contracts += worker.getName() + ", " + worker.getPersonType() + ": " + worker.getContractsCompleted() + " contracts completed\n";
            }
        }

        return contracts;
    }
}
