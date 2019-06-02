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

public class Trivia extends JPanel implements UpdateScreen
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
	public Trivia(GUI guiObject)
	{		
		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 900);
		
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
		encounter.setBounds(73, 395, 273, 25);
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
		answers.setBounds(409, 55, 331, 272);
		add(answers);
		answers.setColumns(10);
		
		numNeeded = new JLabel("You must answer **** correctly");
		numNeeded.setBounds(73, 427, 306, 25);
		add(numNeeded);
		
		answeredStats = new JLabel("Insert Current Stats on questions right");
		answeredStats.setBounds(73, 465, 255, 16);
		add(answeredStats);
		
		correctMessage = new JLabel("Congrats boi");
		correctMessage.setBounds(73, 366, 331, 16);
		add(correctMessage);
		correctMessage.setVisible(false);
		
		warnings = new JLabel("Warnings");
		warnings.setBounds(73, 494, 244, 16);
		add(warnings);
		
		questions = new JTextArea();
		questions.setBounds(80, 55, 317, 272);
		add(questions);
		questions.setColumns(10);
		questions.setLineWrap(true);
		questions.setWrapStyleWord(true);
		
		coinsLeft = new JLabel("Coins Remaining:");
		coinsLeft.setBounds(73, 340, 157, 16);
		add(coinsLeft);
		//questions.setEditable(false);


	}
	
	public void clear()
	{
		correctMessage.setVisible(false);
	}
	
	public void updatePanel(String update)
	{
		answers.setText("");
		
		for(int i = 0; i < 4; i++)
		{
			stats[i] = gui.getTriviaStats()[i];
		}
		
		questions.setText(gui.getQuestion());
		
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
