package wumpus;

//John Knowlton

public class Player
{
	
	public int arrows;
	public int coins;
	public int turns;
	public int score;
	public boolean trivia;
	
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
}
