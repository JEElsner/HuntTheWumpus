// Brandon Conner
package wumpus;

import static wumpus.MovementDirection.*;

public class Map
{
	public int getStartingRoom()
	{
		return StartingRoom;
	}

	public int getPitRoom()
	{
		return PitRoom;
	}

	public int getPitRoom2()
	{
		return PitRoom2;
	}

	public int getBatRoom()
	{
		return BatRoom;
	}

	public int getBatRoom2()
	{
		return BatRoom2;
	}

	public boolean isWumpusAwake()
	{
		return isAwake;
	}

	public static int[] getNeighbors(int RoomNumber)
	{
		return neighbors[RoomNumber - 1];
	}

	private int StartingRoom = 1;
	private int PlayerRoom = 1;
	private int WumpusRoom;
	private int PitRoom = 0;
	private int PitRoom2 = 0;
	private int BatRoom = 0;
	private int BatRoom2 = 0;
	private boolean AnyMatch;
	private boolean isAwake = false;
	private final static int MAX_ROOM_NUMBER = 30;

	public Map()
	{
		WumpusRoom = (int) (Math.random() * 29 + 2);

		int[] hazards = new int[4]; // This for loops randomly assigns the 4 hazards to 4 different rooms
		for (int i = 0; i < hazards.length; i++)
		{
			do
			{
				hazards[i] = (int) (Math.random() * 29 + 2);

				AnyMatch = false;
				for (int k = 0; k < hazards.length; k++)
				{
					if (i == k)
						continue;
					else if (hazards[i] == hazards[k])
					{
						AnyMatch = true;
						break;
					}
				}
			} while (AnyMatch);

		}
		PitRoom = hazards[0];
		PitRoom2 = hazards[1];
		BatRoom = hazards[2];
		BatRoom2 = hazards[3];
	}

	private static final int[][] neighbors = new int[MAX_ROOM_NUMBER][]; // The 30 represents the room number, and the 0
																			// underneath is the room number - 1 due to
																			// index shenanigans
	static
	{

		neighbors[0] = new int[] { 25, 26, 2, 7, 6, 30 };
		// The first number is UP
		// The second number is UP_RIGHT
		// The third number is DOWN_RIGHT
		// The fourth number is DOWN
		// The fifth number is DOWN_LEFT
		// The sixth number is UP_LEFT
		neighbors[1] = new int[] { 26, 3, 9, 8, 7, 1 };
		neighbors[2] = new int[] { 27, 28, 4, 9, 2, 26 };
		neighbors[3] = new int[] { 28, 5, 11, 10, 9, 3 };
		neighbors[4] = new int[] { 29, 30, 6, 11, 4, 28 };
		neighbors[5] = new int[] { 30, 1, 7, 12, 11, 5 };
		neighbors[6] = new int[] { 1, 2, 8, 13, 12, 6 };
		neighbors[7] = new int[] { 2, 9, 15, 14, 13, 7 };
		neighbors[8] = new int[] { 3, 4, 10, 15, 8, 2 };
		neighbors[9] = new int[] { 4, 11, 17, 16, 15, 9 };
		neighbors[10] = new int[] { 5, 6, 12, 17, 10, 4 };
		neighbors[11] = new int[] { 6, 7, 13, 18, 17, 11, };
		neighbors[12] = new int[] { 7, 8, 14, 19, 18, 12, };
		neighbors[13] = new int[] { 8, 15, 21, 20, 19, 13 };
		neighbors[14] = new int[] { 9, 10, 16, 21, 14, 8 };
		neighbors[15] = new int[] { 10, 17, 23, 22, 21, 15 };
		neighbors[16] = new int[] { 11, 12, 18, 23, 16, 10 };
		neighbors[17] = new int[] { 12, 13, 19, 24, 23, 17 };
		neighbors[18] = new int[] { 13, 14, 20, 25, 24, 18 };
		neighbors[19] = new int[] { 14, 21, 27, 26, 25, 19 };
		neighbors[20] = new int[] { 15, 16, 22, 27, 20, 14 };
		neighbors[21] = new int[] { 16, 23, 29, 28, 27, 21 };
		neighbors[22] = new int[] { 17, 18, 24, 29, 22, 16 };
		neighbors[23] = new int[] { 18, 19, 25, 30, 29, 23 };
		neighbors[24] = new int[] { 19, 20, 26, 1, 30, 24 };
		neighbors[25] = new int[] { 20, 27, 3, 2, 1, 25 };
		neighbors[26] = new int[] { 21, 22, 28, 3, 26, 20 };
		neighbors[27] = new int[] { 22, 29, 5, 4, 3, 27 };
		neighbors[28] = new int[] { 23, 24, 30, 5, 28, 22 };
		neighbors[29] = new int[] { 24, 25, 1, 6, 5, 29 };
	}

	public static void debug()
	{
		Map m1 = new Map();
		System.out.println(m1.WumpusRoom);
		m1.isAwake = true;

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);

