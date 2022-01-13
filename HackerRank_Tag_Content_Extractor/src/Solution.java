import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution{
	public static void main(String[] args){
		
		//Scanner in = new Scanner(System.in);
		//int testCases = Integer.parseInt(in.nextLine());
		//while(testCases>0){
			// String line = in.nextLine();
			String line = "<h1>had<h1>public</h1></h1>";
			
			ArrayList<String> myArrayList = new ArrayList<>();
			
			boolean foundAMatch = false;
			int tagClosePos;
			int textClosePos;
			String tag;
			String text;
			String openTag, closeTag;
			
			for(int i = 0; i < line.length(); i++) {

				if(line.charAt(i) == '<') { // found a new tag
					tagClosePos = line.indexOf('>', i);
					tag = line.substring(i, tagClosePos + 1);
					i = tagClosePos;
					myArrayList.add(tag);
				} else // no tag found, start recording text
				{
					textClosePos = line.indexOf('<', i);
					text = line.substring(i, textClosePos);
					i = textClosePos - 1;
					myArrayList.add(text);
				}

			}
			
			/*
			for(int i = 0; i < myArrayList.size(); i++) {
				System.out.println(myArrayList.get(i));
			}
			*/
			
			//System.out.println(line);
			
			for(int i = 0; i < myArrayList.size(); i++) {
				openTag = "x";
				closeTag = "";
				// look for text
				if(myArrayList.get(i).charAt(0) != '<') { // not a tag, so must be text
					text = myArrayList.get(i);
					//System.out.println("found text " + text + " at position " + i);
					for(int j = i; j >= 0; j--) { // start looking backwards for opening tags
						//System.out.println("searching at position " + j);
						if((myArrayList.get(j).charAt(0) == '<') && (myArrayList.get(j).charAt(1) != '/')) { //found an opening tag
							openTag = myArrayList.get(j);
							//System.out.println("found an opening tag" + openTag);
							openTag = openTag.replace("<",""); 
							openTag = openTag.replace(">","");
							for(int k = i; k < myArrayList.size(); k++) { // now look for a closing tag
								if(myArrayList.get(k).substring(0,2).equals("</")) { //found a closing tag
									closeTag = myArrayList.get(k);
									//System.out.println("found a closing tag" + closeTag);
									closeTag = closeTag.replace("</",""); 
									closeTag = closeTag.replace(">","");
									
									k = myArrayList.size(); //stop the search
								}
							}
						
						}
					}

					//System.out.println(openTag);
					//System.out.println(closeTag);
					if(openTag.equals(closeTag)) {
						System.out.println(text);
						foundAMatch = true;	
					} 

				}

				
			}
		
			
			if(!foundAMatch) System.out.println("None");

			
          	//Write your code here
			//
			//opening tag opening tag is okay, but opening tag, text, opening tag is not. Nor is closing tag, text, closing tag - ignore that text
			//
			//Loop through, look for opening "<". Read next characters until closing ">". Record it somehow (ArrayList? HashSet? new Class/Object?)
			//Look for next "<". Read next characters until closing ">"
			//Is there a backslash? If not, it's another opening tag. Need to keep going until reach a closing tag. 
			//Keep a count of the number of opening tags
			//Once a closing tag is found, compare the characters to the last opening tag - are they the same? If not, then it's not a valid tag
			//Keep going until all the closing tags are found. If all the closing tags match with the opening tag, then it's a valid tag. Print the text between the tags
			
			
			//testCases--;
		}
}



