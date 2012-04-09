/**
 * ReferenceString.java
 *
 * This class is responsible for generating Reference String.
 *
 * @author Sean Pea
 * @version 1.0
 */

package program;

import java.io.PrintStream;

public class ReferenceString {
    
    /*
     * Instance Variables
     */
    private int length; // len
    private int vmSize; // P
    
    private int startLocation; // p
    private int windowSize; // e
    private int numberOfPicks; // m
    private double jumpProbability; // t
    
    /*
     * Constructor
     */
    public ReferenceString(int length, int vmSize,
            int windowSize, int numberOfPicks, double jumpProbability) {
        this.length = length;
        this.vmSize = vmSize;
        this.startLocation = (int) (Math.random() * (vmSize));
        this.windowSize = windowSize;
        this.numberOfPicks = numberOfPicks;
        this.jumpProbability = jumpProbability;
    }
    
    /*
     * This method generate a Random String
     */
    public void generate(PrintStream outFile) {
        int random, end;
        double probability;
        //StringBuilder sb = new StringBuilder();
        
        // Print Header Info (t and e)
        outFile.printf("%.2f\n", jumpProbability);
        outFile.println(windowSize);
        
        // Loop until RS reaches its length
        // Each iteration generates (m) elements
        int numElement = 0;
        while (numElement < length) {
            // End of Window
            end = startLocation + windowSize;
            // Loop (m) times to select (m) numbers
            for (int j = 0; j < numberOfPicks; j++) {
                // Generate a Random Number from
                // starting of window to end-1
                random = startLocation + (int)
                        (Math.random() * (end - startLocation));
                // Ensure Window wrap around to the start
                random %= vmSize;
                // Add to RS
                outFile.println(random);
                numElement++;
                //outFile.print(',');
            }
            // Generate a double in [0, 1]
            //probability = ((int) (Math.random() * 101)) / 100.0;
            probability = Math.random();
            // Jump
            //System.out.println("***" + probability + " < " + jumpProbability);
            if (probability < jumpProbability) {
                // Modify startLocation to a Random Number in [0, vmSize-1]
                startLocation = (int) (Math.random() * (vmSize));
            }
            else {
                startLocation++;
            }
        }
        //return outFile.deleteCharAt(sb.length()-1).toString();
    }
    
    
}
