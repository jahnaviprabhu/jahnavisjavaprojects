import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Simple Calculator!");

        boolean newCalculation = true;
        double currentResult = 0;

        while (true) {
            double num1;

            if (newCalculation) {
                // Get the first number from the user for a new calculation
                System.out.print("Enter the first number: ");
                num1 = scanner.nextDouble();
            } else {
                // Use the current result for the continuation of the calculation
                num1 = currentResult;
            }

            // Get the operator from the user
            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            // Get the second number from the user
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            // Perform the calculation based on the operator
            switch (operator) {
                case '+':
                    currentResult = num1 + num2;
                    break;
                case '-':
                    currentResult = num1 - num2;
                    break;
                case '*':
                    currentResult = num1 * num2;
                    break;
                case '/':
                    // Check for division by zero
                    if (num2 != 0) {
                        currentResult = num1 / num2;
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                        continue;
                    }
                    break;
                default:
                    System.out.println("Error: Invalid operator.");
                    continue;
            }

            // Output the result
            System.out.println("The result is: " + currentResult);

            // Ask the user if they want to perform another calculation or continue
            System.out.print("Do you want to start a new calculation or continue with the current result? (new/continue/exit): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("new")) {
                newCalculation = true;
            } else if (response.equalsIgnoreCase("continue")) {
                newCalculation = false;
            } else if (response.equalsIgnoreCase("exit")) {
                break;
            } else {
                System.out.println("Invalid response. Exiting.");
                break;
            }
        }

        // Close the scanner
        scanner.close();
        System.out.println("Thank you for using the Simple Calculator. Goodbye!");
    }
}
