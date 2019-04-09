package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Win extends JPanel
{

	/**
	 * Create the panel.
	 */
	public Win()
	{
		setLayout(null);
		
		JLabel lblYouWin = new JLabel("YOU WIN");
		lblYouWin.setBounds(192, 13, 56, 16);
		add(lblYouWin);
		
		JButton btnNewButton = new JButton("Main Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnNewButton.setBounds(179, 132, 97, 25);
		add(btnNewButton);

	}

}
