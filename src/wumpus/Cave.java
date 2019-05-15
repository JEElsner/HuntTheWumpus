package wumpus;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Cave
{

	private static int[][] cave = new int[30][7];

	// 2D array representing room connections
	// 1-6 column indexes represent 6 designated directions
	public Cave() throws FileNotFoundException
	{
		int i = 0, j = 0;
		int ver = (int) (Math.random() * 5);
		
		InputStream caveFile = getClass().getResourceAsStream("/res/mapOne.txt");
		
		Scanner qreader = new Scanner(caveFile);
		while (qreader.hasNextInt() && (i < 30))
		{
			cave[i][j] = qreader.nextInt();
			j++;
			if (j == 6)
			{
				i++;
				j = 0;
			}
		}
		qreader.close();
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

		System.out.println(cave[0][0] + " " + cave[0][1]);

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { read_map(); } catch (FileNotFoundException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */

		// System.out.println(getConnections(5));
	}

}
