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
		
		JButton btnForDebuggin = new JButton("For Debuggin");
		btnForDebuggin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.debugging);
			}
		});
		btnForDebuggin.setBounds(314, 250, 124, 25);
		add(btnForDebuggin);
		
		JLabel lblHighScores = new JLabel("High Scores");
		lblHighScores.setBounds(58, 154, 74, 16);
		add(lblHighScores);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(68, 183, 56, 16);
		add(lblNewLabel);

	}
}
