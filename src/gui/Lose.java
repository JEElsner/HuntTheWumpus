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
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(164, 117, 97, 25);
		add(btnMainMenu);

	}
	public void updatePanel(String update)
	{
		
	}

}
