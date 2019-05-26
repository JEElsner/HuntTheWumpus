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

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gui.GUI;
import gui.Update;
import gui.UpdateType;

public class Control extends SwingWorker<Void, Update>
{	
	
	public static volatile boolean debugging = false;
	
	// Thread: Initial thread
	public static void main(String[] args)
	{	
		// ============ Actual Code ================= //
		
		// Make a new control object and start it
		controlObject = new Control();
		controlObject.startControl();
		
		// ============ Debug Code ================== //
		
		Scanner in = new Scanner(System.in);
		System.out.println("To debug, type [debug], then the name of your class, otherwise, press enter.");
		
		while (true)
		{
			String inputRecieved = in.nextLine();
			// Check if the user wants to run debug code associated with each class
			if (inputRecieved.startsWith("debug"))
			{
				// get the name of the class the user wanted to debug, which is the second word in the command
				try
				{
					String clazz = inputRecieved.split("\\s")[1];

					// Find the desired class and debug it
					if (clazz.equalsIgnoreCase("control"))
					{
						Control.debug();
					} else if (clazz.equalsIgnoreCase("Cave"))
					{
						Cave.debug();
					} else if (clazz.equalsIgnoreCase("GUI"))
					{
						GUI.debug();
					} else if (clazz.equalsIgnoreCase("Map"))
					{
						Map.debug();
					} else if (clazz.equalsIgnoreCase("Player"))
					{
						Player.debug();
					} else if (clazz.equalsIgnoreCase("Trivia"))
					{
						Trivia.debug();
					}
				} catch (IndexOutOfBoundsException ex)
				{
					// This happens semi-often, so this is a pretty bad catch, but it's debug, so I don't really care
					
					debugging = !debugging;
					System.out.println("Debug Status: " + debugging);
				}
				
				// End "debug" if
			}else if(inputRecieved.equalsIgnoreCase("exit"))
			{
				in.close();
				System.exit(0);
			}
		} // End debug while
	}
	
	// Thread: Initial
	public static void debug()
	{
		Field control;
		try
		{
			control = Control.class.getDeclaredField("controlObject");
			control.setAccessible(true);
			
			Control controlObj = (Control) control.get(null);
			
			for(int i = 0; i < 1000; i++)
				controlObj.playerObject.buyArrows(true);
			
			controlObj.publish(new Update(UpdateType.GET_ARROWS, false, controlObj.playerObject.getArrows()));
			
			System.out.println(controlObj.playerObject.getArrows());
		} catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
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
		guiMessages = new ArrayList<Update>(); // The array through which messages from the GUI are sent
		
		// Initialize all the different game objects
		guiObject = new GUI(this); // REVIEW this may cause an error because control isn't fully instantiated yet
		// No other objects are instantiated yet, because they are used on a per-game basis
	}
	
	// Starts the running of the entire program
	// This is necessary because we don't want the entire game running in Control's constructor
	// Thread: Initial
	public void startControl()
	{
		guiObject.startGUI(); // Start the GUI
		
		SwingUtilities.invokeLater(this::execute); // Begin the worker thread to support the GUI in the background
		// I'm pretty sure SwingWorker.execute() must be called on the EDT, so hence the invokeLater
		
		// Read the high scores and send them to the GUI
		HighScore.readFile(null);
		publish(new Update(UpdateType.GET_HIGH_SCORE, false, HighScore.returnHighscore()));
	}
	
