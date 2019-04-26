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

public class Trivia extends JPanel
{
	private JTextField txtEnterAnswersHere;
	/**
	 * Create the panel.
	 */
	public Trivia(GUI guiObject)
	{
		setLayout(null);
		
		JLabel lblTrivia = new JLabel("Trivia");
		lblTrivia.setBounds(364, 241, 56, 16);
		add(lblTrivia);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(323, 262, 97, 25);
		add(btnMainMenu);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(-6, -18, 209, 201);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblQuestionsWillGo = new JLabel("Questions will go here");
		lblQuestionsWillGo.setBounds(6, 18, 197, 176);
		panel.add(lblQuestionsWillGo);
		lblQuestionsWillGo.setBackground(Color.WHITE);
		
		txtEnterAnswersHere = new JTextField();
		txtEnterAnswersHere.setBounds(206, 42, 244, 141);
		add(txtEnterAnswersHere);
		txtEnterAnswersHere.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Stats on what questions are needed");
		lblNewLabel.setBounds(4, 189, 235, 98);
		add(lblNewLabel);
		
		JLabel lblTrivaAnswersHere = new JLabel("Triva Answers Here");
		lblTrivaAnswersHere.setBounds(215, 13, 136, 16);
		add(lblTrivaAnswersHere);

	}
}
