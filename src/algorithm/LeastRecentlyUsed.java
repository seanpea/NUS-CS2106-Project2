/**
 * FirstInFirstOut.java
 *
 * This class acts as the .
 *
 * @author Sean Pea
 * @version 1.0
 */

package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class LeastRecentlyUsed {
    
    private LinkedList<Integer> memoryQueue = new LinkedList<Integer>();
    private int mainMemorySize;
    
    public LeastRecentlyUsed(int mainMemorySize) {
        this.mainMemorySize = mainMemorySize;
        memoryQueue = new LinkedList<Integer>();
    }
    
    public int run(String referenceString) throws FileNotFoundException {
        int page;
        
        // Reset Page Fault
        int pageFault = 0;
        
        // Try to Open Reference String
        Scanner inFile = new Scanner(new File(referenceString));
        
        // Ignore Header
        inFile.nextDouble();
        inFile.nextInt();
        
        while (inFile.hasNextInt()) {
            // Get Page to be Loaded
            page = inFile.nextInt();
            // Search for Page in Main Memory
            // Page resites in Main Memory
            if (memoryQueue.contains(page)) {
                memoryQueue.add(memoryQueue.remove(memoryQueue.indexOf(page)));
            }
            // Page Fault
            else {
                // Increment Page Fault
                pageFault++;
                //System.out.print('*');
                // No Free Frame
                // Replace Policy
                if (memoryQueue.size() == mainMemorySize) {
                    // Remove Least Recently Used
                    // which is at the front of the List
                    memoryQueue.removeFirst();
                }
                // Allocation Page to Frame
                // Add to the back of the List
                memoryQueue.add(page);
            }
            //System.out.println(page);
            //System.out.println(memoryQueue.toString());
        }
        //System.out.println("*** " + pageFault);
        return pageFault;
    }
    
}
