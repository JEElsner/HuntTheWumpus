package wumpus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class HighScore
{
	private int score;
	private static ArrayList<String> List = new ArrayList<String>();
	private static ArrayList<Integer> scores = new ArrayList<Integer>();
	private static ArrayList<String> names = new ArrayList<String>();
	
	public static final String HIGH_SCORE_PATH = System.getProperty("user.dir") + File.separator + "scores.txt";
	
	// TODO Comment HighScore

	public static void debug()
	{
//		File dir = new File("data");
//		if (dir.exists())
//		{
//			String[] files = dir.list();
//			System.out.println(files.length + "files found...");
//			for (int i = 0; i < files.length; i++)
//			{
//				System.out.println(files[i]);
//			}
//
//		} else
//		{
//			System.out.println("Folder not found.");
//		}
		
		File dir = new File(HIGH_SCORE_PATH);
		readFile(dir);
		writeFile();
		addScore("DAD", 420420);
		addScore("MOM", 111110);
		addScore("COW", 928340);
		writeFile();
		readFile(dir);
	}

	public static void readFile(File fileObj)
	{
		boolean isScore = false;
		int ScoreCount = 0;
		int NameCount = 0;
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
				if (isScore)
				{
					scores.add(ScoreCount, Integer.parseInt(line));
					ScoreCount++;
					System.out.println(line);
				} else
				{
					names.add(NameCount, line);
					NameCount++;
					System.out.println(line);
				}

				isScore = !isScore;
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

	public static void addScore(String n, int s)
	{
		for (int i = 0; i < scores.size(); i++)
		{
			if (s >= scores.get(i))
			{
				scores.add(i, s);
				names.add(i, n);
				break;
			}
		}

		if (names.size() == 11 && scores.size() == 11)
		{
			names.remove(10);
			scores.remove(10);
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

	public static ArrayList<String> returnHighscore()
	{
		for (int i = 0; i < 10; i++)
		{
			List.add(i, names.get(i) + "  -  " + scores.get(i));
		}
		return List;
	}

}
