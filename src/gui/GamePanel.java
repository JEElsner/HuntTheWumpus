package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;

public class GamePanel extends JPanel implements UpdateScreen
{
	/**
	 * Create the panel.
	 */
	private String updateRequired;
	private Choice choice;
	
	public GamePanel(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Game Panel");
		lblTitle.setBounds(191, 5, 68, 16);
		add(lblTitle);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(33, 242, 97, 25);
		add(btnMainMenu);
		
		choice = new Choice();
		choice.setBounds(318, 10, 122, 22);
		choice.add("Action");
		choice.add("Move");
		choice.add("Buy Arrow");
		choice.add("Buy Secret");
		choice.add("Shoot Arrow");
		add(choice);
		
		JButton btnSubmitAction = new JButton("Submit Action");
		btnSubmitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choice.getSelectedItem().equals("Buy Secret") || choice.getSelectedItem().equals("Buy Arrow"))
				{
					guiObject.mainWindow.changeView(GUI.trivia);
					updatePanel(updateRequired);
				}
				
				if(choice.getSelectedItem().equals("Move"))
				{
					updateRequired = "move";
					updatePanel(updateRequired);
				}
				
				if(choice.getSelectedItem().equals("Shoot Arrow"))
				{
					updateRequired = "shoot";
					updatePanel(updateRequired);
				}
					
			}
		});
		btnSubmitAction.setBounds(319, 70, 121, 25);
		add(btnSubmitAction);

	}
	
	public void updatePanel(String update)
	{
		System.out.println(update);
		choice.select(0);
	}
}
