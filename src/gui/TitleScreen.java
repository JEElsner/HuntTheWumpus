package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleScreen extends JPanel
{

	/**
	 * Create the panel.
	 */
	public TitleScreen(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblHuntTheWumpus = new JLabel("Hunt the Wumpus");
		lblHuntTheWumpus.setBounds(146, 47, 173, 16);
		add(lblHuntTheWumpus);
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.mainWindow.changeView(GUI.titleScreen);
				guiObject.mainWindow.menuScreen.updatePanel("HS");
			}
		});
		btnPlayGame.setBounds(157, 152, 117, 25);
		add(btnPlayGame);

	}
}
