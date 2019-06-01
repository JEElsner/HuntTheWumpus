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
					} else if(clazz.equalsIgnoreCase("high score") || clazz.equalsIgnoreCase("highscore"))
					{
						HighScore.debug();
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
		System.out.println();
		System.out.println("  Gamestate readout  ");
		System.out.println("=====================");
		System.out.println();
		
		System.out.println("Map");
		System.out.println("Wumpus: " + controlObject.mapObject.getWumpusRoom());
		System.out.println("Bats: " + controlObject.mapObject.getBatRoom() + ", " + controlObject.mapObject.getBatRoom2());
		System.out.println("Pits: " + controlObject.mapObject.getPitRoom() + ", " + controlObject.mapObject.getPitRoom2());
		System.out.println("Player: " + controlObject.mapObject.getPlayerRoom());
		System.out.println();
		
		System.out.println("Player - " + controlObject.playerObject.getName());
		System.out.println("Turns: " + controlObject.playerObject.getTurns());
		System.out.println("Coins: " + controlObject.playerObject.getCoins());
		System.out.println("Arrows: " + controlObject.playerObject.getArrows());
		System.out.println();
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
		// Temporary variable to pass to newGame
		String name = "NAME_UNSPECIFIED";
		
		while(true)
		{						
			// IDK if all of this code, especially the Update handling, should
			// occur within the synchronized block
			synchronized(guiMessages)
			{					
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
							newGame((Integer) msg.getData(), name);
							break;
							
						case PLAYER_NAME:
							name = (String) msg.getData();
							break;
							
						case GET_HIGH_SCORE:
							break;
							
						case GIVE_ANSWER:
							answerTrivia((String) msg.getData());
							break;
							
						case MOVE: // The player has moved
							movePlayer((MovementDirection) msg.getData());
							break;
							
						case PURCHASE_ARROW: // The player has purchased an arrow
							beginTrivia(2, 3, UpdateType.PURCHASE_ARROW);
							break;
							
						case PURCHASE_SECRET: // The player has purchased a secret
							beginTrivia(2, 3, UpdateType.PURCHASE_SECRET);
							break;
							
						case SHOOT_ARROW: // The player has shot an arrow
							shootArrow((MovementDirection) msg.getData());
							break;
							
						case WINDOW_CLOSING:
							exitGame();
							break;
							
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
	public void newGame(int caveVer, String playerName)
	{	
		if(playerName == null || playerName.equals("NAME_UNSPECIFIED"))
			System.err.println("Player name unknown!");
		
		mapObject = new Map(); // Create a new map with new pit, bat, and wumpus locations
		
		// Create a new player with 100 coins, 3 arrows, 0 turns, and 0 score
		playerObject = new Player(0, 0, playerName);
		// Notify the GUI the number of coins and arrows has changed
		publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
		publish(new Update(UpdateType.GET_ARROWS, false, playerObject.getArrows()));
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		
		// Create a new cave with different doors
		try
		{
			Trivia.read_qa(); // Initialize Trivia for a new game (set the questions to be unused)
			
			if(caveVer == 6)
				caveObject = new Cave(CaveGen.generateNewCave());
			else
				caveObject = new Cave(caveVer);
		} catch (FileNotFoundException e)
		{
			System.err.println("Error creating new cave or reading trivia file: ");
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
		
		checkForWarnings(); // Display any warnings that should be shown in the first room 
	}
	
	// Move the player
	// Thread: Worker
	public void movePlayer(MovementDirection dir)
	{
		System.out.println("\n New Turn:\n------------------\n");
		
		// TODO check if there is a door to the room the player wants to move to
		
		int playerRoom = mapObject.movePlayer(dir); // Move the player to the new location
		System.out.println("Room: " + playerRoom);
		
		// Increment the number of turns the player has taken (and give the player another coin)
		playerObject.countTurns(playerRoom);
		playerObject.addCoins();
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
		
		// Send a trivia hint
		publish(new Update(UpdateType.GET_TRIVIA_ANSWER, false, Trivia.getHint()));
		
		publish(new Update(UpdateType.MOVE, false, playerRoom));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(playerRoom, caveObject.getConnections(playerRoom))));
		
		// Run checks for a new room
		checkForHazards();
		checkForWarnings();
		
		mapObject.wumpusRunsAway();
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
			beginTrivia(3, 5, UpdateType.ENCOUNTER_WUMPUS);
		}else if(room == mapObject.getPitRoom() || room == mapObject.getPitRoom2())
		{	// See if the room has a pit, and make the player fall in
			beginTrivia(2, 3, UpdateType.ENCOUNTER_PIT);
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
	
	// The player enters a room with bats
	// Thread: Worker
	public void foundBats()
	{
		publish(new Update(UpdateType.ENCOUNTER_BAT, false));
		
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
		// Move the player, then run checks for a new room
		// FIXME The Room doesn't update on screen
		mapObject.fallIntoPit();
		publish(new Update(UpdateType.MOVE, false, mapObject.getPlayerRoom()));
		publish(new Update(UpdateType.NEW_DOORS, false, Map.getDirections(mapObject.getPlayerRoom(), caveObject.getConnections(mapObject.getPlayerRoom()))));
		
		checkForHazards();
		checkForWarnings();
	}
	
	/* Begin asking questions to the GUI
	 * Thread: Worker
	 */
	private void beginTrivia(int quota, int max, UpdateType hazardType)
	{
		// Initialize a new trivia session, as it were
		// This keeps track of the stats surrounding one trivia event
		Trivia.askQuestions(max, quota, hazardType.toString());
		
		// Gets the stats for the trivia event from trivia, and prep them to be sent to the GUI
		int[] triviaStats = new int[]
				{
					0, // Was the last question answered correct (Since no question was asked last, just stay 0)
					Trivia.questionsCorrect(), // How many the player has answered correct
					Trivia.questionsAsked(), // How many questions the player has been asked
					Trivia.questionsLeft() // How many more attempts at answering trivia the player has before losing
				};
		publish(new Update(UpdateType.TRIVIA_STATS, false, triviaStats)); // Send stats to GUI
		
		// Spend a coin. If the player has run out of coins, he loses
		if(playerObject.spendCoin() < 0)
		{
			publish(new Update(UpdateType.TRIVIA_SUCCESS, false, false));
			endGame(false);
		}
		
		// Use a coin to ask the question (& update GUI)
		publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
		
		// Send the first question to the GUI, the rest will be handled after the previous one is answered
		publish(new Update(hazardType, false, Trivia.getQuestion()));
	}

	/* Handles the answer a player gives for a Trivia question
	 * 
	 * This is necessary because the Control is seperate from the GUI, so a linear progression
	 * of repeatedly asking a question, then recieving an answer (with a simple loop) doesn't work because
	 * it occurs across threads.
	 * 
	 * Thread: Worker
	 */
	private void answerTrivia(String answer)
	{
		//System.out.println("\n\n");
		
		int[] triviaStats = new int[]
			{
				Trivia.answer(answer) ? 1 : 0, // Was the last question answered correct, 1 if yes, 0 if not
				Trivia.questionsCorrect(), // How many the player has answered correct
				Trivia.questionsAsked(), // How many questions the player has been asked
				Trivia.questionsLeft() // How many more attempts at answering trivia the player has before losing
			};
		publish(new Update(UpdateType.TRIVIA_STATS, false, triviaStats)); // Send Stats to GUI
		
		System.out.print("Answer: " + answer);
		if(triviaStats[0] == 1)
			System.out.println(" Correct");
		else
			System.out.println();
		
		// If enough questions have been answered correct, the trivia event finishes, and the result is carried out
		if(Trivia.triviaPassed())
		{
			// Let the GUI know the trivia event is done, and the player succeeded
			publish(new Update(UpdateType.TRIVIA_SUCCESS, false, true));
			
			// REVIEW specialized messages for each hazard?
			if(Trivia.getReason().equals(UpdateType.ENCOUNTER_PIT.toString()))
			{
				foundPit(); // If the player was trying to get out of a pit, get out of the pit
			}else if(Trivia.getReason().equals(UpdateType.ENCOUNTER_WUMPUS.toString()))
			{
				// If the player was trying to escape the wumpus, make the wumpus run away
				mapObject.fightWumpus();
			}else if(Trivia.getReason().equals(UpdateType.PURCHASE_SECRET.toString()))
			{
				purchaseSecret();
			}else if(Trivia.getReason().equals(UpdateType.PURCHASE_ARROW.toString()))
			{
				purchaseArrow();
			}
		}else if(Trivia.canAskAnotherQuestion())
		{
			// Keep asking questions if the player hasn't answered enough yet
			
			// Spend a coin. If the player has run out of coins, he loses
			if(playerObject.spendCoin() < 0)
			{
				publish(new Update(UpdateType.TRIVIA_SUCCESS, false, false));
				endGame(false);
			}
			
			// Use a coin to ask the question (& update GUI)
			publish(new Update(UpdateType.GET_COINS, false, playerObject.getCoins()));
			
			// Ask the next question
			publish(new Update(UpdateType.GET_TRIVIA_QUESTION, false, Trivia.getQuestion()));
		}else
		{
			// If the player has gotten too many questions wrong, let the GUI know, and end the game
			publish(new Update(UpdateType.TRIVIA_SUCCESS, false, false));
			
			if(Trivia.getReason().equals(UpdateType.ENCOUNTER_WUMPUS.toString()) || Trivia.getReason().equals(UpdateType.ENCOUNTER_PIT.toString()))
			{
				endGame(false);
			}
		}
	}
	
	/* Purchases a secret for the player, and tells the GUI what it is
	 * 
	 * Thread: Worker
	 */
	private void purchaseSecret()
	{	
		// Add a player turn
		playerObject.incrementTurns();
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		
		boolean twoRoomsAway = mapObject.isWumpus2RoomsAway();
		int probability = (int)(Math.random() * 11);
		
		if(twoRoomsAway && probability >= 8)
		{
			publish(new Update(UpdateType.GET_SECRET, false, "The wumpus is within two rooms of you."));
		}else if(probability == 10)
		{
			publish(new Update(UpdateType.GET_SECRET, false, "The wumpus is NOT within two rooms of you."));
		}else if(probability == 1)
		{
			publish(new Update(UpdateType.GET_SECRET, false, "You are in room " + mapObject.getPlayerRoom()));
		}else if(probability <= 3)
		{
			publish(new Update(UpdateType.GET_SECRET, false, "There is a pit in room " + ((Math.random() > 0.5) ? mapObject.getPitRoom() : mapObject.getPitRoom2())));
		}else if(probability <= 5)
		{
			publish(new Update(UpdateType.GET_SECRET, false, "There is a pit in room " + ((Math.random() > 0.5) ? mapObject.getBatRoom() : mapObject.getBatRoom2())));
		}else
		{
			publish(new Update(UpdateType.GET_TRIVIA_ANSWER, false, Trivia.getHint()));
		}
	}

	/* Method that purchases another arrow for the player using coins
	 * and tells the GUI about it
	 * 
	 * Thread: Worker
	 */
	private void purchaseArrow()
	{	
		// Add a player turn
		playerObject.incrementTurns();
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		
		publish(new Update(UpdateType.GET_ARROWS, false, playerObject.buyArrows())); // return new number of arrows
	}
	
	// The player shoots an arrow
	// Thread: Worker
	private void shootArrow(MovementDirection dir)
	{	
		// Add a player turn
		playerObject.incrementTurns();
		publish(new Update(UpdateType.GET_NUM_OF_TURNS, false, playerObject.getTurns()));
		
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
		// Record the player's high score
		HighScore.addScore(playerObject.getName(), playerObject.finalScore(wumpusKilled), caveObject.version,
				playerObject.getTurns(), playerObject.getCoins(), playerObject.getArrows());
		
		if(wumpusKilled) // If the wumpus was killed, the game is won
			publish(new Update(UpdateType.DISPLAY_WIN, false, playerObject.finalScore(wumpusKilled))); // Pass high scores?
		else // The the wumpus wasn't killed the game is lost
			publish(new Update(UpdateType.DISPLAY_LOSE, false, playerObject.finalScore(wumpusKilled))); // Pass high scores?
		
		publish(new Update(UpdateType.GET_HIGH_SCORE, false, HighScore.returnHighscore()));
	}
	
	public void exitGame()
	{
		System.out.println("Writing high scores to file...");
		HighScore.writeFile();
		
		System.out.println("Game Exiting, Goodbye. o/");
		System.exit(0);
	}
}
