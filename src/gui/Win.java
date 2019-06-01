package gui;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class Win extends JPanel implements UpdateScreen
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1866960958992003815L;

	private GUI gui;
	
	private JList<String> hScores;
	private DefaultListModel<String> myScores;
	
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
		
		hScores = new JList<String>();
		hScores.setSelectionBackground(new Color(255, 215, 0));
		hScores.setBackground(new Color(255, 255, 153));
		hScores.setFont(new Font("Georgia", Font.PLAIN, 18));
		hScores.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		hScores.setVisibleRowCount(10);
		hScores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hScores.setBounds(54, 442, 176, 257);
		add(hScores);

	}

	public void updatePanel(String update)
	{
		myScores = new DefaultListModel<String>();
		for(String s : gui.getHighScores())
		{
			myScores.addElement(s);
		}
		
		hScores.setModel(myScores);
	}
}
