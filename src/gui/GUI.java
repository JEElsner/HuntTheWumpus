package gui;

import java.awt.EventQueue;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import wumpus.Control;
import wumpus.Map;
import wumpus.MovementDirection;

public class GUI
{
	// REVIEW Consider Renaming some GUI classes so there isn't any confusion between the class in the wumpus package (e.g. HighScore)
	
	public static final String titleScreen = "Title Screen";
	public static final String highScoreNameEnter = "Enter Your High Score";
	public static final String gameplay = "General Gameplay Screen";
	public static final String trivia = "Trivia Screen";
	public static final String WinScreen = "Win";
	public static final String LoseScreen = "Lose";
	public static final String PlayAgain = "Play Again";
	public static final String easterEgg = "Easter Egg?";
	public static final String debugging = "Debug";
	public static final String title = "Actual Title Screen";
	
	private int pits = 0;
	private int bats = 0;
	private int wumpus = 0;
	
	private int score;
	
	private ArrayList<String> highScores;
	
	//----Variables for Gameplay----//
	private int arrows;
	private int coins;
	private int turns;
	private int currentScore;
	//------------------------------//
	
	private String name;
	private String answer;
	private String question;
	private boolean isSuccessful;
	private boolean lastCorrect;
	private int[] triviaStats = new int[4];
	
	private ScoreDetails details;
	
	private int[] scoreStats = new int[4];
	private String nameScore;
	
	private ArrayList<String> secrets = new ArrayList<String>();
	private ArrayList<String> triviaAnswers = new ArrayList<String>();
	
	private int caveSelected;
	
	private MovementDirection[] doors = new MovementDirection[3];
	private int currentRoom;
	

	public ScoreDetails getDetails()
	{
		return details;
	}
	
	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public ArrayList<String> getSecrets()
	{
		return secrets;
	}

	public void setSecrets(ArrayList<String> secrets)
	{
		this.secrets = secrets;
	}

	public ArrayList<String> getTriviaAnswers()
	{
		return triviaAnswers;
	}

	public void setTriviaAnswers(ArrayList<String> triviaAnswers)
	{
		this.triviaAnswers = triviaAnswers;
	}

	public MovementDirection[] getDoors()
	{
		return doors;
	}

	public void setDoors(MovementDirection[] doors)
	{
		this.doors = doors;
	}
	
	public int getCurrentRoom()
	{
		return currentRoom;
	}

	public void setCurrentRoom(int currentRoom)
	{
		this.currentRoom = currentRoom;
	}

	public String getQuestion()
	{
		return question;
	}
	
	public void setQuestion(String q)
	{
		this.question = q;
	}
	
	public boolean isSuccessful()
	{
		return isSuccessful;
	}

	public void setSuccessful(boolean isCorrect)
	{
		
		this.isSuccessful = isCorrect;
	}

	public boolean isLastCorrect()
	{
		return lastCorrect;
	}

	public void setLastCorrect(boolean lastCorrect)
	{
		this.lastCorrect = lastCorrect;
	}

	public int[] getTriviaStats()
	{
		return triviaStats;
	}

	public void setTriviaStats(int[] stats)
	{
		for(int i = 0; i < 4; i++)
		{
			triviaStats[i] = stats[i];
		}
	}

	public int[] getScoreStats()
	{
		return scoreStats;
	}

	public void setScoreStats(int[] scoreStats)
	{
		for(int i = 0; i < 4; i++)
		{
			scoreStats[i] = scoreStats[i];
		}
	}

	public String getNameScore()
	{
		return nameScore;
	}

