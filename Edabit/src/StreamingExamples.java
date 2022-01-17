import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamingExamples {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(isFourLetters(new String[]{"Ryan", "Kieran", "Jason", "Matt"})));
	}

	/*
	 * STREAMING
	 */

	//start with string, convert to hex & return as spaced out string
	public static String toHex(String str) {
		
		return str.chars()
				.mapToObj(Integer::toHexString)
				.collect(Collectors.joining(" "));
		
//		String result = "";
//		for(int i = 0; i < str.length(); i++) {
//			result += String.format("%04x", (int) str.charAt(i)).substring(2) + " ";
//		}
//		return result.trim();
	}
	
	// stream string array, filter and return new string array
	public static String[] isFourLetters(String[] s) {
		return Arrays.stream(s)
				.filter(str -> str.length() == 4)
				.toArray(size -> new String[size]);
	}

	//concatenate two int arrays
	public static int[] concat(int[] arr1, int[] arr2) {
		return IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
			.toArray();
	}
	
	//start with string, convert to hex & return as spaced out string
	public static String toHexv2(String str) {
		
		return Arrays.stream(str.split(""))
				.map(s->s.charAt(0))
				.map(Integer::toHexString)
				.collect(Collectors.joining(" "));
	}
	
	  public static boolean canAlternate(String str) {
		  
		  long numberOf1s = str.chars().filter(ch -> ch == '1').count();
		  long numberOf0s = str.chars().filter(ch -> ch == '0').count();
		  if(numberOf1s == 0 || numberOf0s == 0) return false;
		  return(Math.abs(numberOf1s - numberOf0s) <= 1);

	  }
	
	// get an average of the digits in a number. e.g., 666, average is 6 (6+6+6 / 3)
	//start with int, convert to string, stream as chars, convert to integer (deduct 48 from char value)
	// sum, then divide by length of string to get an average
	public static int mean(int a) {
		return String.valueOf(a)
				.chars()
				.map(ch -> ch - 48) // unicode value of zero is 48
				.sum() / Integer.toString(a).length();
		
//		return (int)String.valueOf(a).chars()
//				.map(Character::getNumericValue)
//				.average()
//				.orElse(0);
	}
	
	public static int howManyTimes(String msg) {
		return msg.chars()
				.map(ch -> ch - 96)
				.sum();
	  }
	
	//Given an array of 10 numbers, return the maximum possible total made by summing just 5 of the 10 numbers.
	public static int maxTotal(int[] nums) {
		
	    Optional<Integer> stream  = Arrays.stream(nums).boxed().sorted(Collections.reverseOrder()).limit(5).reduce(Integer::sum);
	    return stream.get();
		
		// int count = 0;
		// Arrays.sort(nums);
		// for(int i = nums.length-1; i > nums.length/2-1; i--) {
		// 	count += nums[i];
		// }
		// return count;
	}
	
	
	//stream objects / getter
//	public class Program {	
//		public static double getBudgets(Person[] persons) {
//			return Arrays.stream(persons)
//		            //.filter(number -> number % 2 == 0) //Filter even numbers.
//		            //.skip(1) //Skip the first filtered number.
//					.mapToDouble(person -> person.getBudget())
//					.sum();
//		}
//	}	
	
	//return array up to n where any numbers divisable by 4 are multiplied by 10
	public static int[] amplify(int num) {
		int[] arr = new int[num];
		for(int i = 0; i < num; i++) {
			arr[i] = (i+1)%4 == 0 ? (i+1) * 10 : i+1;
		}
		return arr;

		// .range is up to num+1, map if i modulo 4 is zero then stream i*10, otherwise (:) stream i, to array

		//return IntStream.range(1, num + 1).map(i -> (i % 4 == 0 ? i * 10 : i)).toArray();	
	}
	
	
	public static int[] findEvenNums(int num) {

		int[] result = new int[num/2];
		
		for(int i = 1; i < num/2+1; i++) {
			result[i-1] = i*2;
		}
		return result;
		//		return IntStream.iterate(2, x -> x + 2).limit(num/2).toArray();
		//		https://mkyong.com/java8/java-8-stream-iterate-examples/
	}
	
	// return highest digit from an int
	// start with int, convert to string, split using "", map back to in, return max, as int
	public class HighestDigit {
		  public static int highestDigit(int n) {
		    return Stream.of(Integer.toString(n).split("")).mapToInt(Integer::parseInt).max().getAsInt();
		  }
		}
	
	//stream - start with integer, convert to string, filter & sort
	public static int countOnes(int n) {
		return (int) Integer.toBinaryString(n).chars()
		.filter(ch -> ch == '1')
		.count();
	}
	

	//stream from an array of ints, sort and return 2nd value
	public static int secondLargest(int[] num) {
		//Arrays.sort(num);
		//return num[num.length-2];
		
		return IntStream.of(num).sorted().toArray()[num.length-2];

	}

	//Stream a String, sort & then return a String
	public static String AlphabetSoup(String s) {
		return s.codePoints()
				.sorted()  
				.collect(StringBuilder::new,
				           StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
//		return s.chars().sorted()
//				.mapToObj(ch->String.valueOf((char)ch))
//				.collect(Collectors.joining(""));
			
	}
	
	public static String spaceMeOut(String str) {
		return str.chars()
		.mapToObj(ch->String.valueOf((char)ch))
		.map(ch->ch + " ")
		.collect(Collectors.joining("")).trim();
	}
	
	//stream from an array of Strings, take first letter of each string, sort & return as String
	public static String societyName(String[] friends) {
		return Arrays.stream(friends)
		.map(ch -> ch.charAt(0))
		.sorted()
		.collect(StringBuilder::new,
		           StringBuilder::appendCodePoint, StringBuilder::append)
		.toString();
		
		// return Arrays.stream(friends)
		//	.map(str -> String.valueOf(str.charAt(0)))
		//	.sorted()
		//	.reduce("", String::concat);
		
	}
	

	
}
