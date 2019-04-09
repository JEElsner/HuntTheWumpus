package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
	
	// Different Cards to show
	
	private JPanel menuScreen = new MainMenu();
	private JPanel gameplayScreen = new GamePanel();
	private JPanel highScoreScreen = new HighScore();
	private JPanel triviaScreen = new Trivia();
	private JPanel loseScreen = new Lose();
	private JPanel winScreen = new Win();
	

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	protected MainWindow()
	{
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
	}

	
	// --- My Code --- //
	
	protected void changeView(String changeView)
	{
		layout.show(contentPane, changeView);
	}
}
