// Brandon Conner
package wumpus;

public class Map
{
	public int StartingRoom;
	public int PlayerRoom = 5;
	public int WumpusRoom = 6;
	public int PitRoom;
	public int PitRoom2;
	public int BatRoom;
	public int BatRoom2;
	
	public static final int [][] neighbors = new int[30][]; // The 30 represents the room number, and the 0 underneath is the room number - 1 due to index shenanigans
	
	static
	{		
		neighbors[0] = new int[] {25, 26, 2, 7, 6, 30 };
		//The first number is UP
		//The second number is UP_RIGHT
		//The third number is DOWN_RIGHT 
		//The fourth number is DOWN
		//The fifth number is DOWN_LEFT
		//The sixth number is UP
		neighbors[1] = new int[] {26, 3, 9, 8, 7, 1};
		neighbors[2] = new int[] {27, 28, 4, 9, 2, 26};
		neighbors[3] = new int[] {28, 5, 11, 10, 9, 3};
		neighbors[4] = new int[] {29, 30, 6, 11, 4, 28};
		neighbors[5] = new int[] {30, 1, 7, 12, 11, 5};
		neighbors[6] = new int[] {1, 2, 8, 13, 12, 6};
		neighbors[7] = new int[] {2, 9, 15, 14, 13, 7};
		neighbors[8] = new int[] {3, 4, 10, 15, 8, 2};
		neighbors[9] = new int[] {4, 11, 17, 16, 15, 9};
		neighbors[10] = new int[] {5, 6, 12, 17, 10, 4};
		neighbors[11] = new int[] {6,7,13,18,17,11,};
		neighbors[12] = new int[] {13,};
		neighbors[13] = new int[] {};
		neighbors[14] = new int[] {};
		neighbors[15] = new int[] {};
		neighbors[16] = new int[] {};
		neighbors[17] = new int[] {};
		neighbors[18] = new int[] {};
		neighbors[19] = new int[] {};
		neighbors[20] = new int[] {};
		neighbors[21] = new int[] {};
		neighbors[22] = new int[] {};
		neighbors[23] = new int[] {};
		neighbors[24] = new int[] {};
		neighbors[25] = new int[] {};
		neighbors[26] = new int[] {};
		neighbors[27] = new int[] {};
		neighbors[28] = new int[] {};
		neighbors[29] = new int[] {};
	}
	
	public static void debug()
	{
		Map map = new Map();
		for(int i = 0; i < 6; i ++)
		{
			if (neighbors[map.PlayerRoom][i] == map.WumpusRoom)
				System.out.println("There is a Wumpus in room: " + i);
			else
				System.out.println("No Wumpus");
		}
	}
	
	public int getWumpusRoom()
	{
		return 0;
	}
	
	public boolean ShootArrow(MovementDirection d)
	{
		// MovementDirection d will be whatever the player inputs on the keyboard for where he wants to shoot the arrow
		return false;
		
	}
	
	public int flyAway()
	{
		// if player room is the same as the room as the bat
		// random number generator that returns a room number between 1 and 30
		// check to make sure the new randomly generated room number isn't the same as the current room
		// returns new room number
		return 0;
	}
	
	public boolean BatWarning()
	{
		return false;
		//if the player has a neighbor room containing a bat, then it will send true to game control, and therefore the GUI in order to send a warning
	}
	
	public boolean PitWarning()
	{
		return false;
		//if the player has a neighbor room containing a bat, then it will send true to game control, and therefore the GUI in order to send a warning
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
