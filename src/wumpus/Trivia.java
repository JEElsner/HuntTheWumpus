package wumpus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Trivia
{

	private static String questions[] = new String[101];
	private static String answers[] = new String[101];
	private static boolean asked[] = new boolean[101];

	// Reads the set of 100 questions and answers from two files

	// Builds two String arrays

	public static void read_qa() throws FileNotFoundException
	{
		int i = 1;
		InputStream qlist = Trivia.class.getResourceAsStream("/res/Questions.txt");
		Scanner qreader = new Scanner(qlist);
		while (qreader.hasNextLine() && (i < 101))
		{
			String theLine = qreader.nextLine();
			questions[i] = theLine;
			i++;
		}
		System.out.println("-------Questions list-------");
		for (int j = 1; j <= 100; j++)
			System.out.println(questions[j]);
		qreader.close();
		// -------------------------------------------------------------------------------
		i = 1;
		InputStream alist = Trivia.class.getResourceAsStream("/res/Answers.txt");
		Scanner areader = new Scanner(alist);
		while (areader.hasNextLine() && (i < 101))
		{
			String theLine = areader.nextLine();
			answers[i] = theLine;
			i++;
		}
		System.out.println("-------------Answers list------------");
		for (int j = 1; j <= 100; j++)
			System.out.println(answers[j]);
		areader.close();

		for (int j = 1; i <= 100; i++)
			asked[i] = false; // at the start, no question was asked
	}

	// Method used to check if given answer is corrrect(guess)
	// takes in the question number - i
	// Assign asked value to true because it was asked
	public static boolean answer(int i, String guess)
	{
		if (guess.equals(answers[i]))
		{
			asked[i] = true;
			return true;
		}
		{
			asked[i] = true;
			return true;
		}
	}

	public static void debug()
	{
		try
		{
			read_qa();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (answer(1, "False"))
			System.out.println("Correct answer ");
		else
			System.out.println("Incorrect answer");
	}
}
