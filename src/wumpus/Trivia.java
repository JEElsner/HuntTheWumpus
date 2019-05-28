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
	private static int toAsk=1;
	private static String reason="";
	private static int correct=0;
	

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
		if (guess.equals(answers[toAsk]))
		{
			gotIt[toAsk]=true;
			correct++;
			return true;
			
		}
		else
		{
			gotIt[toAsk]=false;
			return false;
		}
	
	}
	
	public static String askQuestions(int number,String triviaReason)
	{
		reason=triviaReason;
		String questions="";
		for(int i=1;i<=number;i++)
			questions+=getQuestion()+" ";
		return questions;
	}

	public static String getHint()
	{
		String result="";
		for(int i=1;i<=toAsk;i++)
			if(!gotIt[i])
				result+="You got a secret! The answer to question---"+questions[i]+ 
						"---was "+answers[i];
		return result;
	}
	
	public static int questionsAsked()
	{
		return toAsk-1;
	}
	public static void debug()
	{
		try
		{
			read_qa();
			getQuestion();
			answer("idk");
			System.out.println(getHint());
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*if (answer("False"))
			System.out.println("Correct answer ");
		else
			System.out.println("Incorrect answer");
			*/
	}
}
