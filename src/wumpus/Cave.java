package wumpus;

import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.Scanner;
import java.io.*;

public class Cave
{

	private static int[][] cave = new int[30][7];
	public static int version=0;

	// 2D array representing room connections
	// 1-6 column indexes represent 6 designated directions
	public Cave() throws FileNotFoundException
	{
		int i = 0, j = 0;
		int version=(int)(Math.random()*5+1);
		
		InputStream caveFile = getClass().getResourceAsStream("/res/mapOne.txt");
		
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
