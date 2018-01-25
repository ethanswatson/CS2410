package cs2410.assn6.view;

/**
 * Enumeration for types of people
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public enum DisplayOption {
    NONE (""),
    MATH ("Math"),
    INCOME ("Income"),
    HOURS ("Hours"),
    IQ ("IQ"),
    SAY ("Say"),
    CARROTS ("Carrots"),
    CONTRACTS ("Contracts"),
    HOURLY_WORKER (""),
    CONTRACT_WORKER (""),
    HOBBIT ("");

    private String label;

    DisplayOption(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}
