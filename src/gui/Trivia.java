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
	// REVIEW, Consider adding known secrets to this screen
	
	private JTextField answers;
	private GUI gui;
	
	private JLabel question;
	private JLabel numNeeded;
	private JLabel answeredStats;
	private JLabel encounter;
	
	/**
	 * Create the panel.
	 */
	public Trivia(GUI guiObject)
	{		
		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 800);
		
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
		
		question = new JLabel("Questions will go here");
		question.setHorizontalAlignment(SwingConstants.CENTER);
		question.setBounds(12, 30, 191, 164);
		panel.add(question);
		question.setBackground(Color.WHITE);
		
		answers = new JTextField();
		answers.setBounds(206, 13, 244, 170);
		add(answers);
		answers.setColumns(10);
		
		encounter = new JLabel("You are attempting to *****");
		encounter.setBounds(4, 189, 273, 25);
		add(encounter);
		
		JButton submit = new JButton("Submit Answer");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.setAnswer(submit.getText());
				gui.notifyControl(new Update(UpdateType.GIVE_ANSWER, true, gui.getAnswer()));
				updatePanel("Submitted");
			}
		});
		submit.setBounds(319, 196, 131, 25);
		add(submit);
		
		numNeeded = new JLabel("You must answer **** correctly");
		numNeeded.setBounds(4, 212, 273, 25);
		add(numNeeded);
		
		answeredStats = new JLabel("Insert Current Stats on questions right");
		answeredStats.setBounds(4, 250, 255, 16);
		add(answeredStats);

	}
	
	public void updatePanel(String update)
	{
		question.setText(gui.getQuestion());
		if(update.equals("pits"));
		{
			encounter.setText("You have to escape the pit!");
			numNeeded.setText("You must answer 2 out of 3 questions correctly to escape!");
			//TODO print out / handle how many questions are answered and how many are needed
		}
		
		if(update.equals("wumpus"));
		{
			encounter.setText("You have to escape the Wumpus!");
			numNeeded.setText("You must answer 3 out of 5 questions correctly");
			//TODO print out / handle how many questions are answered and how many are needed
		}
		
		if(update.equals("arrows"));
		{
			encounter.setText("You are trying to buy some more arrows");
			numNeeded.setText("You must answer 2 out of 3 questions correctly");
			//TODO print out / handle how many questions are answered and how many are needed
		}
		
		if(update.equals("secret"));
		{
			encounter.setText("You are trying to buy a secret");
			numNeeded.setText("You must answer 2 out of 3 questions correctly");
			//TODO print out / handle how many questions are answered and how many are needed
		}
	}
}
