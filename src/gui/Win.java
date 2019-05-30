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

	private GUI gui;
	
	/**
	 * Create the panel.
	 */
	public Win(GUI guiObject)
	{
		gui = guiObject;
		
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
		
		JLabel lblCongratulationsYouGot = new JLabel("Congratulations! You got a Score of " + gui.getScore() + " Points!");
		lblCongratulationsYouGot.setBounds(75, 93, 411, 16);
		add(lblCongratulationsYouGot);
		
		JLabel lblThankYouFor = new JLabel("Thank you for playing!");
		lblThankYouFor.setBounds(152, 196, 139, 16);
		add(lblThankYouFor);
		
		JButton btnClickHereTo = new JButton("CLICK HERE TO STOP");
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.notifyControl(new Update(UpdateType.WINDOW_CLOSING, true));
			}
		});
		btnClickHereTo.setBounds(245, 262, 193, 25);
		add(btnClickHereTo);
		
		JLabel lblYourNameWill = new JLabel("If you got a High Score, you name, score, and cave will appear on the main menu!");
		lblYourNameWill.setBounds(12, 137, 489, 16);
		add(lblYourNameWill);

	}

	public void updatePanel(String update)
	{
		
	}
}
