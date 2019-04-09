package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel
{

	/**
	 * Create the panel.
	 */
	public MainMenu()
	{
		setLayout(null);

		JLabel lblTitle = new JLabel("Hunt The Wumpus");
		lblTitle.setBounds(172, 5, 106, 16);
		add(lblTitle);

		JButton btnNewgame = new JButton("NewGame");
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				GUI.mainWindow.changeView(GUI.gameplay);
			}
		});
		btnNewgame.setBounds(172, 92, 97, 25);
		add(btnNewgame);
		
		JButton btnEnterHighScore = new JButton("Enter High Score");
		btnEnterHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.highScoreNameEnter);
			}
		});
		btnEnterHighScore.setBounds(152, 124, 129, 25);
		add(btnEnterHighScore);
		
		JButton btnWin = new JButton("Win");
		btnWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.WinScreen);
			}
		});
		btnWin.setBounds(172, 162, 97, 25);
		add(btnWin);
		
		JButton btnLose = new JButton("Lose");
		btnLose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.LoseScreen);
			}
		});
		btnLose.setBounds(172, 190, 97, 25);
		add(btnLose);
		
		JButton btnTriva = new JButton("Triva");
		btnTriva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.trivia);
			}
		});
		btnTriva.setBounds(172, 228, 97, 25);
		add(btnTriva);

	}
}
