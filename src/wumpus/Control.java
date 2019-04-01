/* Jonathan Elsner
 * 
 * Control.java
 * 
 * Defines the control object of Hunt The Wumpus, and interacts with all other aspects of the game.
 */
package wumpus;

import java.util.Scanner;

public class Control
{	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("To debug, type [debug], then the name of your class, otherwise, press enter.");
		System.out.print("Debug an object? ");
		String inputRecieved = in.nextLine();
		
		if(inputRecieved.startsWith("debug"))
		{
			String clazz = inputRecieved.split("\\s")[1];
			
			if(clazz.equalsIgnoreCase("control"))
			{
				Control.debug();
			}else if(clazz.equalsIgnoreCase("Cave"))
			{
				Cave.debug();
			}else if(clazz.equalsIgnoreCase("GUI"))
			{
				GUI.debugging();
			}else if(clazz.equalsIgnoreCase("Map"))
			{
				Map.debug();
			}else if(clazz.equalsIgnoreCase("Player"))
			{
				Player.debug();
			}else if(clazz.equalsIgnoreCase("Trivia"))
			{
				Trivia.debug();
			}
			
			return;
		}
	}
	
	public static void debug()
	{
		System.out.println("Debug test");
	}
	
	// Game Objects //
	
	/* These will most likely be transferred from object to object in parameters of functions,
	 * so when one object needs heavy use of another for a certain task, the object will receive
	 * the necessary object in the parameters of the method that carries out that task.
	 * 
	 * e.g. If the Cave object needs to use the GUI object to draw some stuff, it will be transfered
	 * to it by calling caveObject.drawStuff(guiObject); from a method in this class.
	 */
	
	private Cave caveObject; // The Cave Object for the current game instance
	
	private GUI guiObject; // The GUI Object for the current game instance
	
	private Map mapObject; // The Map object for the current game instance
	
	private Player playerObject; // The Player object for the current game instance
	
	private Trivia triviaObject; // The Trivia object for the current game instance
	
	// Constructs the object
	// IDK, maybe this object should be static. TODO?
	public Control()
	{
	}
	
	// Start a new game for the player to play
	public void newGame()
	{
	}
	
	// Let's user see high scores
	// Don't have a score object yet, so I define and use a generic for now :P
	public <Score> Score[] getScores()
	{
		return null;
	}
	
	// Move the player
	public void movePlayer(MovementDirection dir)
	{
	}
	
	// The player enters the same room as the Wumpus
	public void foundWumpus()
	{
	}
	
	// The player enters a room with bats
	public void foundBats()
	{
	}
	
	// The player enters a room with a bottomless pit
	public void foundPit()
	{
	}
	
	// The player kills the wumpus
	public void killedWumpus()
	{
	}
	
	// The game has ended, because the player has either killed the wumpus, or died
	// Specify true if the wumpus has been killed
	public void endGame(boolean wumpusKilled)
	{
		
	}
}
