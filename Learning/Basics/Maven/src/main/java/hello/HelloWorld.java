package hello;

import org.joda.time.LocalTime;

import static java.lang.System.out;

class HelloWorld
{
	public static void main(String[] args)
	{
		LocalTime currentTime = new LocalTime();
		
		out.println("The current local time is: " + currentTime);
		
		out.println(new Greeter().sayHello());
	}
}		
