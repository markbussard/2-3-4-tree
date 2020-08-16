/* Mark Bussard
 * Comp Sci 282 Project 2
 * Description: The main class. Initializes a 234 Tree and inserts default values
 * on start up. Menu implemented through a switch statement for showing the current
 * tree, inserting a new value, changing order of tree, reading in a data file
 * and creating B-Tree in memory with data, as well as a quit option.
 * 
 */
package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Tree234App {

    private static String INPUT_FILE_NAME; // data file to be read

    private static final String CURRENT_DIRECTORY // gets directory for data file
            = Paths.get("").toAbsolutePath().toString();

    private static Tree234 theTree = new Tree234(); // initializing the tree

    public static void main(String[] args) {
        long value;
        theTree.insert(50);
        theTree.insert(40);
        theTree.insert(60);
        theTree.insert(30);
        theTree.insert(70);

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, find, change, read, or quit: ");
            char choice = UserInput.getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = UserInput.getInt();
                    theTree.insert(value);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = UserInput.getLong();
                    int found = theTree.find(value);
                    if (found != -1) {
                        System.out.println("Found " + value);
                    } else {
                        System.out.println("Could not find " + value);
                    }
                    break;
                case 'c':
                    System.out.print("Enter value to change order of the tree: ");
                    // setting order of tree to user's input
                    Node.setOrder(UserInput.getInt());      
                    
                    if(Node.getOrder() == 4){ // if order = 4, create a 234 Tree
                        theTree = new Tree234();
                    }
                    else { // else create a B-Tree
                        theTree = new BTree();
                    }
                    // insert default values
                    theTree.insert(50);
                    theTree.insert(40);
                    theTree.insert(60);
                    theTree.insert(30);
                    theTree.insert(70);
                    break;
                case 'r':
                    readData(); // read data from text file
                    break;
                case 'q':
                    System.exit(0); // program shutdown
                default:
                    System.out.print("Invalid entry\n");
            } // end switch
        } // end while
    } // end main()

    // method for reading data file
    public static void readData() {
        System.out.print("Enter the name of the text file to read: ");
        INPUT_FILE_NAME = UserInput.getString(); // taking name of input file
        System.out.println("Current order of the tree is " + Node.getOrder() + ".");
        System.out.print("Would you like to change the order? Enter 'Y'es or 'N'o: ");
        char choice = UserInput.getChar(); // grab user choice
        if (choice == 'y') { // if yes, change order of Node class
            System.out.print("Enter new order for the tree: ");
            Node.setOrder(UserInput.getInt()); // set new order
        }
        if(Node.getOrder() == 4){
            theTree = new Tree234(); // initialize a new 234 Tree if order = 4
        }
        else{
            theTree = new BTree(); // initialize a new BTree if order > 4
        }

        // Initializing a scanner object that is grabbing the text from the file
        // at its location and by its name (set by the configuration variables)        
        try {
            Scanner input = new Scanner(new File(CURRENT_DIRECTORY
                    + File.separator + INPUT_FILE_NAME));
            // Setting records array to either default value of 101, or if user
            // ran the program with an argument, then that input is set

            while (input.hasNextLine()) {
                theTree.insert(input.nextLine()); // taking whole line of text 
            }
            input.close(); // closing scanner
            System.out.println("B-Tree successfully created with order: " 
                    + Node.getOrder());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(0);
        }
    }
}
