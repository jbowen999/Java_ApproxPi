import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int loopMax = 0;
        boolean validInput = false;
        
        // Keep asking for input until input is a valid int
        while (!validInput){
            try{
                System.out.println("Enter maximum loop count: ");
                loopMax = scanner.nextInt();
                validInput = true; // exit loop
            } catch (InputMismatchException e) {
                System.out.println("The input is not a valid integer.");
                scanner.next(); // clear invalid input from scanner
            }

        }
        scanner.close();
        // Print headers for table
        System.out.println("Maximum loop count = " + loopMax);
        System.out.printf("%-6s %-15s %-15s %-15s %-15s%n", "Count", "Approximation", "Error", "Variance (%)", "termI");
        System.out.println("--------------------------------------------------------------------");

        approxPi(loopMax);
    }

    public static void approxPi(int loopMax){
        double piOver4 = 0;
        int sign = 1;

        for (int i = 1; i <= loopMax; i++){

            double den = 2 * i - 1;
            double termI = sign / den;
            piOver4 += termI;

            double approximation = 4 * piOver4;
            double error = approximation - Math.PI;
            double variance = (error / Math.PI) * 100;


            // Print output if:
            // 1. The current iteration is one of the first 50 iterations (i <= 50), OR
            // 2. The current iteration is a multiple of 100 (i % 100 == 0), OR
            // 3. The current iteration is the final iteration (i == loopMax), OR
            // 4. The current iteration is the very next iteration after a multiple of 100 ((i - 1) % 100 == 0).
            if (i <= 50 || i % 100 == 0 || i == loopMax || (i - 1) % 100 == 0) {

                // Format string explanation:
                // %-6d    : Print an integer (d) with a minimum width of 6 characters, left-aligned (-).
                // %-15.10f: Print a floating-point number (f) with a minimum width of 15 characters, left-aligned (-),
                //           and 10 decimal places (.10).
                // %n      : Print a newline character to move to the next line.
                //
                // Breakdown of the format string:
                // %-6d    : For the "Count" column (integer).
                // %-15.10f: For the "Approximation" column (floating-point number with 10 decimal places).
                // %-15.10f: For the "Error" column (floating-point number with 10 decimal places).
                // %-15.10f: For the "Variance (%)" column (floating-point number with 10 decimal places).
                // %-15.10f: For the "termI" column (floating-point number with 10 decimal places).
                // %n      : Move to the next line after printing the row.

                System.out.printf("%-6d %-15.10f %-15.10f %-15.10f %-15.10f%n",
                        i, approximation, error, variance, termI);
            }

            // change the sign
            sign *= -1;

        }
        System.out.println("Math.PI = " + Math.PI);

    }



}