/**
 * PageReplacementAlgorithms.java
 *
 * This data structure holds the Plain Text, Cipher Text and the Key
 * that is used during the encryption and decryption process.
 *
 * @author Sean Pea
 * @version 1.0
 */

package program;

public class PageReplacementAlgorithms {
    
    /*
     * Instance Variables
     */
    private int memorySize; // F
    private int vmSize; // P
    
    Engine engine;
    
    /*
     * Constructor
     */
    public PageReplacementAlgorithms() {
        engine = new Engine();
    }
    
    public void run() {
        displayWelcomeMessage();
        init();
        String choice = "";

        while (!choice.equals("0")) {

            try {
                displayMenu();
                choice = Input.getString("Your Choice", null);
                dispatch(choice);
            } catch (Exception ex) {
                System.err.println("\nERROR: Caught an unexpected exception!");
                vmSize = Input.getInt("Virtual Memory Size (P)", 1, null);
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }
    }
    
    private void init() {
        System.out.println();
        System.out.println("Initializing Program...");
        System.out.println();
        memorySize = Input.getInt("Memory Size (F)", 1, null);
        vmSize = Input.getInt("Virtual Memory Size (P)", 1, null);
        System.out.println();
        System.out.println("Initialization Completed...");
    }
    
    private void dispatch(String choice) {
        // To lowercase
        choice = choice.toLowerCase();
        
        // 1a. Generate Reference String
        if (choice.equalsIgnoreCase("1a")) {
            engine.doReferenceString(vmSize);
        }
        // 2a. Random Algorithm
        else if (choice.equalsIgnoreCase("2a")) {
            engine.doRandomAlgorithm(memorySize);
        }
        // 2b. First-In First-Out Algorithm
        else if (choice.equalsIgnoreCase("2b")) {
            engine.doFIFOAlgorithm(memorySize);
        }
        // 2c. Least Recently Used Algorithm
        else if (choice.equalsIgnoreCase("2c")) {
            engine.doLRUAlgorithm(memorySize);
        }
        // 2d. Second-Chance Algorithm
        else if (choice.equalsIgnoreCase("2d")) {
            engine.doSecondChanceAlgorithm(memorySize);
        }
        // 3a. Anaysis of All Algorithms
        else if (choice.equalsIgnoreCase("3a")) {
            engine.doAnalysisAllAlgorithms(memorySize);
        }
        // 0. Exit
        else if (choice.equalsIgnoreCase("0")) {
            System.out.println("\nTerminating Program...");
        }
        // Invalid Command
        else {
            System.out.println("\nInvalid Command.");
        }
    }
    
    private void displayWelcomeMessage() {
        System.out.println("==========================================");
        System.out.println("|       Page Replacement Algorithms      |");
        System.out.println("|                Analyzer                |");
        System.out.println("|               Version 1.0              |");
        System.out.println("==========================================");
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("                Main Menu");
        System.out.println("------------------------------------------");
        System.out.println("Reference String");
        System.out.println("   1a. Generate Reference String");
        System.out.println("Algorithms");
        System.out.println("   2a. Random");
        System.out.println("   2b. First-In First-Out");
        System.out.println("   2c. Least Recently Used");
        System.out.println("   2d. Second-Chance");
        System.out.println("Analysis");
        System.out.println("   3a. All Algorithms");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("------------------------------------------");
    }

    public static void main(String[] args) {
        //new PageReplacementAlgorithms().run();
        new PageReplacementAlgorithms().driver();
        System.out.println("Program Terminated!");
    }
    
    private void driver() {
        engine.doReferenceString();
        engine.doAnalysisAllAlgorithms();
    }
}
