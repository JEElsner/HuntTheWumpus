package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HighScore extends JPanel
{

	/**
	 * Create the panel.
	 */
	public HighScore()
	{
		setLayout(null);
		
		JLabel lblHighScore = new JLabel("High Score");
		lblHighScore.setBounds(173, 13, 67, 16);
		add(lblHighScore);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(157, 127, 97, 25);
		add(btnMainMenu);

	}
}
