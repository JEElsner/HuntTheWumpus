package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ForDebugging extends JPanel
{

	/**
	 * Create the panel.
	 */
	public ForDebugging()
	{
		setLayout(null);
		
		JLabel lblForDebugging = new JLabel("For Debugging");
		lblForDebugging.setBounds(185, 13, 105, 16);
		add(lblForDebugging);
		
		JButton button = new JButton("Enter High Score");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.highScoreNameEnter);
			}
		});
		button.setBounds(161, 127, 129, 25);
		add(button);
		
		JButton btnWin = new JButton("Win");
		btnWin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.WinScreen);
			}
		});
		btnWin.setBounds(172, 162, 97, 25);
		add(btnWin);
		
		JButton btnLose = new JButton("Lose");
		btnLose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.LoseScreen);
			}
		});
		btnLose.setBounds(172, 190, 97, 25);
		add(btnLose);
		
		JButton btnTriva = new JButton("Triva");
		btnTriva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.trivia);
			}
		});
		btnTriva.setBounds(172, 228, 97, 25);
		add(btnTriva);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				GUI.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(295, 228, 97, 25);
		add(btnMainMenu);
		
		JButton btnTestNotify = new JButton("Test Notify");
		btnTestNotify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTestNotify.setBounds(24, 243, 97, 25);
		add(btnTestNotify);

	}

}
