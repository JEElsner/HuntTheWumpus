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
		c = coins;
		a = arrows;
		t = turns;
		s = score;
	}
	
	public int getArrows()
	{
		return arrows;
	}
	
	public int buyArrows()
	{
		coins--;
		return 0;//if(trivia)
		//{
		// arrows++; 
		//}
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
