package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

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
		setBackground(new Color(222, 184, 135));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameScore = new JLabel("New label");
		nameScore.setHorizontalAlignment(SwingConstants.CENTER);
		nameScore.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		nameScore.setBounds(91, 49, 196, 32);
		contentPane.add(nameScore);
		
		caveNo = new JLabel("New label");
		caveNo.setHorizontalAlignment(SwingConstants.CENTER);
		caveNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		caveNo.setBounds(91, 126, 196, 32);
		contentPane.add(caveNo);
		
		turnsNo = new JLabel("New label");
		turnsNo.setHorizontalAlignment(SwingConstants.CENTER);
		turnsNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		turnsNo.setBounds(91, 171, 196, 32);
		contentPane.add(turnsNo);
		
		coinsNo = new JLabel("New label");
		coinsNo.setHorizontalAlignment(SwingConstants.CENTER);
		coinsNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		coinsNo.setBounds(91, 216, 196, 32);
		contentPane.add(coinsNo);
		
		arrowsNo = new JLabel("New label");
		arrowsNo.setHorizontalAlignment(SwingConstants.CENTER);
		arrowsNo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		arrowsNo.setBounds(91, 261, 196, 42);
		contentPane.add(arrowsNo);
		
		this.setSize(400, 400);
		
		
	}
	
	public void settingStats(int[] stats)
	{
		turnsNo.setText("Turns Taken: " + stats[1]);
		coinsNo.setText("Coins Remaining: " + stats[2]);
		arrowsNo.setText("Arrows Remaining: " + stats[3]);
		
		if(stats[0] == 6)
		{
			caveNo.setText("Cave: Random");
		}
		else
		{
			caveNo.setText("Cave: " + stats[0]);
		}
	}
	
	public void settingScores(String nameAndScore)
	{
		nameScore.setText(nameAndScore);		
		
	}
}








