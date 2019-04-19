/* Jonathan Elsner
 * 
 * Control.java
 * 
 * Defines the control object of Hunt The Wumpus, and interacts with all other aspects of the game.
 * 
 * Since different methods in this class occur on different threads, each method is annotated with
 * the thread where it should execute
 */
package wumpus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingWorker;

import gui.ControlToGUIUpdate;
import gui.GUI;
import gui.GUIToControlUpdate;

public class Control extends SwingWorker<Void, ControlToGUIUpdate>
{	
	// Thread: Initial thread
	public static void main(String[] args)
	{
		// Debug Code
		
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
				GUI.debug();
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
		} // End "debug" if
		
		// Actual Code
		
		controlObject = new Control();
		controlObject.startGame();
	}
	
	// Thread: Initial
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
	
	private static Control controlObject; // The Control Object manages the interactions between all of the other objects
	// This one is static because it is the object that owns all the others, and needs not have reference to itself
	
	private Cave caveObject; // The Cave Object for the current game instance
	
	private GUI guiObject; // The GUI Object for the current game instance
	
	private Map mapObject; // The Map object for the current game instance
	
	private Player playerObject; // The Player object for the current game instance
	
	private Trivia triviaObject; // The Trivia object for the current game instance
	
	// --- Instance Data --- //
	
	private ArrayList<GUIToControlUpdate> guiMessages; // Used to receive notifications of events happening on the gui
	
	// Constructs the object
	// Thread: Initial
	public Control()
	{
		guiMessages = new ArrayList<GUIToControlUpdate>();
		guiObject = new GUI(this); // TODO this may cause an error because control isn't fully instantiated yet
	}
	
	// Thread: Initial
	public void startGame()
	{
		guiObject.startGUI(); // Start the GUI
		
		this.execute(); // Begin the worker thread to support the GUI in the background
		// TODO, is this no bueno, and should be done in the EDT
		/* Oh yeah, this may be no bueno, because the process method should be called in the EDT,
		 * and that may actually be the only location where it works properly, and is called frequently
		 */
	}
	
	// Thread: Worker
	protected Void doInBackground() throws Exception
	{	
		while(true)
		{
			synchronized(guiMessages)
			{
				try
				{
					guiMessages.wait();
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Notification Recieved");
			}
		}
	}
	
	// Process results from the SwingWorker worker thread
	// Thread: EDT
	// TODO, hand all of this off to Logan, in the GUI class? Maybe
	protected void process(List<ControlToGUIUpdate> updates)
	{
		guiObject.processControlUpdates(updates);
	}
	
	// Send a message from the gui to the worker thread
	// Thread: EDT
	public void sendMessage(GUIToControlUpdate message)
	{
		synchronized(guiMessages)
		{
			guiMessages.add(message);
			guiMessages.notifyAll();
		}
	}
	
	// Start a new game for the player to play
	// Thread: Worker
	public void newGame()
	{
	}
	
	// Let's user see high scores
	// Thread: Worker
	// Don't have a score object yet, so I define and use a generic for now :P
	public <Score> Score[] getScores()
	{
		return null;
	}
	
	// Move the player
	
	// Thread: Worker
	public void movePlayer(MovementDirection dir)
	{
	}
	
	// The player enters the same room as the Wumpus
	// Thread: Worker
	public void foundWumpus()
	{
	}
	
	// The player enters a room with bats
	// Thread: Worker
	public void foundBats()
	{
	}
	
	// The player enters a room with a bottomless pit
	// Thread: Worker
	public void foundPit()
	{
	}
	
	// The player kills the wumpus
	// Thread: Worker
	public void killedWumpus()
	{
	}
	
	// The game has ended, because the player has either killed the wumpus, or died
	// Specify true if the wumpus has been killed
	// TODO rename to lostGame(), because it should not be similar to startGame, whose purpose is not an antonym of endGame
	public void endGame(boolean wumpusKilled)
	{
		
	}
}
