package wumpus;

import java.util.Scanner;

/* Class to facilitate ease of debugging.
 * 
 * It was meant to save time, but it had the opposite effect, because we haven't used it yet :P
 * 
 * Honestly, I can't really remember what I was trying to do here, so that's why there aren't any more
 * comments, it's fairly self explanatory if you must read it - i hope. :/
 * 
 * I should probably just delete this class, what a failure it is
 */

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
