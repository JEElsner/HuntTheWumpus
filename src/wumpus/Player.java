package wumpus;

//John Knowlton

public class Player
{
	
	private int arrows;
	private int coins;
	private int turns;
	private int score;
	private boolean trivia;
	
	public static void debug()
	{
		
	}
	
	//constructor
	public Player(int c, int a, int t, int s)
	{
		coins = c;
		arrows = a;
		turns = t;
		score = s;
	}
	
	public int getArrows()
	{
		return arrows;
	}
	
	public int buyArrows(boolean triviaCorrect)
	{
		coins--;
		if(trivia)
		{
		 arrows++; 
		}
		return arrows;
	}
	
	public int getCoins()
	{
		return coins;
	}
	
	public void countTurns()
	{
		turns++;
	}
	
	public int getTurns()
	{
		return turns;
	}
	
	public int finalScore()
	{
		return (100 - turns + coins + (10 * arrows));
	}
	
	public void loseCoins()
	{
		coins = 0;
	}
	
	public void loseArrows()
	{
		arrows = 0;
	}
}
