package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Trivia extends JPanel implements UpdateScreen
{
	private JTextField answers;
	private GUI gui;
	/**
	 * Create the panel.
	 */
	public Trivia(GUI guiObject)
	{
		setLayout(null);
		
		gui = guiObject;
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(341, 262, 97, 25);
		add(btnMainMenu);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, -18, 209, 201);
		add(panel);
		panel.setLayout(null);
		
		JLabel question = new JLabel("Questions will go here");
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setBounds(12, 30, 191, 164);
		panel.add(question);
		question.setBackground(Color.WHITE);
		
		answers = new JTextField();
		answers.setBounds(206, 13, 244, 170);
		add(answers);
		answers.setColumns(10);
		
		JLabel questionStats = new JLabel("You are attempting to *****");
		questionStats.setBounds(4, 189, 235, 25);
		add(questionStats);
		
		JButton submit = new JButton("Submit Answer");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.setAnswer(submit.getText());
			}
		});
		submit.setBounds(319, 196, 119, 25);
		add(submit);
		
		JLabel lblYouMustAnswer = new JLabel("You must answer **** correctly");
		lblYouMustAnswer.setBounds(4, 212, 235, 25);
		add(lblYouMustAnswer);
		
		JLabel lblInsertCurrentStats = new JLabel("Insert Current Stats on questions right");
		lblInsertCurrentStats.setBounds(4, 250, 235, 16);
		add(lblInsertCurrentStats);

	}
	
	public void updatePanel(String update)
	{
		
	}
}
