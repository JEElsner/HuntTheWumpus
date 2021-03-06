package wumpus;

//John Knowlton

public class Player
{
	
	private int arrows;
	private int coins;
	private int maxCoins;
	private int turns;
	private int score;
	private String name;
	private boolean trivia;
	private boolean[] isVisited = new boolean[30];
	public static void debug()
	{
		
	}
	
	//constructor
	public Player(int t, int s, String n)
	{
		coins = 10;
		arrows = 3;
		maxCoins = 100;
		turns = t;
		score = s;
		name = n;
		
	}
	
	// Returns false if you can't collect more coins
	public boolean coinsLimit()
	{
		if(maxCoins <= 0)
		{
			return false;
		}
		return true;
	}
	
	public int getArrows()
	{
		return arrows;
	}
	
	public int shootArrows()
	{
		if(arrows > 0)
			arrows--;
		return arrows;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int buyArrows()
	{
		return (arrows += 2);
	}
	
	public int getCoins()
	{
		return coins;
	}
	
	public void incrementTurns() 
	{
		turns++;
	}
	
	public void countTurns(int roomNumber)
	{
		roomNumber--;
		turns++;
		isVisited[roomNumber] = true;
	}
	
	
	
	public boolean HasVisited(int roomNumber)
	{
		roomNumber--;
		return isVisited[roomNumber];
	}
	
	public void addCoins()
	{
		if(coinsLimit())
			{
				coins++;
				maxCoins--;
			}
	}
	
	public int getTurns()
	{
		return turns;
	}
	
	public int finalScore(boolean wumpusKilled)
	{
		score = (coins + (10 * arrows) - turns);
		
		if(wumpusKilled)
		{
			score = score + 100;
		}
		
		if(score < 0)
		{
			score = 0;
		}
		
		return score;
	}
	
	public void loseCoins()
	{
		coins = 0;
	}
	
	public void loseArrows()
	{
		arrows = 0;
	}

	public int spendCoin()
	{
		if(coins > 0)
			return --coins;
		else
			return coins;
	}
	
	public int getCoinsLeft()
	{
		return maxCoins;
	}
}
