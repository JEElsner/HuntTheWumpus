package wumpus;

public class Map
{
	public int StartingRoom;
	public int PlayerRoom;
	public int WumpusRoom;
	public int PitRoom;
	public int PitRoom2;
	public int BatRoom;
	public int BatRoom2;
	
	public int getPlayerRoom()
	{
		MovementDirection d = MovementDirection.UP.DOWN.UP.DOWN.UP.DOWN;
		
		return 0;
	}
	
	public int getWumpusRoom()
	{
		return 0;
	}
	
	public int flyAway()
	{
		// if player room is the same as the room as the bat
		// random number generator that returns a room number between 1 and 30
		// check to make sure the new randomly generated room number isn't the same as the current room
		// returns new room number
		return 0;
	}
	
	public boolean fallIntoPit()
	{
		// if player room is the same as the room as the pit
		// ask trivia class for 3 questions
		// if 2 is correct, return false(Game does not end) and Player Room = starting position
		// if less than 2, return true(Game Over)
		return false;
	}
	
}
