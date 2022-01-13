import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class DirectoryPath {
	
	public static void main(String[] args)
	{
		String a = "C:/Users/johnsmith/Music/Beethoven_5.mp3";
		System.out.println(getFilename(a));
		
		String b = "";
		char c = '#';
		System.out.println(replaceVowels(b, c));
		
	}
	
	public static String replaceVowels(String str, char ch) {
		return str.replaceAll("(?i)[aeiou]", "");

	}

	
	public static String getFilename(String path) {

		Pattern pattern = Pattern.compile("(\\w*\\.\\w{3})");

		Matcher matcher = pattern.matcher(path);
		matcher.find();
		//if(matcher.find()) 
		return path.substring(matcher.start());

  }
}