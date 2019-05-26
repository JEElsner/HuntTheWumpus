package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Win extends JPanel implements UpdateScreen
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1866960958992003815L;

	/**
	 * Create the panel.
	 */
	public Win(GUI guiObject)
	{
		setLayout(null);
		this.setSize(1000, 800);
		
		JLabel lblYouWin = new JLabel("YOU WIN");
		lblYouWin.setBounds(189, 32, 56, 16);
		add(lblYouWin);
		
		JButton btnNewButton = new JButton("CLICK HERE TO PLAY AGAIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnNewButton.setBounds(12, 262, 193, 25);
		add(btnNewButton);
		
		JLabel lblCongratulationsYouGot = new JLabel("Congratulations! You got a High Score of [Get Points Here] Points!");
		lblCongratulationsYouGot.setBounds(39, 72, 411, 16);
		add(lblCongratulationsYouGot);
		
		JLabel lblThankYouFor = new JLabel("Thank you for playing!");
		lblThankYouFor.setBounds(152, 196, 139, 16);
		add(lblThankYouFor);
		
		JButton btnClickHereTo = new JButton("CLICK HERE TO STOP");
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClickHereTo.setBounds(245, 262, 193, 25);
		add(btnClickHereTo);
		
		JLabel lblYourNameWill = new JLabel("Your name will appear on the Main Menu under High Scores!");
		lblYourNameWill.setBounds(49, 95, 364, 16);
		add(lblYourNameWill);
		
		JLabel lblCongratulationsOnYour = new JLabel("Congratulations on your victory! Unfortunately you didn't get a High Score");
		lblCongratulationsOnYour.setBounds(12, 167, 426, 16);
		add(lblCongratulationsOnYour);

	}

	public void updatePanel(String update)
	{
		
	}
}
