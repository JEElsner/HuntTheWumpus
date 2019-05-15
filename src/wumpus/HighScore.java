package wumpus;
import java.io.FileWriter;

public class HighScore
{
	private int score;
	
	public static void debug()
	{
		
	}
	
	public static void main(String args[])
	{
		try 
		{
			FileWriter f = new FileWriter("test.text");
			f.write("Writing a message");
			f.close();

		}
			catch(Exception e) {System.out.println(e);}
		System.out.println("?");
	}
	
	
	
	
	
}
