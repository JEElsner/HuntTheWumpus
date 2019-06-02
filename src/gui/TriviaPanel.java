package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Component;

public class TriviaPanel extends JPanel implements UpdateScreen
{
	// REVIEW, Consider adding known secrets to this screen
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4776622282136822337L;
	private JTextField answers;
	private GUI gui;
	private JLabel numNeeded;
	private JLabel answeredStats;
	private JLabel encounter;
	private JLabel correctMessage;
	private int[] stats = new int[4];
	private JLabel warnings;
	private JTextArea questions;
	private JLabel coinsLeft;
	
	/**
	 * Create the panel.
	 */
	public TriviaPanel(GUI guiObject)
	{
		setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setBackground(new Color(222, 184, 135));		
		gui = guiObject;
		setLayout(null);
		this.setSize(1100, 1000);
		
//		JButton btnMainMenu = new JButton("Main Menu");
//		btnMainMenu.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) 
//			{
//				guiObject.mainWindow.changeView(GUI.titleScreen);
//			}
//		});
//		btnMainMenu.setBounds(686, 465, 97, 25);
//		add(btnMainMenu);
		

		
		encounter = new JLabel("You are attempting to *****");
		encounter.setFont(new Font("Papyrus", Font.BOLD, 20));
		encounter.setBounds(172, 581, 517, 44);
		add(encounter);
		
//		JButton submit = new JButton("Submit Answer");
//		submit.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				gui.setAnswer(answers.getText());
//				gui.notifyControl(new Update(UpdateType.GIVE_ANSWER, true, gui.getAnswer()));
//				updatePanel("Submitted");
//			}
//		});
//		submit.setBounds(652, 406, 131, 25);
//		add(submit);
		
		answers = new JTextField();
		answers.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		answers.setBackground(new Color(245, 222, 179));
		answers.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		answers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					gui.setAnswer(answers.getText());
					gui.notifyControl(new Update(UpdateType.GIVE_ANSWER, true, gui.getAnswer()));
					updatePanel("Submitted");
				}
			}
		});
		answers.setBounds(544, 107, 360, 320);
		add(answers);
		answers.setColumns(10);
		
		numNeeded = new JLabel("You must answer **** correctly");
		numNeeded.setFont(new Font("Papyrus", Font.BOLD, 20));
		numNeeded.setBounds(172, 624, 671, 47);
		add(numNeeded);
		
		answeredStats = new JLabel("Insert Current Stats on questions right");
		answeredStats.setFont(new Font("Papyrus", Font.BOLD, 20));
		answeredStats.setBounds(172, 668, 480, 39);
		add(answeredStats);
		
		correctMessage = new JLabel("Congrats boi");
		correctMessage.setFont(new Font("Papyrus", Font.BOLD, 20));
		correctMessage.setBounds(172, 535, 656, 47);
		add(correctMessage);
		correctMessage.setVisible(false);
		
		warnings = new JLabel("Warnings");
		warnings.setFont(new Font("Papyrus", Font.BOLD, 20));
		warnings.setBounds(172, 706, 567, 44);
		add(warnings);
		
		questions = new JTextArea();
		questions.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		questions.setBackground(new Color(245, 222, 179));
		questions.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		questions.setBounds(172, 107, 360, 320);
		add(questions);
		questions.setColumns(10);
		questions.setLineWrap(true);
		questions.setWrapStyleWord(true);
		
		coinsLeft = new JLabel("Coins Remaining:");
		coinsLeft.setFont(new Font("Papyrus", Font.BOLD, 20));
		coinsLeft.setBounds(172, 494, 244, 39);
		add(coinsLeft);
		
		JLabel lblQuestions = new JLabel("Questions");
		lblQuestions.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestions.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblQuestions.setBounds(223, 54, 244, 39);
		add(lblQuestions);
		
		JLabel lblAnswers = new JLabel("Answers");
		lblAnswers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswers.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblAnswers.setBounds(599, 55, 244, 39);
		add(lblAnswers);
		
		JLabel lblPressEnterTo = new JLabel("Press enter to submit");
		lblPressEnterTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPressEnterTo.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblPressEnterTo.setBounds(599, 440, 244, 39);
		add(lblPressEnterTo);
		questions.setEditable(false);


	}
	
	public void clear()
	{
		correctMessage.setVisible(false);
	}
	
	public void updatePanel(String update)
	{
		questions.setEditable(true);
		answers.setText("");
		
		for(int i = 0; i < 4; i++)
		{
			stats[i] = gui.getTriviaStats()[i];
		}
		
		questions.setText(gui.getQuestion());
		questions.setEditable(false);
		
		coinsLeft.setText("Coins Remaining: " + gui.getCoins());
		
		if(stats[2] > 0)
		{
			if(stats[0] == 0)
			{
				correctMessage.setText("Too bad you got it wrong, here's the next one!");
				correctMessage.setVisible(true);
			}
			
			if(stats[0] == 1)
			{
				correctMessage.setText("Congratulations you got it right! On to the next one!");
				correctMessage.setVisible(true);
			}
		}
		
		if(update.equals("pits"))
		{
			encounter.setText("You have to escape the pit!");
			numNeeded.setText("You must answer 2 out of 3 questions correctly to escape!");
			gui.mainWindow.gameplayScreen.setCompletedAction("pits");
		}
		
		if(update.equals("wumpus"))
		{
			encounter.setText("You have to escape the Wumpus!");
			numNeeded.setText("You must answer 3 out of 5 questions correctly");
			gui.mainWindow.gameplayScreen.setCompletedAction("wumpus");
			
		}
		
		if(update.equals("arrows"))
		{
			encounter.setText("You are trying to buy some more arrows");
			numNeeded.setText("You must answer 2 out of 3 questions correctly");
			gui.mainWindow.gameplayScreen.setCompletedAction("arrows");
			
		}
		
		if(update.equals("secret"))
		{
			encounter.setText("You are trying to buy a secret");
			numNeeded.setText("You must answer 2 out of 3 questions correctly");
			gui.mainWindow.gameplayScreen.setCompletedAction("secret");
			
		}
		
		answeredStats.setText("Questions correct: " + stats[1] + "        Questions asked: " + stats[2]);
		warnings.setText("Warning! You have " + stats[3] + " questions remaining!");
	}
}
