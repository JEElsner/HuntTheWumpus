package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lose extends JPanel
{

	/**
	 * Create the panel.
	 */
	public Lose()
	{
		setLayout(null);
		
		JLabel lblYouLose = new JLabel("YOU LOSE");
		lblYouLose.setBounds(180, 13, 92, 16);
		add(lblYouLose);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(164, 117, 97, 25);
		add(btnMainMenu);

	}

}