	public void setNameScore(String nameScore)
	{
		this.nameScore = nameScore;
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

	public int getTurns()
	{
		return turns;
	}

	public void setTurns(int turns)
	{
		this.turns = turns;
	}

	public void setName(String n)
	{
		name = n.toUpperCase();
	}
	
	public String getName()
	{
		return name;
	}

	public int getCaveSelected()
	{
		return caveSelected;
	}

	public void setCaveSelected(int caveSelected)
	{
		this.caveSelected = caveSelected;
	}

	public ArrayList<String> getHighScores()
	{
		return highScores;
	}

	public void setHighScores(ArrayList<String> highScores)
	{
		this.highScores = highScores;
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
		try
		{
			Field controlObject = Control.class.getDeclaredField("controlObject");
			Field mapObject = Control.class.getDeclaredField("mapObject");
			
			controlObject.setAccessible(true);
			mapObject.setAccessible(true);
			
			Map map = (Map) mapObject.get(controlObject.get(null));
			
			Field batRoom = Map.class.getDeclaredField("BatRoom");
			batRoom.setAccessible(true);
			
			batRoom.set(map, 2);
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
						mainWindow.setSize(1000, 930);
						mainWindow.setVisible(true);
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e)
		{
			System.err.println("Error starting GUI: ");
			e.printStackTrace();
		}
	}
	
	
	public String displayBWarn()
	{
		if(bats == 0 && pits == 0 && wumpus == 0)
			return "Nothing nearby!";
		
		if(bats > 0)
		{
			if(bats == 1)
				return "Bats nearby!\n";
			if(bats == 2)
				return "Several bats nearby!\n";	
		}
		return "";
	}
	
	public String displayPWarn()
	{
		if(pits > 0)
		{
			if(pits == 1)
				return "I feel a draft!\n";
			if(pits == 2)
				return "It's really windy in here!\n";	
		}
		return "";
	}
	
	public String displayWWarn()
	{
		if(wumpus == 1)
		{
			return "I smell a Wumpus!!";
		}
		return "";
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
		this.mainWindow.changeView(trivia);
	}
	
	// Process updates from the control worker thread
	// Thread: EDT
	@SuppressWarnings("unchecked")
	public void processControlUpdates(List<Update> updates)
	{
		for(Update update : updates)
		{
			switch(update.getType())
			{
			case NEW_DOORS:
				doors = (MovementDirection[]) update.getData();
				this.mainWindow.gameplayScreen.updatePanel("new doors");
				break;
				
			case MOVE:
				setCurrentRoom((int) update.getData());
				break;
				
			case PURCHASE_ARROW:
				setQuestion((String) update.getData());
				mainWindow.triviaScreen.updatePanel("arrows");
				mainWindow.changeView(GUI.trivia);
				break;
				
			case PURCHASE_SECRET:
				setQuestion((String) update.getData());
				this.mainWindow.triviaScreen.updatePanel("secret");
				runTrivia();
				break;
				
			case GET_HIGH_SCORE:
				this.setHighScores((ArrayList<String>) update.getData());
				this.mainWindow.menuScreen.updatePanel("high scores");
				this.mainWindow.winScreen.updatePanel("win scores");
				this.mainWindow.winScreen.updatePanel("lose scores");
				// Update the panel
				break;
				
			case GET_SCORE_STATS:
				setScoreStats((int[]) update.getData());
				details = new ScoreDetails();
				details.settingScores(getNameScore());
				details.settingStats((int[]) update.getData());
				details.setVisible(true);
				break;
			
			case GET_COINS:
				setCoins((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("coins");
				break;
				
			case MAX_COINS_REACHED:
				this.mainWindow.gameplayScreen.maxCoins();
				break;
				
			case GET_NUM_OF_TURNS:
				setTurns((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("turns");
				break;
				
			case GET_PLAYER_SCORE:
				setCurrentScore((int) update.getData());
				break;
				
			case GET_TRIVIA_ANSWER:
				triviaAnswers.add((String) update.getData());
				break;
			
			case GET_SECRET:
				secrets.add((String) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("secrets");
				break;
				
			case PIT_WARNING:
				setPits((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("pitsWarn");
				break;
			
			case BAT_WARNING:
				setBats((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("batsWarn");
				break;
				
			case WUMPUS_WARNING:
				setWumpus((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("wumpusWarn");
				break;
				
			case ENCOUNTER_BAT:
				this.mainWindow.gameplayScreen.setCompletedAction("bats");
				this.mainWindow.gameplayScreen.completed();
				break;
				
			case ENCOUNTER_PIT:
				setQuestion((String) update.getData());
				this.mainWindow.triviaScreen.updatePanel("pits");
				mainWindow.changeView(trivia); 
				break;
				
			case ENCOUNTER_WUMPUS:
				setQuestion((String) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("wumpus");
				this.mainWindow.triviaScreen.updatePanel("wumpus");
				runTrivia();
				break;
				
			case GET_TRIVIA_QUESTION:
				setQuestion((String) update.getData());
				this.mainWindow.triviaScreen.updatePanel("new question");
				break;
				
			case GIVE_ANSWER:
				setLastCorrect((boolean) update.getData());
				break;
				
			case TRIVIA_STATS:
				setTriviaStats((int[]) update.getData());
				break;
				
			case TRIVIA_SUCCESS:
				setSuccessful((boolean) update.getData());
				if(isSuccessful())
				{
					this.mainWindow.gameplayScreen.completed();
					this.mainWindow.triviaScreen.clear();
				}
				else
				{
					this.mainWindow.gameplayScreen.setCompletedAction("arrow fail");
					this.mainWindow.gameplayScreen.completed();
					this.mainWindow.triviaScreen.clear();
				}
				this.mainWindow.changeView(gameplay);
				break;
				
			case SHOOT_ARROW:
				break;
				
			case ARROW_MISS:
				this.mainWindow.gameplayScreen.miss();
			case GET_ARROWS:
				setArrows((int) update.getData());
				this.mainWindow.gameplayScreen.updatePanel("arrows");
				break;
				
			case DISPLAY_WIN:
				this.setScore((int) update.getData());
				this.mainWindow.winScreen.updatePanel("scores");
				this.mainWindow.changeView(WinScreen);
				break;
			
			case DISPLAY_LOSE:
				this.setScore((int) update.getData());
				this.mainWindow.loseScreen.updatePanel("scores");
				this.mainWindow.changeView(LoseScreen);
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
