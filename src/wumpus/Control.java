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

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.GUI;
import gui.Update;
import gui.UpdateType;

public class Control extends SwingWorker<Void, Update>
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
			
			in.close();
			return;
		} // End "debug" if
		
		in.close();
		
		// Actual Code
		
		controlObject = new Control();
		controlObject.startControl();
	}
	
	// Thread: Initial
	public static void debug()
	{
		System.out.println("Debug test".contains(null));
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
	
	private ArrayList<Update> guiMessages; // Used to receive notifications of events happening on the gui
	
	// Constructs the object
	// Thread: Initial
	public Control()
	{
		guiMessages = new ArrayList<Update>();
		guiObject = new GUI(this); // TODO this may cause an error because control isn't fully instantiated yet
	}
	
	// Thread: Initial
	public void startControl()
	{
		guiObject.startGUI(); // Start the GUI
		
		SwingUtilities.invokeLater(this::execute); // Begin the worker thread to support the GUI in the background
		// I'm pretty sure SwingWorker.execute() must be called on the EDT, so hence the invokeLater
	}
	
	// Thread: Worker
	protected Void doInBackground() throws Exception
	{	
		while(true)
		{
			// IDK if all of this code, especially the Update handling, should
			// occur within the synchronized block
			synchronized(guiMessages)
			{
				try
				{
					guiMessages.wait(); // Wait for a new update to be posted, then continue once notified
				} catch (InterruptedException e)
				{
					System.err.println("Control worker interrupted:");
					e.printStackTrace();
				}
				
				while(guiMessages.size() > 0)
				{
					Update msg = guiMessages.get(0); // Get the first message to occur
					
					// Ensure the message hasn't already been processed. If it has, get rid of it
					if(msg.isUpdateProcessed())
						guiMessages.remove(0);
					
					// --- Handle the Update --- //
					
					try
					{	
						switch(msg.getType())
						{
						case DEBUG:
							System.out.println("Recieved debug update from GUI: " + msg.getData().toString());
							break;
							
						case NEW_GAME:
							newGame();
							break;
							
						case GET_HIGH_SCORE:
							break;
							
						case GET_TRIVIA:
							break;
							
						case MOVE:
							movePlayer((MovementDirection) msg.getData());
							break;
							
						case PURCHASE_ARROW:
							break;
							
						case PURCHASE_SECRET:
							break;
							
						case SHOOT_ARROW:
							break;
							
						default:
							// TODO change to be more durable
							throw new IllegalArgumentException("Invalid Control Update: " + msg.getType());
						}
					}catch(ClassCastException ex)
					{
						System.err.println("Invalid data for Update: " + msg.getType().toString());
						System.err.println(ex.getMessage());
					}
					
					// --- End of handling Update --- //
					
					msg.finishProcessing(); // Indicate that the message is done being processed
					// TODO necessary?
					
					guiMessages.remove(0); // Remove the update, since it has been processed
				}
			}
		}
	}
	
	// Process results from the SwingWorker worker thread
	// Thread: EDT
	protected void process(List<Update> updates)
	{
		guiObject.processControlUpdates(updates);
	}
	
	// Send a message from the gui to the worker thread
	// Thread: EDT
	public void sendMessage(Update message)
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
		// Create a new cave with different rooms and stuff
		// Give the current room the player is in to the GUI
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
		mapObject.movePlayer(dir); // Move the player to the new location
		publish(new Update(UpdateType.MOVE, false)); // Pass new room & Warnings to GUI
		
		checkForHazards();
		checkForWarnings();
		
		// TODO Warn about wumpus
	}
	
	private void checkForHazards()
	{
		int room = mapObject.getPlayerRoom();
		
		if(room == mapObject.getWumpusRoom())
		{
			foundWumpus();
		}else if(room == mapObject.getPitRoom() || room == mapObject.getPitRoom2())
		{
			foundPit();
		}else if(room == mapObject.getBatRoom() || room == mapObject.getBatRoom2())
		{
			foundBats();
		}
	}
	
	private void checkForWarnings()
	{
		int batWarnings = mapObject.CheckForBats();
		int pitWarnings = mapObject.CheckForPits();
		
		if(batWarnings > 0)
		{
			publish(new Update(UpdateType.BAT_WARNING, false, batWarnings));
		}
		
		if(pitWarnings > 0)
		{
			publish(new Update(UpdateType.PIT_WARNING, false, pitWarnings));
		}
	}
	
	// The player enters the same room as the Wumpus
	// Thread: Worker
	public void foundWumpus()
	{
		publish(new Update(UpdateType.ENCOUNTER_WUMPUS, false)); // Pass trivia questions with update?
		
		// Make the player answer trivia
		// If trivia correct, move wumpus
		// If trivia incorrect, end game
	}
	
	// The player enters a room with bats
	// Thread: Worker
	public void foundBats()
	{
		publish(new Update(UpdateType.ENCOUNTER_BAT, false)); // Pass trivia questions with update?
		
		// Make the player answer trivia?
		
		mapObject.flyAway();
		checkForHazards();
		checkForWarnings();
	}
	
	// The player enters a room with a bottomless pit
	// Thread: Worker
	public void foundPit()
	{
		publish(new Update(UpdateType.ENCOUNTER_PIT, false)); // Pass trivia questions with update?
		
		mapObject.fallIntoPit();
		checkForHazards();
		checkForWarnings();
	}
	
	// The player kills the wumpus
	// Thread: Worker
	public void killedWumpus()
	{
		endGame(true);
	}
	
	// The game has ended, because the player has either killed the wumpus, or died
	// Specify true if the wumpus has been killed
	// Thread: Worker
	public void endGame(boolean wumpusKilled)
	{
		if(wumpusKilled)
			publish(new Update(UpdateType.DISPLAY_WIN, false)); // Pass high scores?
		else
			publish(new Update(UpdateType.DISPLAY_LOSE, false)); // Pass high scores?
	}
}
