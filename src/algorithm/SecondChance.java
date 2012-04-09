/**
 * SecondChance.java
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

public class SecondChance extends GlobalPageReplacement {
    
    private int[] chances;
    private int currentIndex;
    
    public SecondChance(int mainMemorySize) {
        super(mainMemorySize);
        this.chances = new int[mainMemorySize];
        this.currentIndex = 0;
        this.initMemory();
    }
    
    /*
     * Initailize Array with -1 (Free Frame)
     */
    @Override
    protected void initMemory() {
        super.initMemory();
        for (int i = 0; i < chances.length; i++)
            chances[i] = 0;
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
                    while (chances[currentIndex] == 1) {
                        chances[currentIndex++] = 0;
                        currentIndex %= chances.length;
                    }
                    // Set Frame to be Allocated to the Index
                    // that used up its second-chance
                    frame = currentIndex++;
                    currentIndex %= chances.length;
                }
                // Allocation Page to Frame
                mainMemory[frame] = page;
            }
            chances[frame] = 1;
            
            //System.out.println(page);
        }
        //printMemory();
        //System.out.println("*** " + pageFault);
        return pageFault;
    }
    
}
