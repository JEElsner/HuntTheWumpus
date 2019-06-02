package gui;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
	{
		setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setBackground(new Color(222, 184, 135));;
		gui = guiObject;
		setLayout(null);
		this.setSize(1100, 1000);
		
		JLabel lblYouLose = new JLabel("YOU LOSE");
		lblYouLose.setForeground(new Color(178, 34, 34));
		lblYouLose.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouLose.setFont(new Font("Papyrus", Font.BOLD, 26));
		lblYouLose.setBounds(409, 37, 227, 68);
		add(lblYouLose);
		
		JButton btnMainMenu = new JButton("CLICK HERE TO PLAY AGAIN");
		btnMainMenu.setBorder(new LineBorder(new Color(178, 34, 34), 4));
		btnMainMenu.setBackground(new Color(240, 128, 128));
		btnMainMenu.setFont(new Font("Georgia", Font.BOLD, 18));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(74, 791, 328, 40);
		add(btnMainMenu);
		
		JButton button = new JButton("CLICK HERE TO STOP");
		button.setBorder(new LineBorder(new Color(178, 34, 34), 4));
		button.setBackground(new Color(240, 128, 128));
		button.setFont(new Font("Georgia", Font.BOLD, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.notifyControl(new Update(UpdateType.WINDOW_CLOSING, true));
			}
		});
		button.setBounds(699, 791, 328, 40);
		add(button);
		
		JLabel lblYouHaveSuffered = new JLabel("You have suffered a terrible death. Thank you for playing!");
		lblYouHaveSuffered.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouHaveSuffered.setFont(new Font("Papyrus", Font.BOLD, 22));
		lblYouHaveSuffered.setBounds(180, 181, 707, 68);
		add(lblYouHaveSuffered);
		
		hScores = new JList<String>();
		hScores.setSelectionBackground(new Color(255, 215, 0));
		hScores.setBackground(new Color(255, 255, 153));
		hScores.setFont(new Font("Georgia", Font.PLAIN, 18));
		hScores.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		hScores.setVisibleRowCount(10);
		hScores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hScores.setBounds(434, 380, 176, 257);
		add(hScores);
		
		hScores.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
				{
					gui.setNameScore(hScores.getSelectedValue());
					gui.notifyControl(new Update(UpdateType.GET_SCORE_STATS, true, hScores.getSelectedIndex()));
				}
			}
			});
		
		yourScore = new JLabel("You had a score of:");
		yourScore.setHorizontalAlignment(SwingConstants.CENTER);
		yourScore.setFont(new Font("Papyrus", Font.BOLD, 22));
		yourScore.setBounds(324, 118, 399, 40);
		add(yourScore);
		
		JLabel label = new JLabel("High Scores");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Papyrus", Font.BOLD, 24));
		label.setBounds(434, 322, 160, 45);
		add(label);
		
		JLabel label_1 = new JLabel("Double click to view more stats");
		label_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		label_1.setBounds(392, 650, 301, 32);
		add(label_1);

	}
	public void updatePanel(String update)
	{
		yourScore.setText("You had a score of: " + gui.getScore() + " points!");
		
		DefaultListModel<String> m1 = new DefaultListModel<String>();
		m1.setSize(10);
		for(int i = 0; i < 10; i++)
		{
			m1.setElementAt(gui.getHighScores().get(i), i);
		}
		
		hScores.setModel(m1);
	}

}
