/*  Mark Bussard  
 *  Comp Sci 282 Project 2
 *  Description: This class is for grabbing the user's input
 */
package project2;

import java.util.Scanner;

public class UserInput {

    private static final String letters = "abcdefghijklmnopqrstuvwxyz";

    public static int getInt() {
        int number;
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextInt();
                return number;

            } catch (Exception e) {
                System.out.println("You entered an invalid number");
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    public static long getLong() {
        long number;
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextLong();
                return number;

            } catch (Exception e) {
                System.out.println("You entered an invalid number");
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    public static double getDouble() {
        double number;
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                number = input.nextDouble();
                return number;
            } catch (Exception e) {
                System.out.println("You didn't enter a valid double.");
                System.out.print("Please enter a valid double: ");
            }
        }
    }
    
    public static char getChar() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                String letter = input.next();
                return letter.charAt(0);

            } catch (Exception e) {
                System.out.println("You didn't enter a valid character.");
                System.out.print("Please enter a valid character: ");
            }
        }
    }

    public static String getString() {
        String word;
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                word = input.nextLine();
                return word;
            } catch (Exception e) {
                System.out.println("You didn't enter a valid string.");
                System.out.print("Please enter a string: ");
            }
        }
    }

    public static int getInt(int min, int max) {
        int number;
        while (true) {
            number = getInt();

            if (number >= min && number <= max) {
                break;

            } else {
                System.out.println("You didn't enter a number between " + min + " and " + max);
                System.out.print("Enter a number: ");
            }
        }
        return number;
    }

    public static long getLong(long min, long max) {
        long number;
        while (true) {
            number = getLong();

            if (number > min && number < max) {
                return number;

            } else {
                System.out.println("You didn't enter a long between " 
                        + min + " and " + max);
                System.out.print("Enter a long value: ");
            }
        }
    }
    
    public static double getDouble(double min, double max) {
        double number;
        while (true) {
            number = getDouble();

            if (number > min && number < max) {
                return number;

            } else {
                System.out.println("You didn't enter a double between " + min + " and " + max);
                System.out.print("Enter a double value: ");
            }
        }
    }
    
    public static char getChar(char min, char max) {
        char letter;
        while (true) {
            letter = getChar();
            int a = letters.indexOf(letter);
            int b = letters.indexOf(min);
            int c = letters.indexOf(max);

            if (a > b && a < c) {
                return letter;

            } else {
                System.out.println("You didn't enter a character between " + min + " and " + max);
                System.out.print("Enter a character: ");
            }
        }
    }

    public static String getString(int min, int max) {
        String word;
        while (true) {
            word = getString();

            if (word.length() > min && word.length() < max) {
                return word;

            } else {
                System.out.println("You didn't enter a string between " + min + " and " + max + " characters long.");
                System.out.print("Enter a string without spaces: ");
            }
        }
    }
}
