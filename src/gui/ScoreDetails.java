package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ScoreDetails extends JFrame
{

	private JPanel contentPane;
	
	private JLabel nameScore;
	private JLabel caveNo;
	private JLabel turnsNo;
	private JLabel coinsNo;
	private JLabel arrowsNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ScoreDetails frame = new ScoreDetails();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScoreDetails()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameScore = new JLabel("New label");
		nameScore.setBounds(103, 36, 122, 16);
		contentPane.add(nameScore);
		
		caveNo = new JLabel("New label");
		caveNo.setBounds(103, 107, 141, 22);
		contentPane.add(caveNo);
		
		turnsNo = new JLabel("New label");
		turnsNo.setBounds(103, 147, 122, 16);
		contentPane.add(turnsNo);
		
		coinsNo = new JLabel("New label");
		coinsNo.setBounds(103, 176, 122, 22);
		contentPane.add(coinsNo);
		
		arrowsNo = new JLabel("New label");
		arrowsNo.setBounds(103, 205, 122, 22);
		contentPane.add(arrowsNo);
		
		this.setSize(400, 400);
		
		
	}
	
	public void settingScores(List<String> nameAndScore, int[] stats)
	{
		nameScore.setText(nameAndScore.get(0));
		turnsNo.setText("Turns Taken: " + stats[1]);
		coinsNo.setText("Coins Gained: " + stats[2]);
		arrowsNo.setText("Arrows Gained: " + stats[3]);
		
		if(stats[0] == 6)
		{
			caveNo.setText("Cave: Random");
		}
		else
		{
			caveNo.setText("Cave: " + stats[0]);
		}
		
		
	}
}








