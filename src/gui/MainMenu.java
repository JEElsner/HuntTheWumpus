package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

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
	DefaultListModel<String> m1;
	private JLabel nameError;
	
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
				gui.mainWindow.debug.updatePanel("ye");
				gui.mainWindow.changeView(GUI.debugging);
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
		
		JLabel enterName = new JLabel("Enter Your 3 Character Name:");
		enterName.setBounds(232, 75, 176, 16);
		add(enterName);
		
		// REVIEW Add Key listeners to components where pressing enter should start a new game or do something else
		plyrName = new JTextField();
		plyrName.setBounds(420, 72, 116, 22);
		add(plyrName);
		plyrName.setColumns(10);
		
		nameError = new JLabel("Error! Name entered is invalid, please try again with a name that is 3 characters long");
		nameError.setBounds(157, 45, 503, 16);
		nameError.setVisible(false);
		add(nameError);
		
		// TODO Implement Choosing a cave
		JButton btnNewgame = new JButton("NewGame");
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				gui.setName(plyrName.getText());
				if(gui.getName().length() != 3)
				{
					nameError.setVisible(true);
				}
				
				else {
					nameError.setVisible(false);
					gui.mainWindow.changeView(GUI.gameplay);
					gui.mainWindow.gameplayScreen.updatePanel("name");
					gui.notifyControl(new Update(UpdateType.NEW_GAME, true));
				}
			}
		});
		btnNewgame.setBounds(358, 104, 97, 25);
		add(btnNewgame);
		
		
		list = new JList<String>();
		list.setVisibleRowCount(10);
		list.setBounds(224, 274, -141, -88);
		add(list);
		
		
		
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
		System.out.println(gui.getHighScores());
		
		m1 = new DefaultListModel<String>();
		for(String s : gui.getHighScores())
		{
			m1.addElement(s);
		}
		
		list.setModel(m1);
		System.out.println(m1);
		
	}
}
