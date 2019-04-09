package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trivia extends JPanel
{

	/**
	 * Create the panel.
	 */
	public Trivia()
	{
		setLayout(null);
		
		JLabel lblTrivia = new JLabel("Trivia");
		lblTrivia.setBounds(184, 13, 56, 16);
		add(lblTrivia);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(168, 96, 97, 25);
		add(btnMainMenu);

	}

}
