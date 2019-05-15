package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HighScore extends JPanel implements UpdateScreen
{
	private JTextField textField;
	Scanner scan = new Scanner(System.in);
	private GUI gui;
	/**
	 * Create the panel.
	 */
	public HighScore(GUI guiObject)
	{
		gui = guiObject;
		setLayout(null);
		
		JLabel lblHighScore = new JLabel("High Score");
		lblHighScore.setBounds(173, 13, 67, 16);
		add(lblHighScore);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gui.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(157, 127, 97, 25);
		add(btnMainMenu);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ev) {
				if(ev.getKeyCode() == KeyEvent.VK_ENTER)
				{
					gui.setName(textField.getText());
				}
			}
		});
		textField.setBounds(113, 178, 116, 22);
		add(textField);
		textField.setColumns(10);

	}
	
	public void updatePanel(String update)
	{
		
	}
}