	/* Processes updates from the GUI & EDT
	 * This shouldn't be called anywhere, as it is started by all the behind-the-scenes stuff with
	 * SwingWorker
	 * 
	 * Uh, eclipse generated the stuff below, you should probably look at it:
	 * Thread: Worker(non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
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
					// Handle interrupt, IDK why it would be interrupted
					// Maybe the GUI closing and exiting the program?
					System.err.println("Control worker interrupted while waiting:");
					e.printStackTrace();
				} // End try for wait
				
				// Look at all the unprocessed messages in the queue from the GUI
				while(guiMessages.size() > 0)
				{
					Update msg = guiMessages.get(0); // Get the first message to occur
					
					// Ensure the message hasn't already been processed. If it has, get rid of it
					if(msg.isUpdateProcessed())
						guiMessages.remove(0);
					
					// --- Handle the Update --- //
					
					try
					{	
						// Handle the udpate based on its UpdateType
						switch(msg.getType())
						{
						case DEBUG:
							System.out.println("Recieved debug update from GUI: " + msg.getData().toString());
							throw new Exception("Test exception");
							//break;
							
						case NEW_GAME: // A new game has started
							newGame();
							break;
							
						case GET_HIGH_SCORE:
							break;
							
						case GET_TRIVIA:
							/* So thinking about the trivia, and the multi-threading, Control isn't going
							 * to wait in whatever method askTrivia() say, for an answer, it should likely keep going,
							 * and then be notified by a TRIVIA_RESPONSE Update or something, which is then processed and
							 * compared to the correct answer. I think this method goes beyond Control though, Trivia
							 * will have to keep track of the current question that was last asked. oh boy, help.
							 */
							break;
							
						case GIVE_ANSWER:
							answerTrivia((String) msg.getData());
							
						case MOVE: // The player has moved
							movePlayer((MovementDirection) msg.getData());
							break;
							
						case PURCHASE_ARROW: // The player has purchased an arrow
							purchaseArrow();
							break;
							
						case PURCHASE_SECRET: // The player has purchased a secret
							purchaseSecret();
							break;
							
						case SHOOT_ARROW: // The player has shot an arrow
							shootArrow((MovementDirection) msg.getData());
							break;
							
						// TODO Deal with the closing of the GUI and saving of high scores
							
