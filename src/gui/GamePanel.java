package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GamePanel extends JPanel
{
	/**
	 * Create the panel.
	 */
	public GamePanel(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Game Panel");
		lblTitle.setBounds(191, 5, 68, 16);
		add(lblTitle);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(162, 137, 97, 25);
		add(btnMainMenu);

	}
	public void updatePanel()
	{
		
	}

}
