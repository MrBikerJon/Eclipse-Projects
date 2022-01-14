import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//
//
//
///


public class Challenge {
	
	public static void main (String[] args) throws Exception {
		System.out.println(rev(1054));
	}

	//reverse an int and return as a string
	public static String rev(int n) {
		StringBuilder s = new StringBuilder(Integer.toString(Math.abs(n)));
		return s.reverse().toString();
		
		//even shorter:
		//return new StringBuilder(Math.abs(n) + "").reverse().toString();
		
	}
	
	
	  public static int highestDigit(int n) {
		  String str = Integer.toString(n);
		  int result = 0;
		  for(int i = 0; i < str.length(); i++) {
			  if(str.charAt(i) > result) result = str.charAt(i);
		  }
		  // above returns a unicode. unicode of zero is 48
		  // therefore subtract 48 from result to get actual number
		  return result-48;
	  }	
		
	public static boolean timeForMilkAndCookies(GregorianCalendar date) {
			return (date.get(GregorianCalendar.MONTH) == 11 && date.get(GregorianCalendar.DAY_OF_MONTH) == 24);
		}
	
	public static String tpChecker(int people, int tp) {
		    // paper used per day = people * 57
		  // days of paper left = 500 * tp / above calc
		  final int SHEETS_PER_ROLL = 500, SHEETS_PER_DAY = 57;
		  int daysSupply = ((SHEETS_PER_ROLL * tp)/(SHEETS_PER_DAY * people));
		  return daysSupply < 15? "Your TP will only last " + daysSupply + " days, buy more!" :
			  "Your TP will last " + daysSupply + " days, no need to panic!";
	  }
	
	public static int halveCount(int a, int b) {
		int count = 0;
		while (a > b) {
			a /= 2;
			count++;
		}
		return count-1;
	  }
	
	public static boolean validatePIN(String s) {
		return s.matches("\\d{4}||\\d{6}");
	}
	
	public static int[] cumulativeSum(int[] nums) {
		//create new int array to hold new values
		int[] newNums = new int[nums.length];
		//store the first element in nums as first element in new array
		newNums[0] = nums[0];
		//loop through nums, starting at pos 1
		for(int i = 1; i < nums.length; i++) {
		//add element in current pos to element in new array pos-1, and store in new array at current pos
			newNums[i] = newNums[i-1] + nums[i];
		}
		//return new array
		return newNums;
	}
	
	public static int nthSmallest(int[] arr, int n) {
		Arrays.sort(arr);
		return n>arr.length? -1 : arr[n];
	}
	
	public static boolean largestSwap(int num) {
		return num/10 > num%10;
		
		//int a = num/10;
		//int b = num%10;
		//System.out.println(a + " " + b);
		//return false;
	}


	class Person{
		private String name;
		private int age;
		private double budget;
			
		public Person(String name, int age, double budget) {
			super();
			this.name = name;
			this.age = age;
			this.budget = budget;
		}
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public double getBudget() {
			return budget;
		}
		public void setBudget(double budget) {
			this.budget = budget;
		}
	}


	
	
	  public static String getMiddle(String word) {
			int index = word.length()/2;
			
			//if the length of word is odd, divide length by two and return the middle char
			if(word.length()%2 == 1) return word.substring(index, index+1);
			
			//otherwise, divide length by two and return the two values either side
			//(length divided by two, and length divided by two minus one)
			return word.substring(index-1, index+1);
	  }
	
	public static String indexShuffle(String str) {
		String evens = "", odds = "";
		for(int i = 0; i < str.length(); i++) {
			if(i%2 == 0) evens += str.charAt(i); else odds+= str.charAt(i);
		}
		return evens + odds;
	}

	
	  public static int Fibonacci(int a)
		{
			int result = 1, priorResult = 0, priorPriorResult = 0;
			for(int i = 0; i < a; i++) {
				priorPriorResult = priorResult;
				priorResult = result;
				result = priorResult + priorPriorResult;
			}
			return result;
		}
	
	public static int howManyTimes(String msg) {
		return msg.chars()
				.map(ch -> ch - 96)
				.sum();
	  }
	
	  public static boolean matchLastItem(String[] t) {
			String s = "";
			for(int i = 0; i < t.length-1; i++) {
				s+=t[i];
			}
			return s.equals(t[t.length-1]);
			
			//return String.join("", Arrays.copyOfRange(t, 0, t.length-1)).equals(t[t.length-1]);
			
			//copyOfRange: creates a copy of elements, within a specified range of the original array.
			//Syntax : public static int[] copyOfRange(int[] original_array, int from_index, int to_index) 
	  }
	
	public static int endCorona(int recovers, int newCases, int activeCases) {
		return (int) Math.ceil((float) activeCases/(recovers-newCases));
	}


