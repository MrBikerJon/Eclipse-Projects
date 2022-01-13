import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
    	String NandM;
    	String operationToPerform;
    	Scanner s = new Scanner(System.in);
    	int N = 0;
    	int M = 0;
    	String C1, C2;
    	int Cint1, Cint2;
    	
    	NandM = s.nextLine();

        String[] tokens=NandM.split(" ");  
    	
    	N = Integer.parseInt(tokens[0]); // N is length of the bitsets (number of bits)
    	M = Integer.parseInt(tokens[1]); // M is number of operations to perform
    	
    	String[] myArray = new String[M];
    	
    	// get the instructions & store into myArray
    	for(int i = 0; i < myArray.length; i++) {
    		myArray[i] = s.nextLine();
    	}
    	
    	BitSet B1 = new BitSet(N);
    	BitSet B2 = new BitSet(N);
    	
    	//loop through myArray
    	for(int i = 0; i < M; i++) {
    		
    		String[] tokens2=myArray[i].split(" ");  
    		operationToPerform = tokens2[0]; // get the first two characters - this is the operation to perform
    	
    		C1 = tokens2[1]; // first number
    		C2 = tokens2[2]; // second number
    		Cint1 = Integer.parseInt(C1); // first number
    		Cint2 = Integer.parseInt(C2); // second number
    		
    		//System.out.println(operationToPerform);
    		//System.out.println("C1 = " + C1 + ", C2 = " + C2);
    		
    		switch(operationToPerform) {
    		case "SET": // set
    			if(C1.equals("1")) { // set operation on B1
    				B1.set(Cint2, true);			
    			} else {
    				B2.set(Cint2, true);
    			}
    			break;
    		case "FLIP":
    			if(C1.equals("1")) { // flip bit on B1
    				B1.flip(Cint2);
    			} else {
    				B2.flip(Cint2);
    			}
    			break;
    		case "AND":
    			if(C1.equals("1")) { // 
    				B1.and(B2);
    			} else {
    				B2.and(B1);
    			}
    			break;
    		case "OR":
    			if(C1.equals("1")) { // 
    				B1.or(B2);
    			} else {
    				B2.or(B1);
    			}
    			break;
    		case "XOR":
    			if(C1.equals("1")) { // 
    				B1.xor(B2);
    			} else {
    				B2.xor(B1);
    			}
    			break;
    		default:
    			break;
    		}
   
    	System.out.println(B1.cardinality() + " " + B2.cardinality());
    	
    	}
    	
    	// finished loops through, now clear out for the next input
    	B1.clear();
    	B2.clear();
    	/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    
}