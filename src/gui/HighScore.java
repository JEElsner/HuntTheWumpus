package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class HighScore extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	public String name;
	Scanner scan = new Scanner(System.in);

	/**
	 * Create the panel.
	 */
	public HighScore(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblHighScore = new JLabel("High Score");
		lblHighScore.setBounds(173, 13, 67, 16);
		add(lblHighScore);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(157, 127, 97, 25);
		add(btnMainMenu);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				name = textField.getText();
			}
		});
		textField.setBounds(12, 200, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 200, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);

	}
}
