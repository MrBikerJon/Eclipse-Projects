
public class Person {
	private int age;
	private String name;
	private double weight;
	
	public Person(int age, String name, double weight)
	{
		this.age = age;
		this.name = name;
		this.weight = weight;

	}

	public int getAge()
	{
		return age;
	}
	
	public int getName()
	{
		return age;
	}

	public int getWeight()
	{
		return age;
	}
	
	@Override
	public String toString()
	{
		return ("Age: " + age + " Name: " + name + " Weight: " +
				weight);
	}


}
