package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

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
	//private JLabel errorStats;
	
	private String myStats;
	private JLabel label;
	private JButton btnQuit;
	
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
		this.setSize(1100, 1000);

		JLabel lblTitle = new JLabel("Hunt The Wumpus");
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 36));
		lblTitle.setBounds(370, 13, 367, 45);
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
		lblHighScores.setBounds(138, 384, 160, 45);
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
		enterName.setBounds(410, 227, 282, 32);
		add(enterName);
		
		// REVIEW Add Key listeners to components where pressing enter should start a new game or do something else
		plyrName = new JTextField();
		plyrName.setHorizontalAlignment(SwingConstants.CENTER);
		plyrName.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		plyrName.setFont(new Font("Papyrus", Font.BOLD, 18));
		plyrName.setBackground(new Color(245, 222, 179));
		plyrName.setBounds(513, 272, 74, 35);
		add(plyrName);
		
		plyrName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					startNewGame();
				}
			}
		});
		
		nameError = new JLabel("Error! Invalid name, enter a name containing 3 alphabetic characters and no spaces");
		nameError.setForeground(new Color(139, 69, 19));
		nameError.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nameError.setBounds(234, 154, 779, 25);
		nameError.setVisible(false);
		add(nameError);
		
		JButton btnNewgame = new JButton("New Game");
		btnNewgame.setFont(new Font("Georgia", Font.BOLD, 20));
		btnNewgame.setBackground(new Color(154, 205, 50));
		btnNewgame.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				startNewGame();
			}
		});
		btnNewgame.setBounds(488, 505, 140, 55);
		add(btnNewgame);
				
		list = new JList<String>();
		list.setSelectionBackground(new Color(255, 215, 0));
		list.setBackground(new Color(255, 255, 153));
		list.setFont(new Font("Georgia", Font.PLAIN, 18));
		list.setBorder(new LineBorder(new Color(218, 165, 32), 6));
		list.setVisibleRowCount(10);
		//list.addMouseListener(mListen);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(122, 442, 176, 257);
		add(list);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
				{
					gui.setNameScore(list.getSelectedValue());
					gui.notifyControl(new Update(UpdateType.GET_SCORE_STATS, true, list.getSelectedIndex()));
				}
			}
			});
		
//		seeStats = new JButton("See Stats");
//		seeStats.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(list.getSelectedIndex() == -1)
//				{
//					errorStats.setVisible(true);
//				}
//				else
//				{
//					gui.setNameScore(list.getSelectedValue());
//					gui.notifyControl(new Update(UpdateType.GET_SCORE_STATS, true, list.getSelectedIndex()));
////					ScoreDetails details = gui.getDetails();
////					details.settingStats(gui.getScoreStats());	
////					details.settingScores(list.getSelectedValuesList());
//					//gui.getDetails().setVisible(true);
//				}
//			}
//		});
//		seeStats.setBounds(96, 719, 97, 25);
//		add(seeStats);

		

		
		JLabel lblChooseYourCave = new JLabel("Choose your Cave");
		lblChooseYourCave.setFont(new Font("Papyrus", Font.BOLD, 24));
		lblChooseYourCave.setBounds(768, 390, 252, 45);
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
		caves.setBounds(793, 455, 176, 218);
		add(caves);
		
		caves.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
				{
					startNewGame();
				}
			}
			});
		
		errorCave = new JLabel("Error! Please select a Cave!");
		errorCave.setForeground(new Color(139, 69, 19));
		errorCave.setFont(new Font("Times New Roman", Font.BOLD, 18));
		errorCave.setBounds(448, 125, 226, 16);
		add(errorCave);
		
		label = new JLabel("Double click to view more stats");
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		label.setBounds(80, 712, 301, 32);
		add(label);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.notifyControl(new Update(UpdateType.WINDOW_CLOSING, true));
			}
		});
		btnQuit.setFont(new Font("Georgia", Font.BOLD, 20));
		btnQuit.setBorder(new LineBorder(new Color(0, 128, 0), 4));
		btnQuit.setBackground(new Color(154, 205, 50));
		btnQuit.setBounds(488, 820, 140, 55);
		add(btnQuit);
		errorCave.setVisible(false);
		
//		errorStats = new JLabel("Error! Please select a player to view stats for!");
//		errorStats.setForeground(new Color(139, 69, 19));
//		errorStats.setFont(new Font("Times New Roman", Font.BOLD, 18));
//		errorStats.setBounds(12, 757, 372, 35);
//		add(errorStats);
//		errorStats.setVisible(false);

		
		
	}
	

	//What you could do, IDK if this is a good idea
	public void repaint()
	{
		updatePanel(updateRequired);
		
		super.repaint();
	}
	
	public void startNewGame()
	{
		gui.setName(plyrName.getText());
		if(gui.getName().replaceAll(" ", "").length() != 3 || !(isAlphabet(gui.getName())))
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
			gui.mainWindow.gameplayScreen.clear();
			gui.mainWindow.changeView(GUI.gameplay);
		}
	}
	
	public boolean isAlphabet(String str)
	{
			for(int i = 0; i < str.length(); i++)
			{
				if(!(Character.isLetter(str.charAt(i))))
				{
					return false;
				}
			}
		return true;
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
