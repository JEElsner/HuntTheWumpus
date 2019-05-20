package wumpus;
import java.io.File;
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
			
	
	public static void debug()
	{
		
	}
	
	//public static void main(String args[])
	//{
		//File dir = new File("data");
		//if(dir.exists()) 
		//{
			//String[] files = dir.list();
			//System.out.println(files.length + "files found...");
			//for(int i = 0; i < files.length; i++)
			//{
				//System.out.println(files[i]);
			//}
			
		//}
		//else
		//{
			//System.out.println("Folder not found.");
		//}
	//}
	
	public static void main(String[] args)
	{
		
		
	}
	
	public void readFile(File fileObj)
	{
		boolean isScore = false;
		int ScoreCount = 0;
		int NameCount = 0;
		try 
		{
			FileReader fil = new FileReader ("test.text");
			BufferedReader buff = new BufferedReader(fil);
			String line = "";
			while((line = buff.readLine()) != null)
			{	
					if(isScore)
					{
						scores.add(ScoreCount, Integer.parseInt(line));
						ScoreCount++;
					}
					else
					{
						names.add(NameCount, line);
						NameCount++;
					}
				
				System.out.println(line); 
				isScore = !isScore;
			}
	
			buff.close(); 			
		}
		
		catch(IOException e)
		{
			System.out.println("A write error has occured");
		}
	}
	
	public void writeFile()
	{
		File fileObj = new File("test.txt");
		System.out.println(fileObj.getAbsolutePath());		
		
		
		try
		{
			FileWriter file = new FileWriter("test.text");
			BufferedWriter buffer = new BufferedWriter(file);	
						
			buffer.write(names.get(0));
			buffer.newLine();
			buffer.write(scores.get(0));
			buffer.newLine();
			buffer.write(names.get(1));
			buffer.newLine();
			buffer.write(scores.get(1));
			buffer.newLine();
			buffer.write(names.get(2));
			buffer.newLine();
			buffer.write(scores.get(2));
			buffer.newLine();
			buffer.write(names.get(3));
			buffer.newLine();
			buffer.write(scores.get(3));
			buffer.newLine();
			buffer.write(names.get(4));
			buffer.newLine();
			buffer.write(scores.get(4));
			buffer.newLine();
			buffer.write(names.get(5));
			buffer.newLine();
			buffer.write(scores.get(5));
			buffer.newLine();
			buffer.write(names.get(6));
			buffer.newLine();
			buffer.write(scores.get(6));
			buffer.newLine();
			buffer.write(names.get(7));
			buffer.newLine();
			buffer.write(scores.get(7));
			buffer.newLine();
			buffer.write(names.get(8));
			buffer.newLine();
			buffer.write(scores.get(8));
			buffer.newLine();
			buffer.write(List.get(9));
			buffer.newLine();
			buffer.write(List.get(9));
			buffer.newLine();
			buffer.close();
		}
		// index of where the number starts to the end, check to see if the highscore they just got  is greater than the other highscores
		catch(IOException e)
		{
			System.out.println("A write error has occured");
		}
	}
	
	public static ArrayList<String> returnHighscore ()
	{
		return List;
	}
	
	
	
	
	
	
	
}
