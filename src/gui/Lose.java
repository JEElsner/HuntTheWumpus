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

public class Lose extends JPanel implements UpdateScreen
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7047099827406529827L;
	
	private GUI gui;
	
	private JList<String> hScores;
	private JLabel yourScore;
	/**
	 * Create the panel.
	 */
	public Lose(GUI guiObject)
	{;
		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 800);
		
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.notifyControl(new Update(UpdateType.WINDOW_CLOSING, true));
			}
		});
		button.setBounds(245, 262, 193, 25);
		add(button);
		
		JLabel lblYouHaveSuffered = new JLabel("You have suffered a terrible death. Thank you for playing!");
		lblYouHaveSuffered.setBounds(47, 112, 360, 16);
		add(lblYouHaveSuffered);
		
		hScores = new JList<String>();
		hScores.setSelectionBackground(new Color(255, 215, 0));
		hScores.setBackground(new Color(255, 255, 153));
		hScores.setFont(new Font("Georgia", Font.PLAIN, 18));
		hScores.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		hScores.setVisibleRowCount(10);
		hScores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hScores.setBounds(54, 442, 176, 257);
		add(hScores);
		
		yourScore = new JLabel("You had a score of:");
		yourScore.setBounds(174, 65, 187, 16);
		add(yourScore);

	}
	public void updatePanel(String update)
	{
		yourScore.setText("You had a score of: " + gui.getScore() + "points!");
		
		DefaultListModel<String> m1 = new DefaultListModel<String>();
		m1.setSize(10);
		for(int i = 0; i < 10; i++)
		{
			m1.setElementAt(gui.getHighScores().get(i), i);
		}
		
		hScores.setModel(m1);
	}

}
