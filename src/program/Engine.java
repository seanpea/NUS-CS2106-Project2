/**
 * Engine.java
 *
 * This class processes the functionalities provided by KryptosP4.
 *
 * @author Sean Pea
 * @version 1.0
 */

package program;

import algorithm.FirstInFirstOut;
import algorithm.LeastRecentlyUsed;
import algorithm.Random;
import algorithm.SecondChance;
import java.io.*;
import java.util.Scanner;

public class Engine {
    
    public void doReferenceString(int vmSize) {
        
        // Variable
        int rsLength;
        int windowSize; // e
        int numberOfPicks; // m
        double jumpProbability; // t
    
        String outFilename;
        
        PrintStream outFile = null;
        
        // Print Function
        System.out.println("\n1a. Generate Reference String");
        System.out.println("------------------------------------------");
        
        // Get Reference String Length
        rsLength = Input.getInt("Reference String Length", 1, null);
        windowSize = Input.getInt("Working Set's Window Size (e)", 1, vmSize);
        numberOfPicks = Input.getInt("Number of Picks (m)", 1, null);
        jumpProbability = Input.getInt("Jump Probability (t)", 0, 100) / 100.0;
        
        // Get Output Filename
        outFilename = Input.getString("Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();
        
        // Create Reference String
        ReferenceString rs = new ReferenceString(
                rsLength, vmSize, windowSize, numberOfPicks, jumpProbability);
        
        try {
            outFile = new PrintStream(new File(outFilename));
            rs.generate(outFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Output file cannot be opened!");
        } finally {
            if (outFile != null)
                outFile.close();
        }
        
        // For Error Checking
        //System.out.println(rs.generate());
        
    }
    
    public void doReferenceString() {
        
        // Variable
        int vmSize = 1000;
        int rsLength = 250000;
        int numberOfPicks = 100; // m
        // 10 Ts
        double[] t = {0.01, 0.03, 0.05, 0.08, 0.1,
            0.15, 0.3, 0.5, 0.7, 0.85};
        // 22 Es
        int[] e = {2, 4, 7, 10, 15, 20, 25, 30, 35, 40, 45, 50,
            55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        
        // 220 Times
        String outFilename;
        ReferenceString rs;
        PrintStream outFile = null;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < e.length; j++) {
                outFilename = "resource/rs-t" + (t[i] * 100) + "-e" + e[j] + ".txt";
                System.out.print("Processing " + outFilename);
                rs = new ReferenceString(rsLength, vmSize,
                        e[j], numberOfPicks, t[i]);
                try {
                    outFile = new PrintStream(new File(outFilename));
                    rs.generate(outFile);
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: Output file cannot be opened!");
                } finally {
                    if (outFile != null)
                        outFile.close();
                }
                System.out.println("\t..... [OK]");
            }
        }
        
    }
    
    public void doRandomAlgorithm(int memorySize) {
        String inFileName;
        
        // Print Function
        System.out.println("\n2a. Random Algorithm");
        System.out.println("------------------------------------------");
        
        // Get Output Filename
        inFileName = Input.getString("Reference String's Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();
        
        Random random = new Random(memorySize);
        try {
            random.run(inFileName);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Input file cannot be opened!");
        }
    }
    
    public void doFIFOAlgorithm(int memorySize) {
        String inFileName;
        
        // Print Function
        System.out.println("\n2b. First-In First-Out Algorithm");
        System.out.println("------------------------------------------");
        
        // Get Output Filename
        inFileName = Input.getString("Reference String's Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();
        
        FirstInFirstOut fifo = new FirstInFirstOut(memorySize);
        try {
            fifo.run(inFileName);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Input file cannot be opened!");
        }
    }
    
    public void doLRUAlgorithm(int memorySize) {
        String inFileName;
        
        // Print Function
        System.out.println("\n2c. Least Recently Used Algorithm");
        System.out.println("------------------------------------------");
        
        // Get Output Filename
        inFileName = Input.getString("Reference String's Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();
        
        LeastRecentlyUsed lru = new LeastRecentlyUsed(memorySize);
        try {
            lru.run(inFileName);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Input file cannot be opened!");
        }
    }
    
    public void doSecondChanceAlgorithm(int memorySize) {
        String inFileName;
        
        // Print Function
        System.out.println("\n2d. Second-Chance Algorithm");
        System.out.println("------------------------------------------");
        
        // Get Output Filename
        inFileName = Input.getString("Reference String's Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();
        
        SecondChance sc = new SecondChance(memorySize);
        try {
            sc.run(inFileName);
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Input file cannot be opened!");
        }
    }
    
    public void doAnalysisAllAlgorithms(int memorySize) {
        String inFilename, outFilename;
        int pfRandom, pfFifo, pfLru, pfSecondChance;
        
        Scanner inFile = null;
        BufferedWriter outFile = null;

        
        // Print Function
        System.out.println("\n3a. All Algorithms");
        System.out.println("------------------------------------------");
        
        // Get Output Filename
        inFilename = Input.getString("Reference String's Filename", null);
        outFilename = Input.getString("Output Filename", null);
        
        System.out.println("------------------------------------------");
        System.out.println();

        try {
            //Date t1 = new Date();
            Random random = new Random(memorySize);
            pfRandom = random.run(inFilename);
            
            FirstInFirstOut fifo = new FirstInFirstOut(memorySize);
            pfFifo = fifo.run(inFilename);
            
            LeastRecentlyUsed lru = new LeastRecentlyUsed(memorySize);
            pfLru = lru.run(inFilename);
            
            SecondChance sc = new SecondChance(memorySize);
            pfSecondChance = sc.run(inFilename);
            //Date t2 = new Date();
            
            //Date t3 = new Date(t2.getTime()-t1.getTime());
            
            //System.out.println(t3);
            
            // File
            inFile = new Scanner(new File(inFilename));
            outFile = new BufferedWriter(
                    new FileWriter(new File(outFilename), true));
            
            outFile.newLine();
            outFile.append(inFile.nextDouble() + "," + inFile.nextInt()
                    + "," + pfRandom + "," + pfFifo
                    + "," +pfLru + "," + pfSecondChance);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Output file cannot be opened!");
        } catch (IOException ex) {
            System.out.println("Error: Input file cannot be opened!");
        } finally {
            if (inFile != null)
                inFile.close();
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException ex) {
                    System.out.println("Error: Output file cannot be closed!");
                }
            }
        }
    }
    
    public void doAnalysisAllAlgorithms() {
        
        // Variable
        int memorySize = 100;
        String inFilename, outFilename = "resource/Analysis2.csv";
        int pfRandom, pfFifo, pfLru, pfSecondChance;
        // 10 Ts
        double[] t = {0.01, 0.03, 0.05, 0.08, 0.1,
            0.15, 0.3, 0.5, 0.7, 0.85};
        // 22 Es
        int[] e = {2, 4, 7, 10, 15, 20, 25, 30, 35, 40, 45, 50,
            55, 60, 65, 70, 75, 80, 85, 90, 95, 100};
        
        Scanner inFile = null;
        BufferedWriter outFile = null;
        
        // 220 Times
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < e.length; j++) {
                inFilename = "resource/rs-t" + (t[i] * 100) + "-e" + e[j] + ".txt";
                System.out.print("Processing " + inFilename);
                
                try {
                    Random random = new Random(memorySize);
                    pfRandom = random.run(inFilename);

                    FirstInFirstOut fifo = new FirstInFirstOut(memorySize);
                    pfFifo = fifo.run(inFilename);

                    LeastRecentlyUsed lru = new LeastRecentlyUsed(memorySize);
                    pfLru = lru.run(inFilename);

                    SecondChance sc = new SecondChance(memorySize);
                    pfSecondChance = sc.run(inFilename);

                    // File
                    inFile = new Scanner(new File(inFilename));
                    outFile = new BufferedWriter(
                            new FileWriter(new File(outFilename), true));

                    outFile.newLine();
                    outFile.append(inFile.nextDouble() + "," + inFile.nextInt()
                            + "," + pfRandom + "," + pfFifo
                            + "," +pfLru + "," + pfSecondChance);

                } catch (FileNotFoundException ex) {
                    System.out.println("Error: Output file cannot be opened!");
                } catch (IOException ex) {
                    System.out.println("Error: Input file cannot be opened!");
                } finally {
                    if (inFile != null)
                        inFile.close();
                    if (outFile != null) {
                        try {
                            outFile.close();
                        } catch (IOException ex) {
                            System.out.println("Error: Output file cannot be closed!");
                        }
                    }
                }
                System.out.println("\t..... [OK]");
            }
        }
        
    }
    
}
