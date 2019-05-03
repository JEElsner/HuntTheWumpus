package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainMenu extends JPanel
{

	/**
	 * Create the panel.
	 */
	private GUI gui;
	private JLabel playerNameLbl;
	
	public MainMenu(GUI guiObject)
	{

		gui = guiObject;
		setLayout(null);

		JLabel lblTitle = new JLabel("Hunt The Wumpus");
		lblTitle.setBounds(172, 5, 106, 16);
		add(lblTitle);

		JButton btnNewgame = new JButton("NewGame");
		btnNewgame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				gui.mainWindow.changeView(GUI.gameplay);
			}
		});
		btnNewgame.setBounds(172, 92, 97, 25);
		add(btnNewgame);
		
		JButton btnForDebuggin = new JButton("For Debuggin");
		btnForDebuggin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gui.mainWindow.changeView(GUI.debugging);
			}
		});
		btnForDebuggin.setBounds(314, 250, 124, 25);
		add(btnForDebuggin);
		
		JLabel lblHighScores = new JLabel("High Scores");
		lblHighScores.setBounds(58, 154, 74, 16);
		add(lblHighScores);
		
		playerNameLbl = new JLabel();
		playerNameLbl.setText("Hello " + gui.getName());
		playerNameLbl.setBounds(68, 183, 106, 16);
		add(playerNameLbl);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerNameLbl.setText("Hello " + gui.getName());
			}
		});
		btnUpdate.setBounds(77, 250, 97, 25);
		add(btnUpdate);
		
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				playerNameLbl.setText("Hello " + gui.getName());
				System.out.println("Focus");
			}
		});
	}
	
	//What you could do, IDK if this is a good idea
	public void repaint()
	{
		updatePanel();
		
		super.repaint();
	}
	
	public void updatePanel()
	{
		if(gui == null)
			return;
		
		playerNameLbl.setText("Hello " + gui.getName());
	}
}