	//reverse sort an array
	public static int incrementToTop(int[] arr) {
		Arrays.sort(arr);
		int count = 0;
		for(int i = arr.length-1; i >=0; i--) {
			count += arr[arr.length-1]-arr[i];
		}
		return count;
	}
	
	/*
	 * --------------------------
	 */
	
	public static String spaceMeOut1(String str) {
		String result = "";
		char charArray[] = str.toCharArray();
		for(char c : charArray) {
			result += c + " ";
		}
		return result.substring(0, result.length()-1);
		
	}

	
	public static String sevenBoom(int[] arr) {
		String str = Arrays.toString(arr);
		return str.contains("7") ? "Boom!" : "there is no 7 in the array";
		//return Arrays.toString(arr).contains("7") ? "Boom!" : "there is no 7 in the array";
	}

	public static String intOrString(Object var) {
		if(var instanceof String) return "str"; return "int";
	}
	
	  public static boolean canAlternate(String str) {
		  
		  long numberOf1s = str.chars().filter(ch -> ch == '1').count();
		  long numberOf0s = str.chars().filter(ch -> ch == '0').count();
		  if(numberOf1s == 0 || numberOf0s == 0) return false;
		  return(Math.abs(numberOf1s - numberOf0s) <= 1);

	  }
	
	
	public static String add(String a, String b) {
		try {
			return (Integer.parseInt(a) + Integer.parseInt(b))+"";
		}
		catch(Exception e)
		{
			return "Invalid Operation";
		}
	}
	
	public static double[] minMax(double[] arr) {
		Arrays.sort(arr);
		return new double[]{arr[0], arr[arr.length]-1};
	}
	
	
	public static int[] hashPlusCount(String s) {
		int hashes = (int) s.chars().filter((x) -> { 
			return x == '#';
			}).count();
		return new int[] {hashes, s.length() - hashes};
	}
	
	/*
	 * PATTERN MATCHER
	 */
	
	public static int firstVowel(String str) {		
		Pattern pattern = Pattern.compile("(?i)[aeiou]");
		Matcher matcher = pattern.matcher(str);
		matcher.find();
		return matcher.start();
	}
	
	//See comment for simpler regex matching...
	public static boolean isValidZipCode(String zip) {
		Pattern pattern = Pattern.compile("[0-9]{5}");
		Matcher matcher = pattern.matcher(zip);
		return matcher.matches();
		
		// return zip.matches("[0-9]{5}");
	}
	
	public static int getDecimalPlaces(String num) {
		Pattern pattern = Pattern.compile("(?<=\\.)[\\d]*");
		Matcher matcher = pattern.matcher(num);
		return matcher.find()? matcher.end() - matcher.start() : 0;
		
		//return num.contains(".") ? (num.length() - num.indexOf(".") - 1) : 0;
	  }
	

	public static boolean variableValid(String variable) {
		Pattern pattern = Pattern.compile("(^[^a-zA-Z]|\\s)");
		Matcher matcher = pattern.matcher(variable);
		return matcher.find()? false : true;
			
		//	return variable.matches("[a-zA-z][0-9a-zA-z]*");
	  }


	/*
	 *  MANIPULATE STRINGS
	 */
	
	//take a string and replace all but the last 4 digits with *
	public static String cardHide(String card) {
		return card.substring(0, card.length()-4).replaceAll("(\\d)", "*") + card.substring(card.length()-4);
	
		//return card.replaceAll(".(?=.{4})", "*");  --- better Regex (look behind)

	}
	
	// split a string using a delimiter (".") & return part of it
	public static String retrieveMajor(String semver) {
		String s[] = semver.split("\\.");
		return s[0];
		
		//return semver.split("\\.")[0];
	}

	public static String joinPath(String portion1, String portion2) {
		//Pattern pattern = Pattern.compile("[a-zA-Z0-9");
		//Matcher matcher1 = pattern.matcher(portion1);
		//Matcher matcher2 = pattern.matcher(portion2);
		return portion1.replaceAll("/", "") + "/" + portion2.replaceAll("/", "");  
	}
	
	//format a string (replace characters, with regex matching)
	public static String convertBinary(String str) {
		return str.replaceAll("[a-mA-M]", "0").replaceAll("[n-zN-Z]", "1");  
	}

	
	//reverse a string
	public static boolean checkPalindrome(String str) {
		String newStr = "";
		for(int i = str.length()-1; i >= 0; i--) {
			newStr += str.charAt(i);
		}
		return newStr.equals(str);
		
		//StringBuilder s = new StringBuilder(str);
		//return s.reverse().toString().equals(str);
	}
	
	//format a string (add characters, using regex)
	public static String formatPhoneNumber(int [] nums) {
		return Arrays.toString(nums).replaceAll("\\[|\\]|,|\\s", "")
				.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
	}
			
}

