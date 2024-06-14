import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        for (int i = 99; i > 0; i--) {
            // Print the current number of bottles
            if(i == 1){
                System.out.println(i + " bottle of beer on the wall, " + i + " bottle of beer.");
            }
            System.out.println(i + " bottles of beer on the wall, " + i + " bottles of beer.");

            // Calculate the number of bottles for the next verse
            int next = i - 1;

            // Print the action and the remaining bottles
            if (next > 0) {
                System.out.println("Take one down, pass it around, " + next + " bottles of beer on the wall.");
            } else {
                System.out.println("Take one down, pass it around, no more bottles of beer on the wall.");
            }

            // Print a blank line between verses
            System.out.println();
        }

        // Print the final verse when there are no more bottles left
        System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
        System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");
    }
}