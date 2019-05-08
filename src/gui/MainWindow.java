package gui;

import java.awt.BorderLayout; 
import java.awt.EventQueue;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainWindow extends JFrame
{

	private JPanel contentPane;
	private CardLayout layout;
	public GUI guiObject;
	// Different Cards to show
	
	protected MainMenu menuScreen;
	protected GamePanel gameplayScreen;
	protected HighScore highScoreScreen;
	protected Trivia triviaScreen;
	protected Lose loseScreen;
	protected Win winScreen;
	protected ForDebugging debug;
	
	
	public GUI getGUI()
	{
		return guiObject;
	}

	/**
	 * Create the frame.
	 */
	protected MainWindow(GUI guiObject)
	{
		this.guiObject = guiObject;
		
		menuScreen = new MainMenu(guiObject);
		gameplayScreen = new GamePanel(guiObject);
		highScoreScreen = new HighScore(guiObject);
		triviaScreen = new Trivia(guiObject);
		loseScreen = new Lose(guiObject);
		winScreen = new Win(guiObject);
		debug = new ForDebugging(guiObject);		
		
		setTitle("Hunt The Wumpus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Card Layout Code
		
		layout = new CardLayout(0,0);
		contentPane.setLayout(layout);
		
		contentPane.add(menuScreen, GUI.titleScreen);
		contentPane.add(gameplayScreen, GUI.gameplay);
		contentPane.add(highScoreScreen, GUI.highScoreNameEnter);
		contentPane.add(triviaScreen, GUI.trivia);
		contentPane.add(loseScreen, GUI.LoseScreen);
		contentPane.add(winScreen, GUI.WinScreen);
		contentPane.add(debug, GUI.debugging);
	}

	
	// --- My Code --- //
	
	protected void changeView(String changeView)
	{
		layout.show(contentPane, changeView);
		//String update = "";
		//menuScreen.updatePanel();
		//gameplayScreen.updatePanel(update);
	}
}