		m1.moveWumpus();
		System.out.println(m1.WumpusRoom);
	}

	public int getWumpusRoom()
	{
		return WumpusRoom;
	}

	public int getPlayerRoom()
	{
		return PlayerRoom;
	}

	public int movePlayer(MovementDirection dir)
	{
		PlayerRoom = getNearbyRoom(PlayerRoom, dir);
		return PlayerRoom;
	}

	public static int getNearbyRoom(int room, MovementDirection dir)
	{
		int newRoom = neighbors[room - 1][dir.ordinal()];
		return newRoom;
	}

	/*public static int getNearbyRoom2(int room, MovementDirection dir)
	{
		int newRoom = room;
		if (dir == DOWN)
		{
			newRoom += 6;
			if (newRoom > MAX_ROOM_NUMBER)
			{
				newRoom -= MAX_ROOM_NUMBER;
			}
		}

		else if (dir == UP)
		{
			newRoom -= 6;
			if (newRoom < 0)
			{
				newRoom += MAX_ROOM_NUMBER;
			}
		}
		
		else if(newRoom % 2 == 1)
		{
			if (dir == UP_RIGHT)
			{
				newRoom -= 5;
				if(newRoom < 0)
				{
					newRoom += MAX_ROOM_NUMBER;
				}
			}
			
			else if (dir == DOWN_RIGHT)
			{
				newRoom++;
				if(newRoom > MAX_ROOM_NUMBER)
				{
					newRoom -= MAX_ROOM_NUMBER;
				}
			}
			
			else if (dir == DOWN_LEFT)
			{
				newRoom -= 1;
			}
		}

	} */

	public boolean shootArrow(MovementDirection dir)
	{
		if (WumpusRoom == getNearbyRoom(PlayerRoom, dir))
			return true;

		int ArrowRoom = getNearbyRoom(PlayerRoom, dir);

		int[] ArrowNeighbors = getNeighbors(ArrowRoom);

		for (int i = 0; i < 6; i++)
		{
			if (ArrowNeighbors[i] == WumpusRoom)
			{
				isAwake = true;
			}
		}
		return false;
	}

	public int moveWumpus()
	{
		if (isAwake)
		{
			int random = (int) (Math.random() * 2);
			if (random == 1)
			{
				return WumpusRoom;
			}

			else
			{
				int randomRoom = (int) (Math.random() * 6);

				WumpusRoom = getNearbyRoom(WumpusRoom, MovementDirection.values()[randomRoom]);

			}

		}
		return WumpusRoom;
	}

	public int flyAway()
	{
		if (PlayerRoom == BatRoom)
		{
			BatRoom = (int) (Math.random() * 29 + 2);
			while (BatRoom == BatRoom2 || BatRoom == PitRoom || BatRoom == PitRoom2 || BatRoom == PlayerRoom)
			{
				BatRoom = (int) (Math.random() * 29 + 2);
			}

			PlayerRoom = (int) (Math.random() * 29 + 2);
			while (PlayerRoom == BatRoom || PlayerRoom == BatRoom || PlayerRoom == PitRoom || PlayerRoom == PitRoom2
					|| PlayerRoom == WumpusRoom)
			{
				PlayerRoom = (int) (Math.random() * 29 + 2);
			}
		}

		if (PlayerRoom == BatRoom2)
		{
			BatRoom2 = (int) (Math.random() * 29 + 2);
			while (BatRoom2 == BatRoom || BatRoom2 == PitRoom || BatRoom2 == PitRoom2 || BatRoom2 == PlayerRoom)
			{
				BatRoom2 = (int) (Math.random() * 29 + 2);
			}

			PlayerRoom = (int) (Math.random() * 29 + 2);
			while (PlayerRoom == BatRoom || PlayerRoom == BatRoom || PlayerRoom == PitRoom || PlayerRoom == PitRoom2
					|| PlayerRoom == WumpusRoom)
			{
				PlayerRoom = (int) (Math.random() * 29 + 2);
			}
		}
		return PlayerRoom;
	}

	public int CheckForBats()
	{
		int count = 0;
		for (MovementDirection dir : MovementDirection.values())
		{
			if (BatRoom == getNearbyRoom(PlayerRoom, dir))
				count++;
			if (BatRoom2 == getNearbyRoom(PlayerRoom, dir))
				count++;
		}

		return count;
		// if the player has a neighbor room containing a bat, then it will the number
		// of adjacent bats, and therefore the GUI in order to send a warning
	}

	public int CheckForPits()
	{
		int count = 0;
		for (MovementDirection dir : MovementDirection.values())
		{
			if (PitRoom == getNearbyRoom(PlayerRoom, dir))
				count++;
			if (PitRoom2 == getNearbyRoom(PlayerRoom, dir))
				count++;
		}
		return count;
		// if the player has a neighbor room containing a bat, then it will send the
		// number of adjacent pits, and therefore the GUI in order to send a warning
	}

	public boolean CheckForWumpus()
	{
		for (MovementDirection dir : MovementDirection.values())
		{
			if (WumpusRoom == getNearbyRoom(PlayerRoom, dir))
				return true;
		}
		return false;
	}

	public void fallIntoPit() // returns the Player Room Number, or -1 if lost?
	{
		PlayerRoom = 1;
	}

}
