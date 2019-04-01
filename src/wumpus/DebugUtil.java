package wumpus;

import java.util.Scanner;

public class DebugUtil
{
	private static Scanner input;
	
	public static void init(Scanner in)
	{
		input = in;
	}
	
	public static void error(String message)
	{
		System.err.print(message);
		input.nextLine();
	}
	
	public static void error(String message, Object expected, Object result)
	{
		System.err.println(message);
		System.err.printf("Expected: %s; Actual: %s", expected, result);
		input.nextLine();
	}
}
