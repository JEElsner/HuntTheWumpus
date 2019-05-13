package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class MainMenu extends JPanel implements UpdateScreen
{

	/**
	 * Create the panel.
	 */
	private GUI gui;
	private String updateRequired = "yes";
	private JTextField plyrName;
	private JLabel highScore1;
	private JLabel highScore2;
	private JLabel highScore3;
	private JLabel highScore4;
	private JLabel highScore5;
	
	public MainMenu(GUI guiObject)
	{

		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 800);

		JLabel lblTitle = new JLabel("Hunt The Wumpus");
		lblTitle.setBounds(332, 0, 106, 16);
		add(lblTitle);
		
		JButton btnForDebuggin = new JButton("For Debuggin");
		btnForDebuggin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gui.mainWindow.changeView(GUI.debugging);
				gui.setHs1(plyrName.getText());
			}
		});
		btnForDebuggin.setBounds(656, 460, 124, 25);
		add(btnForDebuggin);
		
		JLabel lblHighScores = new JLabel("High Scores");
		lblHighScores.setBounds(121, 154, 74, 16);
		add(lblHighScores);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(12, 460, 97, 25);
		add(btnUpdate);
		
		JLabel enterName = new JLabel("Enter Your Name:");
		enterName.setBounds(290, 75, 106, 16);
		add(enterName);
		
		plyrName = new JTextField();
		plyrName.setBounds(420, 72, 116, 22);
		add(plyrName);
		plyrName.setColumns(10);
		
		JButton btnNewgame = new JButton("NewGame");
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				gui.setName(plyrName.getText());
				gui.mainWindow.changeView(GUI.gameplay);
				gui.mainWindow.gameplayScreen.updatePanel("name");
			}
		});
		btnNewgame.setBounds(358, 104, 97, 25);
		add(btnNewgame);
		
		highScore1 = new JLabel("1. " + gui.getHs1());
		highScore1.setBounds(88, 184, 56, 16);
		add(highScore1);
		
		
		highScore2 = new JLabel("2. " + gui.getHs2());
		highScore2.setBounds(88, 202, 56, 16);
		add(highScore2);
		
		highScore3 = new JLabel("3. " + gui.getHs3());
		highScore3.setBounds(88, 224, 56, 16);
		add(highScore3);
		
		highScore4 = new JLabel("4. " + gui.getHs4());
		highScore4.setBounds(88, 242, 56, 16);
		add(highScore4);
		
		highScore5 = new JLabel("5. " + gui.getHs5());
		highScore5.setBounds(88, 266, 56, 16);
		add(highScore5);
	}
	
	//What you could do, IDK if this is a good idea
	public void repaint()
	{
		updatePanel(updateRequired);
		
		super.repaint();
	}
	
	public void updatePanel(String update)
	{
		if(gui == null)
			return;
		highScore1.setText("1. " + gui.getHs1());
		highScore2.setText("2. " + gui.getHs2());
		highScore3.setText("3. " + gui.getHs3());
		highScore4.setText("4. " + gui.getHs4());
		highScore5.setText("5. " + gui.getHs5());
	}
}
