package wumpus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class HighScore
{
	private static ArrayList<String> List = new ArrayList<String>();
	private static ArrayList<Integer> scores = new ArrayList<Integer>();
	private static ArrayList<String> names = new ArrayList<String>();
	private static ArrayList<Integer> caves = new ArrayList<Integer>();
	private static ArrayList<Integer> turns = new ArrayList<Integer>();
	private static ArrayList<Integer> coins = new ArrayList<Integer>();
	private static ArrayList<Integer> arrows = new ArrayList<Integer>();

	public static final String HIGH_SCORE_PATH = System.getProperty("user.dir") + File.separator + "scores.txt";

	// We created three seperate arraylists for the different types of data (score,
	// name and the cave version)
	// Then we used file readers and writers to store and rewrite data onto a file
	// so the highscores can be saved
	// and modified

	public static void debug()
	{
		System.out.println(names.size());
	}

	public static void readFile(File fileObj)
	{
		int cycle = 0;
		int ScoreCount = 0;
		int NameCount = 0;
		int CaveCount = 0;
		int TurnCount = 0;
		int CoinCount = 0;
		int ArrowCount = 0;
		try
		{
			File scoresFile = new File(HIGH_SCORE_PATH);
			System.out.println("Reading High Scores from:\n" + scoresFile.getAbsolutePath());

			if (!scoresFile.exists())
			{
				System.out.println("High Scores file does not exist. Creating...");
				scoresFile.createNewFile();
				setDefault();
				return;
			}

			FileReader fil = new FileReader(scoresFile);
			BufferedReader buff = new BufferedReader(fil);
			String line = "";
			while ((line = buff.readLine()) != null)
			{
				if (cycle % 6 == 0)
				{
					names.add(NameCount, line);
					NameCount++;
				} else if (cycle % 6 == 1)
				{
					scores.add(ScoreCount, Integer.parseInt(line));
					ScoreCount++;
				}

				else if (cycle % 6 == 2)
				{
					caves.add(CaveCount, Integer.parseInt(line));
					CaveCount++;
				}

				else if (cycle % 6 == 3)
				{
					turns.add(TurnCount, Integer.parseInt(line));
					TurnCount++;
				}

				else if (cycle % 6 == 4)
				{
					coins.add(CoinCount, Integer.parseInt(line));
					CoinCount++;
				}

				else if (cycle % 6 == 5)
				{
					arrows.add(ArrowCount, Integer.parseInt(line));
					ArrowCount++;
				}

				cycle++;
			}

			buff.close();

			if (scores.size() == 0)
			{
				setDefault();
			}
		}

		catch (IOException e)
		{
			System.err.println("Failed to read from high score file: ");
			e.printStackTrace();
		}
	}

	public static void setDefault()
	{
		System.out.println("Populating Default Scores...");

		for (int i = 0; i < 10; i++)
		{
			scores.add(000000);
			caves.add(1);
			turns.add(1);
			coins.add(1);
			arrows.add(1);
		}
		names.add("AAA");
		names.add("BBB");
		names.add("CCC");
		names.add("DDD");
		names.add("EEE");
		names.add("FFF");
		names.add("GGG");
		names.add("HHH");
		names.add("III");
		names.add("JJJ");
	}

	public static void addScore(String n, int s, int c, int t, int o, int a)
	{
		for (int i = 0; i < scores.size(); i++)
		{
			if (s >= scores.get(i))
			{
				scores.add(i, s);
				names.add(i, n);
				caves.add(i, c);
				turns.add(i, t);
				coins.add(i, o);
				arrows.add(i, a);

				break;
			}
		}

		if (names.size() == 11)
		{
			names.remove(10);
			scores.remove(10);
			caves.remove(10);
			turns.remove(10);
			coins.remove(10);
			arrows.remove(10);
		}
	}

	public static void writeFile()
	{
		File fileObj = new File(HIGH_SCORE_PATH);
		System.out.println("Saving High Scores to:\n" + fileObj.getAbsolutePath());

		try
		{
			FileWriter file = new FileWriter(fileObj);
			BufferedWriter buffer = new BufferedWriter(file);

			for (int i = 0; i < 10; i++)
			{
				buffer.write(names.get(i));
				buffer.newLine();
				buffer.write("" + scores.get(i));
				buffer.newLine();
				buffer.write("" + caves.get(i));
				buffer.newLine();
				buffer.write("" + turns.get(i));
				buffer.newLine();
				buffer.write("" + coins.get(i));
				buffer.newLine();
				buffer.write("" + arrows.get(i));
				buffer.newLine();
				
			}

			buffer.close();

		}
		// index of where the number starts to the end, check to see if the highscore
		// they just got is greater than the other highscores
		catch (IOException e)
		{
			System.err.println("Failed to write to high score file:");
			e.printStackTrace();
		}
	}

	public static int[] returnStats(int ranking)
	{
		int[] stats = new int[4];
		stats[0] = caves.get(ranking);
		stats[1] = turns.get(ranking);
		stats[2] = coins.get(ranking);
		stats[3] = arrows.get(ranking);

		return stats;
	}

	public static ArrayList<String> returnHighscore()
	{
		for (int i = 0; i < names.size(); i++)
		{
			List.add(i, names.get(i) + "  -  " + scores.get(i));
		}
		return List;
	}

}
