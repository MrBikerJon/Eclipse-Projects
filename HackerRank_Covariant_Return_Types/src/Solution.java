import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Complete the classes below
class Flower {
	
	public String whatsYourName()
	{
		return "I have many names and types.";
	}
}

class Jasmine extends Flower {
	
	@Override
	public String whatsYourName()
	{
		return "Jasmine";
	}
}

class Lily extends Flower {
	
	@Override
	public String whatsYourName()
	{
		return "Lily";
	}
}

class Region {
	
	public Flower yourNationalFlower()
	{
		Flower flower = new Flower();
		return flower;
	}
}

class WestBengal extends Region {
	
	@Override
	public Jasmine yourNationalFlower()
	{
		Jasmine jasmine = new Jasmine();
		return jasmine;
	}
}

class AndhraPradesh extends Region {
	
	@Override
	public Lily yourNationalFlower()
	{
		Lily lily = new Lily();
		return lily;
	}
}

