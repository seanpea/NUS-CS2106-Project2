/**
 * PageReplacementAlgorithms.java
 *
 * This class acts as the .
 *
 * @author Sean Pea
 * @version 1.0
 */

package algorithm;

public class GlobalPageReplacement {
    
    /*
     * Instance Variables
     */
    protected int pageFault;
    protected int[] mainMemory;
    
    public GlobalPageReplacement(int mainMemorySize) {
        this.pageFault = 0;
        this.mainMemory = new int[mainMemorySize];
    }
    
    /*
     * Initailize Array with -1 (Free Frame)
     */
    protected void initMemory() {
        for (int i = 0; i < mainMemory.length; i++)
            mainMemory[i] = -1;
    }
    
    /*
     * Search Memory for a specific element
     * 
     * Post: return location of element in memory or -1 if not found
     */
    protected int searchMemory(int element) {
        for (int i = 0; i < mainMemory.length; i++)
            if (element == mainMemory[i])
                return i;
        return -1;
    }
    
    /*
     * Search Memory for a specific element
     * 
     * Post: return location of element in memory or -1 if not found
     */
    protected void printMemory() {
        for (int i = 0; i < mainMemory.length; i++)
            System.out.print(mainMemory[i] + " | ");
        System.out.println();
    }
    
}
