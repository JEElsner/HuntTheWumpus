package wumpus;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.Scanner;

public class Cave
{

	private int[][] cave = new int[30][6];
	public int version=0;

	// 2D array representing room connections
	// 1-6 column indexes represent 6 designated directions
	public Cave() throws FileNotFoundException
	{
		// REVIEW Eliminate one constructor by calling this(random cave)
		
		int i = 0, j = 0;
		version=(int)(Math.random()*5+1);
		
		InputStream caveFile = getClass().getResourceAsStream("/res/mapOne.txt");
		
		System.out.println("Using Cave: " + version);
		
		// REVIEW Consider programmatic choosing of cave
		if(version==2)
		caveFile = getClass().getResourceAsStream("/res/mapTwo.txt");
		if(version==3)
		caveFile = getClass().getResourceAsStream("/res/mapThree.txt");
		if(version==4)
		caveFile = getClass().getResourceAsStream("/res/mapFour.txt");
		if(version==5)
		caveFile = getClass().getResourceAsStream("/res/mapFive.txt");
		
		Scanner reader = new Scanner(caveFile);

		while (reader.hasNextInt() && (i < 30))
		{
			cave[i][j] = reader.nextInt();
			j++;
			if (j == 6)
			{
				i++;
				j = 0;
			}
		}
		reader.close();
	}
	
	
	//Constructor that lets us select a specific cave: 1 to 5
	public Cave(int ver) throws FileNotFoundException
	{
		System.out.println("Using Cave: " + ver);
		
		version = ver;
		
		int i = 0, j = 0;
		
		InputStream caveFile = getClass().getResourceAsStream("/res/mapOne.txt");
		
		if(ver==2)
		caveFile = getClass().getResourceAsStream("/res/mapTwo.txt");
		if(ver==3)
		caveFile = getClass().getResourceAsStream("/res/mapThree.txt");
		if(ver==4)
		caveFile = getClass().getResourceAsStream("/res/mapFour.txt");
		if(ver==5)
		caveFile = getClass().getResourceAsStream("/res/mapFive.txt");
		
		Scanner reader = new Scanner(caveFile);

		while (reader.hasNextInt() && (i < 30))
		{
			cave[i][j] = reader.nextInt();
			j++;
			if (j == 6)
			{
				i++;
				j = 0;
			}
		}
		reader.close();
	}
	
	// Constructor that lets us specify our own custom cave
	public Cave(int[][] customCave) throws FileNotFoundException
	{
		this(); // Call default constructor to have a cave to fall back on if the custom cave is bad
		
		/* Check to make sure the custom cave meets a variety of constraints. If it does, set it to be the cave, otherwise, don't do anything more
		 * 
		 * Constraints:
		 * 		* the cave is not null
		 * 		* the cave is the right length
		 *		* each room in the cave exists
		 *		* each room in the cave has the right number of doors
		 *		* each door in each room of the cave is a valid door
		 */
		VALIDITY_CHECK:
		{
			if(customCave == null)
			{
				System.err.print("Custom cave is null!");
				break VALIDITY_CHECK;
			}
			
			if(customCave.length != cave.length) // Compared to the length of the existing cave in case the length of the cave changes from the current spec
			{
				System.err.println("Custom cave of improper size: " + customCave.length);
				break VALIDITY_CHECK;
			}
			
			// Iterate through each room in the cave
			for(int room = 0; room < customCave.length; room ++)
			{
				if(customCave[room] == null)
				{
					System.err.println("Custom cave room does not exist: " + room);
				}
				
				if(customCave[room].length != cave[room].length) // Compared to the length of the room in the existing cave in case the length changes from the current spec
				{
					System.err.println("Custom cave room of improper size: Room: " + room + " Length: " + customCave[room].length);
					break VALIDITY_CHECK;
				}
				
				for(int door : customCave[room])
				{
					if(door < 0 || door > 30)
					{
						System.err.println("Custom cave has invalid door value in room: " + room);
						break VALIDITY_CHECK;
					}
				}
			}
			
			// Set the custom cave to be the actual cave
			cave = customCave;
			System.out.println("Using Cave: Randomly Generated");
		}
	}

	// Prints a list of the built cave for debugging purposes
	public void printConnections()
	{
		System.out.println("-------Map connections list-------");

		for (int i = 0; i < 30; i++)
		{
			System.out.print("Room " + (i + 1) + ":");
			for (int j = 0; j < 6; j++)
				System.out.print(cave[i][j] + " ");
			System.out.println();
		}

	}

	/*
	 * room is an integer between 1 and 30 representing the room number Return a
	 * integer of 6 int(s) that represent the 6 designated directions.
	 */
	public int[] getConnections(int room)
	{
		int[] dirs = new int[6];
		for (int i = 0; i < 6; i++)
			dirs[i] = cave[room - 1][i];

		return dirs;
	}

	public static void debug()
	{
		try
		{
			Cave one = new Cave();
			one.printConnections();
			// System.out.println(one.getConnections(1));

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		/*
		 * try { read_map(); } catch (FileNotFoundException e) {
		 * catch block e.printStackTrace(); }
		 */

		// System.out.println(getConnections(5));
	}

}
