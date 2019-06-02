package wumpus;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Trivia
{

	private static String questions[] = new String[116]; // array of questions
	private static String answers[] = new String[116]; // array of answers
	private static boolean asked[] = new boolean[116]; // array of asked questions
	private static boolean gotIt[] = new boolean[116]; // gotIt right or not
	private static int wrong[] = new int[116];
	private static int toAsk = 1;
	private static String reason = "";
	private static int hint = 1;
	private static int wrongCount = 1;

	private static int correct = 0;
	private static int correctQuota = 0;
	private static int totalQuestions = 0;
	private static int totalQuestionsAsked = 0;

	// Reads the set of 100 questions and answers from two files

	// Builds two String arrays

	public static void read_qa() throws FileNotFoundException
	{
		int i = 1;
		InputStream qlist = Trivia.class.getResourceAsStream("/res/Questions.txt");
		Scanner qreader = new Scanner(qlist);
		while (qreader.hasNextLine() && (i < 116))
		{
			String theLine = qreader.nextLine();
			questions[i] = theLine;
			asked[i] = false;
			gotIt[i] = false;
			i++;
		}

		// -------------------------------------------------------------------------------
		i = 1;
		InputStream alist = Trivia.class.getResourceAsStream("/res/Answers.txt");
		Scanner areader = new Scanner(alist);
		while (areader.hasNextLine() && (i < 116))
		{
			String theLine = areader.nextLine();
			answers[i] = theLine;
			i++;
		}
		/*
		 * System.out.println("-------Questions list-------"); for (int j = 1; j <= 100;
		 * j++) System.out.println(questions[j]);
		 * 
		 * System.out.println("-------------Answers list------------"); for (int j = 1;
		 * j <= 100; j++) System.out.println(answers[j]);
		 */
		qreader.close();
		areader.close();

	}

	// Method used to check if given answer is correct(guess)
	// takes in the question number - i
	// Assign asked value to true because it was asked

	public static String getQuestion()
	{
		if (toAsk < 116)
			return questions[toAsk];
		else
			return "Ran out of questions.";
	}

	public static boolean isAlphabet(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (!(Character.isLetter(str.charAt(i))))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean answer(String guess)
	{
		asked[toAsk] = true;
		totalQuestionsAsked++;

		answers[toAsk] = answers[toAsk].replace(" ", "");
		if (isAlphabet(guess))
		{
			guess = guess.replaceAll(" ", "");
			if (guess.toLowerCase().contains(answers[toAsk].toLowerCase()))
			{
				gotIt[toAsk] = true; // toAsk will increment after it is used to reference the array
				correct++;
				toAsk++;
				return true;
			}

			else
			{
				gotIt[toAsk] = false;
				toAsk++;
				wrong[wrongCount++] = toAsk;
				return false;
			}
		}

		else
		{
			if (guess.equals(answers[toAsk]))
			{
				gotIt[toAsk] = true; // toAsk will increment after it is used to reference the array
				correct++;
				toAsk++;
				return true;
			}
		}
		gotIt[toAsk] = false;
		toAsk++;
		wrong[wrongCount++] = toAsk;
		return false;
	}

	public static void askQuestions(int number, int quota, String triviaReason)
	{
		reason = triviaReason;

		correct = 0;
		correctQuota = quota;
		totalQuestions = number;
		totalQuestionsAsked = 0;
	}

	public static String getHint()
	{
		String result = "";
		result += "" + questions[hint] + "-" + answers[hint];
		if (wrong[hint] <= toAsk - 1)
			hint++;
		if (hint <= 115)
			return result;
		else
			return "No more hints.Already got every possible hint.";
	}

	public static boolean triviaPassed()
	{
		return correct >= correctQuota;
	}

	public static boolean canAskAnotherQuestion()
	{
		return totalQuestionsAsked < totalQuestions;
	}

	public static String getReason()
	{
		return reason;
	}

	public static int getCorrectQuota()
	{
		return correctQuota;
	}

	public static int questionsAsked()
	{
		return totalQuestionsAsked;
	}

	public static int questionsCorrect()
	{
		return correct;
	}

	public static int questionsLeft()
	{
		return totalQuestions - totalQuestionsAsked;
	}

	public static void debug()
	{
		toAsk = 85;
	}
}
