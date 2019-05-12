package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ForDebugging extends JPanel implements UpdateScreen
{
	private GUI gui;
	
	/**
	 * Create the panel.
	 */
	public ForDebugging(GUI guiObject)
	{
		gui = guiObject;
		
		setLayout(null);
		
		JLabel lblForDebugging = new JLabel("For Debugging");
		lblForDebugging.setBounds(185, 13, 105, 16);
		add(lblForDebugging);
		
		JButton btnWin = new JButton("Win");
		btnWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.WinScreen);
			}
		});
		btnWin.setBounds(172, 162, 97, 25);
		add(btnWin);
		
		JButton btnLose = new JButton("Lose");
		btnLose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.LoseScreen);
			}
		});
		btnLose.setBounds(172, 190, 97, 25);
		add(btnLose);
		
		JButton btnTriva = new JButton("Triva");
		btnTriva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.trivia);
			}
		});
		btnTriva.setBounds(172, 228, 97, 25);
		add(btnTriva);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
				guiObject.mainWindow.menuScreen.updatePanel("HS");
			}
		});
		btnMainMenu.setBounds(295, 228, 97, 25);
		add(btnMainMenu);
		
		JButton btnTestNotify = new JButton("Test Notify");
		btnTestNotify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.DEBUG, true, "Hey, this is debug!"));
			}
		});
		btnTestNotify.setBounds(24, 243, 97, 25);
		add(btnTestNotify);

	}
	
	public void updatePanel(String update)
	{
		
	}

}
