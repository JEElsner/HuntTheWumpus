package wumpus;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Trivia
{

	private static String questions[] = new String[101]; //array of questions
	private static String answers[] = new String[101];  //array of answers
	private static boolean asked[] = new boolean[101];  //array of asked questions
	private static boolean gotIt[]=new boolean[101]; //gotIt right or not
	private static int wrong[]=new int[101];
	private static int toAsk=1;
	private static String reason="";
	private static int hint=1;
	private static int wrongCount=1;
	
	
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
		while (qreader.hasNextLine() && (i < 101))
		{
			String theLine = qreader.nextLine();
			questions[i] = theLine;
			asked[i] = false;
			gotIt[i] = false;
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
	}

	// Method used to check if given answer is corrrect(guess)
	// takes in the question number - i
	// Assign asked value to true because it was asked
	
	public static String getQuestion()
	{
		return questions[toAsk];
	}
	
	public static boolean answer(String guess)
	{
		asked[toAsk]=true;
		totalQuestionsAsked++;
		
		if (guess.equalsIgnoreCase(answers[toAsk]))
		{
			gotIt[toAsk++]=true; // toAsk will increment after it is used to reference the array
			correct++;
			return true;
			
		}
		else
		{
			gotIt[toAsk++]=false;
			wrong[wrongCount++]=toAsk;
			return false;
		}
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
		String result="";
		result+=""+questions[hint]+ "-"+answers[hint];
		hint++;
		return result;
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
		try
		{
			read_qa();
			answer("idk");
			answer("idk");
			answer("idk");
			System.out.println(getHint());
			System.out.println(getHint());
			System.out.println(getHint());
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		/*if (answer("False"))
			System.out.println("Correct answer ");
		else
			System.out.println("Incorrect answer");
			*/
	}
}
