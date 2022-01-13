import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
    	int x = 0;
    	int y = 0;
    	boolean exceptionFound = false;
    	
    	Scanner scanner = new Scanner(System.in);
    	try {
    		x = scanner.nextInt();
    		//y = scanner.nextInt();
    	}
    	catch (InputMismatchException e){

    		System.out.println("java.util.InputMismatchException");
    		exceptionFound = true;
    	}
    	//catch (Exception e){
    	//	System.out.println(e);
    	//	exceptionFound = true;
    	//}
    	
    	if(!exceptionFound) { try {
    		//x = scanner.nextInt();
    		y = scanner.nextInt();
    	}
    	catch (InputMismatchException e){

    		System.out.println("java.util.InputMismatchException");
    		exceptionFound = true;
    	}
    	}
    	//catch (Exception e){
    	//	System.out.println(e);
    	//	exceptionFound = true;
    	//}
    	
    	if(!exceptionFound) {
    		try {
    		System.out.println(x/y);
    	}
    	catch(ArithmeticException e) {
    		System.out.println(e);
    		
    	}
    }

    }
    
}


