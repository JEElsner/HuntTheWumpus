package gui;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import wumpus.Control;
import wumpus.MovementDirection;

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
	
	private int pits = 0;
	private int bats = 0;
	private int wumpus = 0;
	
	private static String hs1N;
	private static String hs2N;
	private static String hs3N;
	private static String hs4N;
	private static String hs5N;
	
	//----Variables for Gameplay----//
	private int arrows;
	private int coins;
	private int currentScore;
	//------------------------------//
	
	private String name;
	private String answer;
	
	private MovementDirection[] doors = new MovementDirection[3];
	

	public MovementDirection[] getDoors()
	{
		return doors;
	}

	public void setDoors(MovementDirection[] doors)
	{
		this.doors = doors;
	}
	
	public int getBats()
	{
		return bats;
	}

	public void setBats(int numofBat)
	{
		this.bats = numofBat;
	}

	public int getPits()
	{
		return pits;
	}

	public void setPits(int numOfPit)
	{
		this.pits = numOfPit;
	}
	
	public int getWumpus()
	{
		return wumpus;
	}
	
	public void setWumpus(int w)
	{
		this.wumpus = w;
	}

	public int getArrows()
	{
		return arrows;
	}

	public void setArrows(int arrows)
	{
		this.arrows = arrows;
	}

	public int getCoins()
	{
		return coins;
	}

	public void setCoins(int coins)
	{
		this.coins = coins;
	}

	public int getCurrentScore()
	{
		return currentScore;
	}

	public void setCurrentScore(int currentScore)
	{
		this.currentScore = currentScore;
	}

	public String getHs1()
	{
		return hs1N;
	}

	public void setHs1(String hs1)
	{
		GUI.hs1N = hs1;
	}

	public String getHs2()
	{
		return hs2N;
	}

	public void setHs2(String hs2)
	{
		GUI.hs2N = hs2;
	}

	public String getHs3()
	{
		return hs3N;
	}

	public void setHs3(String hs3)
	{
		GUI.hs3N = hs3;
	}

	public String getHs4()
	{
		return hs4N;
	}

	public void setHs4(String hs4)
	{
		GUI.hs4N = hs4;
	}

	public String getHs5()
	{
		return hs5N;
	}

	public void setHs5(String hs5)
	{
		GUI.hs5N = hs5;
	}

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
		GUI g = new GUI(new Control());
		
		g.startGUI();
		g.processControlUpdates(Arrays.asList(new Update(UpdateType.MOVE, false, new MovementDirection[] {MovementDirection.UP, MovementDirection.UP_LEFT, MovementDirection.DOWN})));
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
						mainWindow.setSize(1000, 800);
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
	
	public String displayBWarn()
	{
		if(bats > 0)
		{
			if(bats == 1)
				return "Bats nearby";
			if(bats == 2)
				return "Several bats nearby";	
		}
		return null;
	}
	
	public String displayPWarn()
	{
		if(pits > 0)
		{
			if(pits == 1)
				return "I feel a draft";
			if(pits == 2)
				return "It's really windy in here";	
		}
		return null;
	}
	
	public String displayWWarn()
	{
		if(wumpus == 1)
		{
			return "I smell a Wumpus!";
		}
		return null;
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
		for(Update update : updates)
		{
			switch(update.getType())
			{
			case MOVE:
				doors = (MovementDirection[]) update.getData();
				this.mainWindow.gameplayScreen.updatePanel("new game");
				break;
				
			case PURCHASE_ARROW:
				setArrows((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("arrows");
				break;
			
			case GET_COINS:
				setCoins((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("coins");
				break;
				
			case GET_PLAYER_SCORE:
				setCurrentScore((int) update.getData());
				break;
				
			case PIT_WARNING:
				setPits((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("pits");
				break;
			
			case BAT_WARNING:
				setBats((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("bats");
				break;
				
			case WUMPUS_WARNING:
				this.mainWindow.gameplayScreen.updatePanel("wumpus");
				break;
				
			default:
				System.err.println("Unhandled update type: " + update.getType());
				break;
			}
		}
	}
	
	// Notify the Control Object when it needs to respond to a GUI event
	// Thread: EDT
	protected void notifyControl(Update update)
	{
		controller.sendMessage(update);
	}



}