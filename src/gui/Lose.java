package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lose extends JPanel implements UpdateScreen
{
	/**
	 * Create the panel.
	 */
	public Lose(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblYouLose = new JLabel("YOU LOSE");
		lblYouLose.setBounds(180, 13, 92, 16);
		add(lblYouLose);
		
		JButton btnMainMenu = new JButton("CLICK HERE TO PLAY AGAIN");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(12, 262, 193, 25);
		add(btnMainMenu);
		
		JButton button = new JButton("CLICK HERE TO STOP");
		button.setBounds(245, 262, 193, 25);
		add(button);
		
		JLabel lblYouHaveSuffered = new JLabel("You have suffered a terrible death. Thank you for playing!");
		lblYouHaveSuffered.setBounds(47, 112, 360, 16);
		add(lblYouHaveSuffered);

	}
	public void updatePanel(String update)
	{
		
	}

}
