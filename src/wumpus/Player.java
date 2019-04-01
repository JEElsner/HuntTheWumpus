package wumpus;

//John Knowlton

public class Player
{
	
	public int arrows;
	public int coins;
	public int turns;
	public int score;
	
	public static void debug()
	{
		
	}
	
	//constructor
	public Player()
	{
		
	}
	
	public int getArrows()
	{
		return 0;//return arrows
	}
	
	public int buyArrows()
	{
		return 0;// arrows += 1 if trivia is answered
	}
	
	public int getCoins()
	{
		return 0;//return coins
	}
	
	public int getTurns()
	{
		return 0;//return total turns
	}
	
	public int finalScore()
	{
		return 0;//100 points - turns + gold + 10*arrows
	}
}
