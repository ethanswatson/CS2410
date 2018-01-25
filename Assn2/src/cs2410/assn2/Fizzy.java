package cs2410.assn2;

/**
 * The Fizzy class runs the FizzBuzz solution
 *
 * @author Ethan Watson
 * @version 1.0.0
 */

public class Fizzy {
    /**
     * The current counter position from 1 to 100
     */
    private static int counter;

    /**
     * Main method to run program
     * @param args N/A
     */
    public static void main(String[] args) {
        for (counter = 1; counter <= 100; counter++) {
            if (isFizz(counter) || isBuzz(counter)) {
                if (isFizz(counter)) {
                    System.out.print("Fizz");
                }
                if (isBuzz(counter)) {
                    System.out.print("Buzz");
                }
            }
            else {
                System.out.print(counter);
            }
            // Move to next line
            System.out.println();
        }
    }

    /**
     * Checks if a value is a multiple of 3
     * @param val The value to be checked
     * @return true if val is a multiple of 3, false otherwise
     */
    private static boolean isFizz(int val) {
        boolean result = false;

        if (val % 3 == 0) {
            result = true;
        }

        return result;
    }

    /**
     * Checks if a value is a multiple of 5
     * @param val The value to be checked
     * @return true if val is a multiple of 5, false otherwise
     */
    private static boolean isBuzz(int val) {
        boolean result = false;

        if (val % 5 == 0) {
            result = true;
        }

        return result;
    }
}
