package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;

import java.awt.Color;
import java.awt.Font;

public class MainMenu extends JPanel implements UpdateScreen
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6627299953571002199L;
	/**
	 * Create the panel.
	 */
	private GUI gui;
	private String updateRequired = "yes";
	private JTextField plyrName;
	private JList<String> list;
	private JList<String> caves;
	private DefaultListModel<String> options = new DefaultListModel<String>();
	
	private JLabel nameError;
	private JLabel errorCave;
	JButton seeStats;
	private JLabel errorStats;
	
	private String myStats;
	
	public String getMyStats()
	{
		return myStats;
	}
	
	public void setMyStats(List<String> stats)
	{
		myStats = stats.get(0);
	}
	
	public MainMenu(GUI guiObject)
	{
		setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setBackground(new Color(222, 184, 135));

		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 930);

		JLabel lblTitle = new JLabel("Hunt The Wumpus");
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 36));
		lblTitle.setBounds(302, 13, 367, 45);
		add(lblTitle);
		
//		JButton btnForDebuggin = new JButton("For Debuggin");
//		btnForDebuggin.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) 
//			{
//				gui.mainWindow.debug.updatePanel("ye");
//				gui.mainWindow.changeView(GUI.debugging);
//			}
//		});
//		btnForDebuggin.setBounds(864, 874, 124, 25);
//		add(btnForDebuggin);
		
		JLabel lblHighScores = new JLabel("High Scores");
		lblHighScores.setFont(new Font("Papyrus", Font.BOLD, 24));
		lblHighScores.setBounds(70, 384, 160, 45);
		add(lblHighScores);
		
//		JButton btnUpdate = new JButton("Update");
//		btnUpdate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				updatePanel("yes");
//			}
//		});
//		btnUpdate.setBounds(12, 874, 97, 25);
//		add(btnUpdate);
		
		JLabel enterName = new JLabel("Enter Your 3 Character Name:");
		enterName.setFont(new Font("Papyrus", Font.BOLD, 18));
		enterName.setBounds(342, 227, 282, 32);
		add(enterName);
		
		// REVIEW Add Key listeners to components where pressing enter should start a new game or do something else
		plyrName = new JTextField();
		plyrName.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		plyrName.setFont(new Font("Papyrus", Font.BOLD, 18));
		plyrName.setBackground(new Color(245, 222, 179));
		plyrName.setBounds(445, 272, 74, 35);
		add(plyrName);
		plyrName.setColumns(10);
		
		nameError = new JLabel("Error! Name entered is invalid, please try again with a name that is 3 characters long");
		nameError.setForeground(new Color(139, 69, 19));
		nameError.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nameError.setBounds(145, 154, 662, 25);
		nameError.setVisible(false);
		add(nameError);
		
		// TODO Implement Choosing a cave//COMPLETED
		JButton btnNewgame = new JButton("New Game");
		btnNewgame.setFont(new Font("Georgia", Font.BOLD, 20));
		btnNewgame.setBackground(new Color(154, 205, 50));
		btnNewgame.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				gui.setName(plyrName.getText());
				if(gui.getName().length() != 3)
				{
					nameError.setVisible(true);
				}
				
				else if(caves.isSelectionEmpty())
				{
					errorCave.setVisible(true);
				}
				
				else {
					gui.notifyControl(new Update(UpdateType.PLAYER_NAME, true, gui.getName()));
					gui.notifyControl(new Update(UpdateType.NEW_GAME, true, checkCave()));
					nameError.setVisible(false);
					errorCave.setVisible(false);
					gui.mainWindow.gameplayScreen.updatePanel("name");
					gui.mainWindow.changeView(GUI.gameplay);
				}
			}
		});
		btnNewgame.setBounds(420, 505, 140, 55);
		add(btnNewgame);
				
		list = new JList<String>();
		list.setSelectionBackground(new Color(255, 215, 0));
		list.setBackground(new Color(255, 255, 153));
		list.setFont(new Font("Georgia", Font.PLAIN, 18));
		list.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		list.setVisibleRowCount(10);
		//list.addMouseListener(mListen);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(54, 442, 176, 257);
		add(list);
		
		seeStats = new JButton("See Stats");
		seeStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() == -1)
				{
					errorStats.setVisible(true);
				}
				else
				{
					gui.setNameScore(list.getSelectedValue());
					gui.notifyControl(new Update(UpdateType.GET_SCORE_STATS, true, list.getSelectedIndex()));
//					ScoreDetails details = gui.getDetails();
//					details.settingStats(gui.getScoreStats());	
//					details.settingScores(list.getSelectedValuesList());
					//gui.getDetails().setVisible(true);
				}
			}
		});
		seeStats.setBounds(96, 719, 97, 25);
		add(seeStats);

		

		
		JLabel lblChooseYourCave = new JLabel("Choose your Cave");
		lblChooseYourCave.setFont(new Font("Papyrus", Font.BOLD, 24));
		lblChooseYourCave.setBounds(700, 390, 252, 45);
		add(lblChooseYourCave);
		
		options.addElement("Cave One");
		options.addElement("Cave Two");
		options.addElement("Cave Three");
		options.addElement("Cave Four");
		options.addElement("Cave Five");
		options.addElement("Random Cave");
		
		caves = new JList<String>();
		caves.setSelectionBackground(new Color(107, 142, 35));
		caves.setBackground(new Color(189, 183, 107));
		caves.setFont(new Font("Papyrus", Font.BOLD, 20));
		caves.setBorder(new LineBorder(new Color(0, 128, 0), 3, true));
		caves.setModel(options);
		caves.setVisibleRowCount(6);
		caves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		caves.setBounds(725, 455, 176, 218);
		add(caves);
		
		errorCave = new JLabel("Error! Please select a Cave!");
		errorCave.setForeground(new Color(139, 69, 19));
		errorCave.setFont(new Font("Times New Roman", Font.BOLD, 18));
		errorCave.setBounds(380, 125, 226, 16);
		add(errorCave);
		errorCave.setVisible(false);
		
		errorStats = new JLabel("Error! Please select a player to view stats for!");
		errorStats.setForeground(new Color(139, 69, 19));
		errorStats.setFont(new Font("Times New Roman", Font.BOLD, 18));
		errorStats.setBounds(12, 757, 372, 35);
		add(errorStats);
		errorStats.setVisible(false);

		
		
	}
	

	//What you could do, IDK if this is a good idea
	public void repaint()
	{
		updatePanel(updateRequired);
		
		super.repaint();
	}
	
	public int checkCave()
	{		
		for(int i = 0; i < 6; i++)
		{
			if(caves.isSelectedIndex(i))
			{
				gui.setCaveSelected(i + 1);
			}
		}
		return gui.getCaveSelected();
	}
	
	public void updatePanel(String update)
	{
		if(gui == null)
			return;
		DefaultListModel<String> m1 = new DefaultListModel<String>();
		m1.setSize(10);
		for(int i = 0; i < 10; i++)
		{
			m1.setElementAt(gui.getHighScores().get(i), i);
		}
		
		list.setModel(m1);
		
	}
}
