/**
 * Input.java
 *
 * This class is responsible for capturing valid inputs from users.
 *
 * @author Sean Pea
 * @version 1.0
 */

package program;

import java.util.Scanner;

public class Input {

    public static String getString(String attrName, String oldValue) {
        Scanner scan = new Scanner(System.in);
        String strValue = null;

        try {
            while (true) {
                System.out.print("Enter " + attrName +
                        (oldValue==null ? "" : "(" + oldValue + ")") + " : ");
                strValue = scan.nextLine();
                if (strValue.length() != 0)
                    break;
                else if (strValue.length() == 0 && oldValue != null) {
                    strValue = oldValue;
                    break;
                }
                System.out.println("Invalid " + attrName + "...");
            }
        } catch(Exception ex) {
            System.out.println("\nERROR: " + ex.getMessage());
        }
        
        return strValue.trim();
    }

    public static int getInt(String attrName, Integer minValue, Integer maxValue) {
        Scanner scan = new Scanner(System.in);
        String strValue;
        int intValue = -1;

        try {
            while (true) {
                System.out.print("Enter " + attrName + " : ");
                strValue = scan.nextLine();
                if (strValue.length() != 0) {
                    try {
                        intValue = Integer.parseInt(strValue);
                        if (minValue != null && intValue < minValue) {
                            System.out.println("Invalid Range. " + attrName +
                                    " must be more than " + minValue +".");
                        }
                        else if (maxValue != null && intValue > maxValue) {
                            System.out.println("Invalid Range. " + attrName +
                                    " must be less than " + maxValue +".");
                        }
                        else {
                            break;
                        }
                    } catch(NumberFormatException ex) {
                        System.out.println("Invalid Number...");
                    }
                }
                System.out.println("Invalid " + attrName + "...");
            }
        } catch(Exception ex) {
            System.out.println("\nERROR: " + ex.getMessage());
        }
        
        return intValue;
    }

}