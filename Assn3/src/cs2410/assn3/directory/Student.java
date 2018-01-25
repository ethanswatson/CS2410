package cs2410.assn3.directory;

/**
 * Holds info for a single student
 *
 * @author Ethan Watson
 * @version X.X.X
 */
public class Student {
    /**
     * Student's first name
     */
    private String firstName;

    /**
     * Student's last name
     */
    private String lastName;

    /**
     * Student's age
     */
    private int age;

    /**
     * Student's major code
     */
    private String major;

    /**
     * Student's ID number
     */
    private String id;

    /**
     * Constructs Student object
     * @param firstName first name
     * @param lastName last name
     * @param age age
     * @param major major code
     * @param id id number
     */
    public Student(String firstName, String lastName, int age, String major, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.major = major;
        this.id = id;
    }

    /**
     * Accessor for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Accessor for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Accessor for age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Accessor for major
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Accessor for id
     * @return id
     */
    public String getId() {
        return id;
    }
}
