/* Jonathan Elsner
 * 
 * Control.java
 * 
 * Defines the control object of Hunt The Wumpus, and interacts with all other aspects of the game.
 */
package wumpus;

public class Control
{
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
	// Again, don't have a Direction object yet, so using a generic again.
	/* I imagine direction could just be an int, as an id for the direction
	 * 
	 * OR
	 * 
	 * We could use ints that represent the difference between the current room and the room
	 * traveled to. I made a diagram. Anyway, then the code would just be as simple as:
	 * 
	 * Player.setRoom((Player.getRoom() + difference) % 29 + 1);
	 * 														 ^---
	 * Tangent: I wish the room numbering would start at 0, so we could get rid of this stupid +1
	 */
	public <Direction> void movePlayer(Direction dir)
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