						default: // In case we get a bad update
							// CHANGE change to be more durable
							throw new IllegalArgumentException("Invalid Control Update: " + msg.getType());
						} // End Switch
					}catch(ClassCastException ex)
					{
						// In case we get a bad update
						// This shouldn't happen because of the type checking when an Update is instantiated
						System.err.println("Invalid data for Update: " + msg.getType().toString());
						System.err.println(ex.getMessage());
					} // End Catch
					
					// --- End of handling Update --- //
					
					msg.finishProcessing(); // Indicate that the message is done being processed
					// REVIEW necessary?
					
					guiMessages.remove(0); // Remove the update, since it has been processed
				} // End while looping through guiMessages
			} // End synchronized
		} // End while(true)
	} // End doInBackground
	
	/* Runs on the EDT when doInBackground() finishes on the worker thread.
	 * 
	 * In this case, it is mostly used to catch exceptions that occur in doInBackground()
	 * (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	protected void done()
	{
		try
		{
			get();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
			System.exit(1); // CHANGE to fail softer when this happens. Right now, I just want to know if this exception is called, so I want it to fail hard
		} catch (ExecutionException e)
		{
			System.err.println("EXCEPTION in doInBackground():");
			e.getCause().printStackTrace();
			System.exit(1); // REVIEW if an error occurs, and the background task ends, the program can't continue, so it should fail hard
		}
	}
	
	// Process results from the SwingWorker worker thread
	// Thread: EDT
	protected void process(List<Update> updates)
	{
		// Forward the processing to be handled by the GUI class
		// Sue me, I'm lazy. Why do the work myself, when I can make Logan do it?
		guiObject.processControlUpdates(updates);
	}
	
	// Send a message from the GUI to the worker thread
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
		mapObject = new Map(); // Create a new map with new pit, bat, and wumpus locations
		
		// Create a new player with 100 coins, 3 arrows, 0 turns, and 0 score
		playerObject = new Player(100, 3, 0, 0);
		// Notify the GUI the number of coins and arrows has changed
		publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
		publish(new Update(UpdateType.GET_ARROWS, false, playerObject.getArrows()));
		
		// Create a new cave with different doors
		try
		{
			caveObject = new Cave();
		} catch (FileNotFoundException e)
		{
			System.err.println("Error creating new cave: ");
			e.printStackTrace();
		}
		
		// Create a new trivia object to keep track of trivia questions to be asked and answered
		triviaObject = new Trivia();
		
		// Debug / Cheat information
		/*System.out.println("Bat Rooms: " + mapObject.getBatRoom() + ", " + mapObject.getBatRoom2());
		System.out.println("Pit Rooms: " + mapObject.getPitRoom() + ", " + mapObject.getPitRoom2());
		System.out.println("Wumpus Room: " + mapObject.getWumpusRoom());*/
		
		// Publish the first Updates to tell the GUI where the player is and where they can move
		publish(new Update(UpdateType.MOVE, false, mapObject.getPlayerRoom()));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(mapObject.getPlayerRoom(), caveObject.getConnections(mapObject.getPlayerRoom()))));
	}
	
	// Let's user see high scores
	// Thread: Worker
	// Don't have a score object yet, so I define and use a generic for now :P
	public <Score> Score[] getScores()
	{
		return null; // TODO Huh, this generic guy is still around. That's pretty bad, we should get to removing him soon.
	}
	
	// Move the player
	// Thread: Worker
	public void movePlayer(MovementDirection dir)
	{
		// TODO check if there is a door to the room the player wants to move to
		
		// Increment the number of turns the player has taken (and give the player another coin)
		playerObject.countTurns();
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
		
		int playerRoom = mapObject.movePlayer(dir); // Move the player to the new location
		System.out.println("Room: " + playerRoom);
		
		publish(new Update(UpdateType.MOVE, false, playerRoom));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(playerRoom, caveObject.getConnections(playerRoom))));
		
		// Run checks for a new room
		checkForHazards();
		checkForWarnings();
		
		mapObject.moveWumpus();
	}
	
	/* Check whether the player's current room has any hazards in it, and handle them
	 * 
	 * Thread: Worker
	 */
	private void checkForHazards()
	{
		int room = mapObject.getPlayerRoom(); // Get the current room
		
		// See if the Wumpus is in the room
		if(room == mapObject.getWumpusRoom())
		{
			foundWumpus();
		}else if(room == mapObject.getPitRoom() || room == mapObject.getPitRoom2())
		{	// See if the room has a pit, and make the player fall in
			foundPit();
		}else if(room == mapObject.getBatRoom() || room == mapObject.getBatRoom2())
		{	// See if the room has bats, and make the player get carried away by bats
			foundBats();
		}
	}
	
	/* Check if warnings need to be sent to the GUI about hazards in surrounding rooms
	 * 
	 * Thread: Worker
	 */
	private void checkForWarnings()
	{
		// Get the number of bats & pits in the surrounding pits
		// I have no idea why I put these into variables first
		int batWarnings = mapObject.CheckForBats();
		int pitWarnings = mapObject.CheckForPits();
		
		// Handle the warnings appropriately
		publish(new Update(UpdateType.BAT_WARNING, false, batWarnings));
		publish(new Update(UpdateType.PIT_WARNING, false, pitWarnings));
		publish(new Update(UpdateType.WUMPUS_WARNING, false, mapObject.CheckForWumpus() ? 1 : 0));
	}
	
	// The player enters the same room as the Wumpus
	// Thread: Worker
	public void foundWumpus()
	{
		publish(new Update(UpdateType.ENCOUNTER_WUMPUS, false)); // Pass trivia questions with update?
		
		// Make the player answer trivia
		// If trivia correct, move wumpus
		// If trivia incorrect, end game
		// Tell the map the wumpus is awake
	}
	
	// The player enters a room with bats
	// Thread: Worker
	public void foundBats()
	{
		publish(new Update(UpdateType.ENCOUNTER_BAT, false)); // Pass trivia questions with update?
		
		// Make the player answer trivia?
		
		// Move the player & bats, then run checks for a new room
		int playerRoom = mapObject.flyAway();
		publish(new Update(UpdateType.MOVE, false, playerRoom));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(playerRoom, caveObject.getConnections(playerRoom))));
		
		checkForHazards();
		checkForWarnings();
	}
	
	// The player enters a room with a bottomless pit
	// Thread: Worker
	public void foundPit()
	{
		publish(new Update(UpdateType.ENCOUNTER_PIT, false)); // Pass trivia questions with update?
		
		// Move the player, then run checks for a new room
		mapObject.fallIntoPit();
		publish(new Update(UpdateType.MOVE, false, mapObject.getPlayerRoom()));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(mapObject.getPlayerRoom(), caveObject.getConnections(mapObject.getPlayerRoom()))));
		
		checkForHazards();
		checkForWarnings();
	}

	/* Handles the answer a player gives for a Trivia question
	 * 
	 * Thread: Worker
	 */
	private void answerTrivia(String answer)
	{
		Trivia.answer(answer);
		
		// TODO check if enough trivia questions have been asked for the type of trivia event
		// If so, do the reward of the event
		// Otherwise, ask another trivia question
	}
	
	/* Purchases a secret for the player, and tells the GUI what it is
	 * 
	 * Thread: Worker
	 */
	private void purchaseSecret()
	{
		// TODO Implement Purchase Secret
		// Use coin
		// Ask trivia?
		// Get secret
		publish(new Update(UpdateType.PURCHASE_SECRET, false, "Secret"));
	}

	/* Method that purchases another arrow for the player using coins
	 * and tells the GUI about it
	 * 
	 * Thread: Worker
	 */
	private void purchaseArrow()
	{
		// TODO Implement Purchase Arrow
		// Use coin
		// Ask trivia?
		// tell player they've got another arrow
		publish(new Update(UpdateType.PURCHASE_ARROW, false, playerObject.buyArrows(true))); // return new number of arrows
	}
	
	// The player shoots an arrow
	// Thread: Worker
	private void shootArrow(MovementDirection dir)
	{	
		// FIXME The player presses the 'shoot arrow' button, moves, then presses a direction to shoot
		
		// Make the player lose an arrow, and update the GUI
		publish(new Update(UpdateType.SHOOT_ARROW, false, playerObject.shootArrows()));
		
		if(Map.getNearbyRoom(mapObject.getPlayerRoom(), dir) == mapObject.getWumpusRoom())
			killedWumpus();
		else
		{
			publish(new Update(UpdateType.ARROW_MISS, false, playerObject.getArrows()));
			
			if(playerObject.getArrows() == 0 && playerObject.getCoins() == 0)
				endGame(false);
		}
	}
	
	// The player kills the wumpus
	// Thread: Worker
	public void killedWumpus()
	{
		// TBH, IDK if anything else needs to go here...
		
		endGame(true); // Well, the wumpus is dead, so the game ends
	}
	
	// The game has ended, because the player has either killed the wumpus, or died
	// Specify true if the wumpus has been killed
	// Thread: Worker
	public void endGame(boolean wumpusKilled)
	{
		/* TODO Add tracking of player name in Player, and use update to transmit name */
		HighScore.addScore("Stevo", playerObject.finalScore());
		
		if(wumpusKilled) // If the wumpus was killed, the game is won
			publish(new Update(UpdateType.DISPLAY_WIN, false, playerObject.finalScore())); // Pass high scores?
		else // The the wumpus wasn't killed the game is lost
			publish(new Update(UpdateType.DISPLAY_LOSE, false, playerObject.finalScore())); // Pass high scores?
		
		publish(new Update(UpdateType.GET_HIGH_SCORE, false, HighScore.returnHighscore()));
	}
}
