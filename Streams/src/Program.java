import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Program {
	public static void main(String[] args)
	{
		Person p1 = new Person(34, "Bob", 72.6);
		Person p2 = new Person(42, "Joe", 77.3);
		Person p3 = new Person(17, "Xavier", 66.3);
		
	
		Person[] s = new Person[]{p1, p2, p3};
	
		
		List<Person> a = Arrays.asList(s);
		a = capMe(a);
		
		a.forEach(Person -> System.out.println(Person));
		
	}
	
	public static List<Person> capMe(List<Person> s) {
		
		List<Person> capitalized = s.stream()
				.sorted(Comparator.comparing(person -> person.getAge()))
				//.filter(person -> person.getAge() > 40)
				.collect(Collectors.toList());
		
		return capitalized;
	}
}