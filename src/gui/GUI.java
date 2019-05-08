package gui;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import wumpus.Control;

public class GUI
{
	
	public static final String titleScreen = "Title Screen";
	public static final String highScoreNameEnter = "Enter Your High Score";
	public static final String gameplay = "General Gameplay Screen";
	public static final String trivia = "Trivia Screen";
	public static final String WinScreen = "Win";
	public static final String LoseScreen = "Lose";
	public static final String PlayAgain = "Play Again";
	public static final String easterEgg = "Easter Egg?";
	public static final String debugging = "Debug";
	
	private String name;
	private String answer;
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}

	public String getAnswer()
	{
		return answer;
	}

	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	
	// The GUI that displays the game
	protected MainWindow mainWindow;
	
	private Control controller;
	
	public GUI(Control controller)
	{
		this.controller = controller;
	}
	
	public static void debug()
	{
		new GUI(new Control()).startGUI();
	}
	
	public void startGUI()
	{
		try
		{
			GUI thisGUI = this;
			
			EventQueue.invokeAndWait(new Runnable()
			{
				public void run()
				{
					try
					{
						mainWindow = new MainWindow(thisGUI);
						mainWindow.setVisible(true);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displayWin()
	{
		//Run this if you win
	}
	
	public void displayLose()
	{
		//Run this if you lose
	}
	
	public void displayRoom()
	{
		//Ideally I get a separate program with the room's GUI coded and then just run that GUI here
	}
	
	public void checkEncounter()
	{
		//Get from Game Control the location and return whatever message based on location
		//Bat - 'Bats Nearby'
			//TwoBat - 'Several bats nearby'
		//Pit - 'I feel a draft'
			//TwoPit - 'It's really windy in here'
		//Wumpus - 'I smell a Wumpus!'
	}
	
	public void encounterBat()
	{
		//Move player to a different room - change display
	}
	
	
	public void encounterPit()
	{
		runTrivia();
		//Trivia would run once for each trivia you may need
		//Definite ifs should you finish only needing two trivia questions
		//Then at the end should you not answer correct, back to room - may need 
		displayRoom();
	}
	
	public void encounterWumpus()
	{
		runTrivia();
		//Again, running the trivia several times, with the cut off at 3/5
		//If he wins
	}
	
	public void displayScore()
	{
		//Get the score from Game Control and keep running 
		//Then use this to put it into high score at the end of the game
	}
	
	public String highScoreDisplay(String name, int score)
	{
		//This is for the inbetween games, to just have it on the screen
		return "" + name + ": " + score;
	}
	
	
	public void betweenGames()
	{
		//Here you have the play again questions
		//Also displaying the high scores
		//highScoreDisplay();
	}
	
	public void displayInventory()
	{
		//Keep in corner of the screen - but continually display both the coins and arrows
	}
	
	
	public void move()
	{
		//Reads what room/direction you want to move
		//U, UR, UL, DR, DL ***No Stay option
		//Move accordingly
	}
	
	public void shootArrow()
	{
		//Whatever needs to be displayed for this action 
		//Two scenarios for if it hits or if it misses
	}
	
	public void purchaseArrow()
	{
		runTrivia();
		//Need 2/3 questions right
		//Display if this happened - two scenarios
	}
	
	public void purchaseSecret()
	{
		runTrivia();
		//Need two out of three trivia questions correct
		//Two different scenarios for if that requirement is met or not
	}
	
	public void runTrivia()
	{
		//Run the trivia for x amount of times - read from Control
	}
	
	// Process updates from the control worker thread
	// Thread: EDT
	public void processControlUpdates(List<Update> updates)
	{
		
	}
	
	// Notify the Control Object when it needs to respond to a GUI event
	// Thread: EDT
	protected void notifyControl(Update update)
	{
		controller.sendMessage(update);
	}

}
