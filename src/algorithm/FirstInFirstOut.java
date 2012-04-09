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
import java.util.Scanner;

public class FirstInFirstOut extends GlobalPageReplacement {
    
    private int oldestPageIndex;
    
    public FirstInFirstOut(int mainMemorySize) {
        super(mainMemorySize);
        this.oldestPageIndex = 0;
        initMemory();
    }
    
    public int run(String referenceString) throws FileNotFoundException {
        int page, frame;
        
        // Reset Page Fault
        pageFault = 0;
        
        // Try to Open Reference String
        Scanner inFile = new Scanner(new File(referenceString));
        
        // Ignore Header
        inFile.nextDouble();
        inFile.nextInt();
        
        while (inFile.hasNextInt()) {
            // Get Page to be Loaded
            page = inFile.nextInt();
            // Search for Page in Main Memory
            frame = searchMemory(page);
            
            //printMemory();
            
            // Page Fault
            if (frame == -1) {
                // Increment Page Fault
                pageFault++;
                //System.out.print('*');
                // Search for Free Frame
                frame = searchMemory(-1);
                // No Free Frame
                // Replace Policy
                if (frame == -1) {
                    // Allocate to the Oldest Page
                    frame = oldestPageIndex++;
                    oldestPageIndex %= mainMemory.length;
                }
                // Allocation Page to Frame
                mainMemory[frame] = page;
            }
            //System.out.println(page);
        }
        //printMemory();
        //System.out.println("*** " + pageFault);
        return pageFault;
    }
    
}
