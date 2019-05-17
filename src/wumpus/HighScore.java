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
		
		File fileObj = new File("test.txt");
		System.out.println(fileObj.getAbsolutePath());
		
		ArrayList<String> List = new ArrayList<String>();
		
		
		
		try
		{
			FileWriter file = new FileWriter("test.text");
			BufferedWriter buffer = new BufferedWriter(file);
			
			
			
			buffer.write(List.get(0));
			buffer.newLine();
			buffer.write(List.get(1));
			buffer.newLine();
			buffer.write(List.get(2));
			buffer.newLine();
			buffer.write(List.get(3));
			buffer.newLine();
			buffer.write(List.get(4));
			buffer.newLine();
			buffer.write(List.get(5));
			buffer.newLine();
			buffer.write(List.get(6));
			buffer.newLine();
			buffer.write(List.get(7));
			buffer.newLine();
			buffer.write(List.get(8));
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
	
	public void readFile(File fileObj)
	{
		try 
		{
			FileReader fil = new FileReader ("test.text");
			BufferedReader buff = new BufferedReader(fil);
			String line = "";
			while((line = buff.readLine()) != null)
			{	System.out.println( line );         }
	
			buff.close(); 
			
		} 
	}
	
	public static ArrayList<String> returnHighscore ()
	{
		return List;
	}
	
	
	
	
	
	
	
}
