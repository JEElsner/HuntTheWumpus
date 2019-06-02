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

public class Win extends JPanel implements UpdateScreen
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1866960958992003815L;

	private GUI gui;
	
	private JList<String> hScores;
	private JLabel yourScore;
	
	/**
	 * Create the panel.
	 */
	public Win(GUI guiObject)
	{
		setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setBackground(new Color(222, 184, 135));
		gui = guiObject;
		
		setLayout(null);
		this.setSize(1100, 1000);
		
		JLabel lblYouWin = new JLabel("YOU WIN");
		lblYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWin.setForeground(new Color(34, 139, 34));
		lblYouWin.setFont(new Font("Papyrus", Font.BOLD, 26));
		lblYouWin.setBounds(439, 48, 184, 90);
		add(lblYouWin);
		
		JButton btnNewButton = new JButton("CLICK HERE TO PLAY AGAIN");
		btnNewButton.setFont(new Font("Georgia", Font.BOLD, 18));
		btnNewButton.setBorder(new LineBorder(new Color(178, 34, 34), 4));
		btnNewButton.setBackground(new Color(240, 128, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnNewButton.setBounds(85, 852, 328, 40);
		add(btnNewButton);
		
		yourScore = new JLabel("Congratulations! You got a Score of " + gui.getScore() + " Points!");
		yourScore.setHorizontalAlignment(SwingConstants.CENTER);
		yourScore.setFont(new Font("Papyrus", Font.BOLD, 22));
		yourScore.setBounds(267, 179, 530, 50);
		add(yourScore);
		
		JLabel lblThankYouFor = new JLabel("Thank you for playing!");
		lblThankYouFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblThankYouFor.setFont(new Font("Papyrus", Font.BOLD, 22));
		lblThankYouFor.setBounds(358, 295, 344, 64);
		add(lblThankYouFor);
		
		JButton btnClickHereTo = new JButton("CLICK HERE TO STOP");
		btnClickHereTo.setFont(new Font("Georgia", Font.BOLD, 18));
		btnClickHereTo.setBorder(new LineBorder(new Color(178, 34, 34), 4));
		btnClickHereTo.setBackground(new Color(240, 128, 128));
		btnClickHereTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.notifyControl(new Update(UpdateType.WINDOW_CLOSING, true));
			}
		});
		btnClickHereTo.setBounds(686, 852, 328, 40);
		add(btnClickHereTo);
		
		JLabel lblYourNameWill = new JLabel("If you got a High Score, you name and score will appear on the main menu and below!");
		lblYourNameWill.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourNameWill.setFont(new Font("Papyrus", Font.BOLD, 22));
		lblYourNameWill.setBounds(61, 242, 947, 40);
		add(lblYourNameWill);
		
		hScores = new JList<String>();
		hScores.setSelectionBackground(new Color(255, 215, 0));
		hScores.setBackground(new Color(255, 255, 153));
		hScores.setFont(new Font("Georgia", Font.PLAIN, 18));
		hScores.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		hScores.setVisibleRowCount(10);
		hScores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hScores.setBounds(456, 505, 176, 257);
		add(hScores);
		
		JLabel label = new JLabel("High Scores");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Papyrus", Font.BOLD, 24));
		label.setBounds(456, 447, 160, 45);
		add(label);
		
		JLabel lblDoubleClickTo = new JLabel("Double click to view more stats");
		lblDoubleClickTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoubleClickTo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblDoubleClickTo.setBounds(401, 775, 301, 32);
		add(lblDoubleClickTo);
		
		hScores.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
				{
					gui.setNameScore(hScores.getSelectedValue());
					gui.notifyControl(new Update(UpdateType.GET_SCORE_STATS, true, hScores.getSelectedIndex()));
				}
			}
			});

	}

	public void updatePanel(String update)
	{
		yourScore.setText("Congratulations! You got a Score of " + gui.getScore() + " Points!");
		
		DefaultListModel<String> m1 = new DefaultListModel<String>();
		m1.setSize(10);
		for(int i = 0; i < 10; i++)
		{
			m1.setElementAt(gui.getHighScores().get(i), i);
		}
		
		hScores.setModel(m1);
	}
}
