package wumpus;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Trivia
{

	private static String questions[]=new String[101];
	private static String answers[]=new String[101];
	private static boolean asked[]=new boolean[101];
 
	public static void read_qa() throws FileNotFoundException
	{
		int i=1;
		File qlist=new File("C:\\Users\\sn390603\\Desktop\\questions.txt");
		Scanner qreader=new Scanner(qlist);
		while(qreader.hasNextLine()&&(i<101))
		{
			String theLine=qreader.nextLine();
			questions[i]=theLine;
			i++;
		}
		System.out.println("-------Questions list-------");
		for(int j=1;j<=100;j++)
			System.out.println(questions[j]);
		qreader.close();
	//-------------------------------------------------------------------------------	
		i=1;
		File alist=new File("C:\\Users\\sn390603\\Desktop\\answers.txt");
		Scanner areader=new Scanner(alist);
		while(areader.hasNextLine()&&(i<101))
		{
			String theLine=areader.nextLine();
			answers[i]=theLine;
			i++;
		}
		System.out.println("-------------Answers list------------");
		for(int j=1;j<=100;j++)
			System.out.println(answers[j]);
		areader.close();
		
		for(int j=1;i<=100;i++)
			asked[i]=false;
	}
	
	public static boolean answer(int i,String guess)
	{
		if(guess.equals(answers[i]))
		{
			asked[i]=true;
			return true;
		}
		{
			asked[i]=true;
			return true;
		}
	}
	
	public static void debug() throws FileNotFoundException
	{
		read_qa();
		if(answer(1,"False"))
			System.out.println("Correct answer ");
		else
			System.out.println("Incorrect answer");
			
		
	}
}
