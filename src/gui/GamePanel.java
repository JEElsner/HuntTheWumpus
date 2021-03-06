package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Polygon;
import java.awt.Canvas;
import wumpus.MovementDirection;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel implements UpdateScreen
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1508960354336972743L;

	/**
	 * Create the panel.
	 */
	private String updateRequired;
	
	private MyCanvas canvas = new MyCanvas();
	
	private JButton moveUp;
	private JButton moveUpRight;
	private JButton moveUpLeft;
	private JButton moveDown;
	private JButton moveDownRight;
	private JButton moveDownLeft;
	
	private GUI gui;
	
	private JButton buyArrow;
	private JButton buySecret;
	
	private JList<String> myTriviaAnswers;
	private DefaultListModel<String> triviaAnswers;
	
	private JList<String> mySecrets;
	private DefaultListModel<String> obtainedSecrets;
	
	private ArrayList<JButton> shooting = new ArrayList<JButton>();
	private ArrayList<MovementDirection> shootingDirection = new ArrayList<MovementDirection>();
	private JLabel chooseDirection;
	private JButton optOne;
	private JButton optTwo;
	private JButton optThree;
	private JLabel miss;
	
	private JButton[] moving = new JButton[6];
	private JLabel yourRoom;
	private JLabel playerName;
	private JLabel coins;
	private JLabel arrows;
	private JLabel turnsTaken;
	private JLabel secretsObtained;
	private JLabel lblWarnings;
	private JTextPane warnings;
	private JLabel coinMax;
	private JLabel completedMessage;
	private JButton shootArrow;
	private JButton stopShooting;
		
	public GamePanel(GUI guiObject)
	{
		setBorder(new LineBorder(new Color(139, 69, 19), 4));
		setBackground(new Color(222, 184, 135));
		gui = guiObject;
		setLayout(null);
		this.setSize(1100, 1000);
		
		
//		JLabel lblTitle = new JLabel("Game Panel");
//		lblTitle.setBounds(468, 0, 68, 16);
//		add(lblTitle);
		
		JButton btnMainMenu = new JButton("Quit to Main Menu");
		btnMainMenu.setFont(new Font("Georgia", Font.BOLD, 16));
		btnMainMenu.setBackground(new Color(205, 133, 63));
		btnMainMenu.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(JOptionPane.showConfirmDialog(gui.mainWindow,
						"Are you sure you want to quit? All your progress will be lost.",
						"Confirm Quit",
						JOptionPane.YES_NO_OPTION) == 0)
				{
					gui.mainWindow.changeView(GUI.titleScreen);
					clear();
				}
			}
		});
		btnMainMenu.setBounds(37, 889, 175, 38);
		add(btnMainMenu);
		
		completedMessage = new JLabel("Message");
		completedMessage.setHorizontalAlignment(SwingConstants.CENTER);
		completedMessage.setOpaque(true);
		completedMessage.setForeground(Color.BLACK);
		completedMessage.setFont(new Font("Papyrus", Font.BOLD, 18));
		completedMessage.setBackground(new Color(180, 150, 110));
		completedMessage.setBounds(368, 268, 298, 38);
		add(completedMessage);
		completedMessage.setVisible(false);

		
		buyArrow = new JButton("Buy Arrow");
		buyArrow.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		buyArrow.setBackground(new Color(154, 205, 50));
		buyArrow.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		buyArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.PURCHASE_ARROW, true));
				clear();
			}
		});
		buyArrow.setBounds(915, 37, 111, 25);
		add(buyArrow);
		
		buySecret = new JButton("Buy Secret");
		buySecret.setBackground(new Color(154, 205, 50));
		buySecret.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		buySecret.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		buySecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.PURCHASE_SECRET, true));
				clear();
			}
		});
		buySecret.setBounds(915, 75, 111, 25);
		add(buySecret);
		
		shootArrow = new JButton("Shoot Arrow");
		shootArrow.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		shootArrow.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		shootArrow.setBackground(new Color(154, 205, 50));
		shootArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRequired = "shoot";
				clear();
				updateShooting();
			}
		});
		shootArrow.setBounds(915, 113, 111, 25);
		add(shootArrow);
		
		chooseDirection = new JLabel("Choose a direction to shoot");
		chooseDirection.setHorizontalAlignment(SwingConstants.CENTER);
		chooseDirection.setForeground(new Color(139, 69, 19));
		chooseDirection.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		chooseDirection.setBounds(815, 215, 261, 16);
		add(chooseDirection);
		
		optOne = new JButton("");
		optOne.setBackground(new Color(154, 205, 50));
		optOne.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		optOne.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		optOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(0)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optOne.setBounds(915, 244, 111, 25);
		optOne.setVisible(false);
		add(optOne);
		
		optTwo = new JButton("");
		optTwo.setBackground(new Color(154, 205, 50));
		optTwo.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		optTwo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		optTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(1)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optTwo.setBounds(915, 272, 111, 25);
		optTwo.setVisible(false);
		add(optTwo);
		
		optThree = new JButton("");
		optThree.setBackground(new Color(154, 205, 50));
		optThree.setBorder(new LineBorder(new Color(34, 139, 34), 3));
		optThree.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		optThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(2)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optThree.setBounds(915, 300, 111, 25);
		optThree.setVisible(false);
		add(optThree);

		chooseDirection.setVisible(false);
		
		
		yourRoom = new JLabel("Current Room: ");
		yourRoom.setFont(new Font("Papyrus", Font.BOLD, 18));
		yourRoom.setOpaque(true);
		yourRoom.setBackground(new Color(180, 150, 110));
		yourRoom.setForeground(Color.BLACK);
		yourRoom.setBounds(445, 357, 167, 38);
		add(yourRoom);
		
		//----------------------------------//
		//--------Movement Buttons----------//
		//----------------------------------//
		moveUp = new JButton("Up");
		moveUp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveUp.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveUp.setBackground(new Color(160, 195, 160));
		moveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP));
				clear();
			}
		});
		moveUp.setBounds(461, 84, 97, 30);
		add(moveUp);
		
		moveUpRight = new JButton("Up Right");
		moveUpRight.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveUpRight.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveUpRight.setBackground(new Color(160, 195, 160));
		moveUpRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_RIGHT));
				clear();
			}
		});
		moveUpRight.setBounds(708, 151, 97, 30);
		add(moveUpRight);
		
		moveUpLeft = new JButton("Up Left");
		moveUpLeft.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveUpLeft.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveUpLeft.setBackground(new Color(160, 195, 160));
		moveUpLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_LEFT));
				clear();
			}
		});
		moveUpLeft.setBounds(231, 151, 97, 30);
		add(moveUpLeft);
		
		moveDown = new JButton("Down");
		moveDown.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveDown.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveDown.setBackground(new Color(160, 195, 160));
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN));
				clear();
			}
		});
		moveDown.setBounds(461, 628, 97, 30);
		add(moveDown);
		
		moveDownRight = new JButton("Down Right");
		moveDownRight.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveDownRight.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveDownRight.setBackground(new Color(160, 195, 160));
		moveDownRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_RIGHT));
				clear();
			}
		});
		moveDownRight.setBounds(714, 557, 111, 30);
		add(moveDownRight);
		
		moveDownLeft = new JButton("Down Left");
		moveDownLeft.setBackground(new Color(160, 195, 160));
		moveDownLeft.setBorder(new LineBorder(new Color(160, 82, 45), 3));
		moveDownLeft.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		moveDownLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_LEFT));
				clear();
			}
		});
		moveDownLeft.setBounds(231, 557, 97, 30);
		add(moveDownLeft);
		
		moving[0] = moveUp;
		moving[1] = moveUpRight;
		moving[2] = moveUpLeft;
		moving[3] = moveDown;
		moving[4] = moveDownRight;
		moving[5] = moveDownLeft;
		
		canvas.setBackground(new Color(222, 184, 135));
		canvas.setBounds(206, 84, 622, 574);
		add(canvas);
		
		playerName = new JLabel();
		playerName.setFont(new Font("Papyrus", Font.BOLD, 18));
		playerName.setBounds(12, 17, 79, 30);
		add(playerName);
		
		coins = new JLabel("Coins:");
		coins.setFont(new Font("Papyrus", Font.BOLD, 18));
		coins.setBounds(440, 13, 111, 21);
		add(coins);
		
		arrows = new JLabel("Arrows:");
		arrows.setFont(new Font("Papyrus", Font.BOLD, 18));
		arrows.setBounds(440, 37, 111, 34);
		add(arrows);
		
		turnsTaken = new JLabel("Turns Taken:");
		turnsTaken.setFont(new Font("Papyrus", Font.BOLD, 18));
		turnsTaken.setBounds(12, 55, 171, 46);
		add(turnsTaken);
		
		secretsObtained = new JLabel("Trivia Answers:");
		secretsObtained.setFont(new Font("Papyrus", Font.BOLD, 18));
		secretsObtained.setBounds(24, 684, 159, 24);
		add(secretsObtained);
		
		lblWarnings = new JLabel("WARNINGS");
		lblWarnings.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblWarnings.setBounds(12, 114, 158, 41);
		add(lblWarnings);
		
		warnings = new JTextPane();
		warnings.setBorder(new LineBorder(new Color(160, 82, 45), 4));
		warnings.setBackground(new Color(238, 232, 170));
		warnings.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		warnings.setEditable(false);
		warnings.setBounds(12, 160, 187, 146);
		add(warnings);
		
		miss = new JLabel("Oh no! You missed!");
		miss.setHorizontalAlignment(SwingConstants.CENTER);
		miss.setForeground(new Color(139, 69, 19));
		miss.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		miss.setBounds(877, 151, 187, 21);
		miss.setVisible(false);
		add(miss);
		
		coinMax = new JLabel("Max coins reached!");
		coinMax.setForeground(new Color(139, 69, 19));
		coinMax.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		coinMax.setBounds(563, 11, 187, 25);
		coinMax.setVisible(false);
		add(coinMax);
		
		MyCellRenderer cellRenderer = new MyCellRenderer(550);
		
		myTriviaAnswers = new JList<String>();
		myTriviaAnswers.setFont(new Font("Georgia", Font.BOLD, 16));
		myTriviaAnswers.setSelectionBackground(new Color(210, 180, 140));
		myTriviaAnswers.setBorder(new LineBorder(new Color(160, 82, 45), 4));
		myTriviaAnswers.setBackground(new Color(180, 150, 110));
		//myTriviaAnswers.setModel(triviaAnswers);
		myTriviaAnswers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myTriviaAnswers.setCellRenderer(cellRenderer);
		myTriviaAnswers.setBounds(230, 780, 604, 60);
		//add(myTriviaAnswers);
		
		JScrollPane tAnswers = new JScrollPane(myTriviaAnswers);
		tAnswers.setBounds(180, 680, 774, 80);
		add(tAnswers);
		
		JLabel lblSecretsObtained = new JLabel("Secrets:");
		lblSecretsObtained.setFont(new Font("Papyrus", Font.BOLD, 18));
		lblSecretsObtained.setBounds(80, 785, 97, 25);
		add(lblSecretsObtained);
		
		mySecrets = new JList<String>();
		mySecrets.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		mySecrets.setBorder(new LineBorder(new Color(160, 82, 45), 4));
		mySecrets.setSelectionBackground(new Color(210, 180, 140));
		mySecrets.setBackground(new Color(180, 150, 110));
		//mySecrets.setModel(obtainedSecrets);
		mySecrets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mySecrets.setBounds(230, 780, 604, 60);
		//add(mySecrets);
		
		JScrollPane mySec = new JScrollPane(mySecrets);
		mySec.setBounds(180, 780, 774, 80);
		add(mySec);
		
		stopShooting = new JButton("Stop Shooting");
		stopShooting.setBackground(new Color(154, 205, 50));
		stopShooting.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		stopShooting.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		stopShooting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shoot();
			}
		});
		stopShooting.setBounds(889, 357, 158, 25);
		add(stopShooting);
		stopShooting.setVisible(false);
		
		//-----------Making Them Rotate---------------//
		
//		Canvas c = new Canvas();
//		c.setBackground(Color.LIGHT_GRAY);
//		c.setBounds(176, 84, 622, 574);
//		add(c);
		

				
		updatePanel("Refresh");

	}
	
	//------------------------------------------//
	//-----This is where the methods start------//
	//------------------------------------------//
	
	private class MyCanvas extends Canvas
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2733423041629501989L;

		@Override
		public void paint(Graphics g)
		{
			g.setColor(new Color(180, 150, 110));

		    Polygon h = new Polygon();
		    
		    int xPos = 311;
		    int yPos = 287;
		    int side = 292;
			for (int i = 0; i < 6; i++)
			{
				h.addPoint((int) (xPos + side * Math.cos(i * Math.PI / 3)),
						  (int) (yPos + side * Math.sin(i * Math.PI / 3)));
			}
			
			g.fillPolygon(h);
			g.drawPolygon(h);
		}
	}
	
	public void shoot()
	{
		chooseDirection.setVisible(false);
		
		for(JButton b : shooting)
		{
			b.setVisible(false);
		}
		
		while(shooting.size() > 0)
		{
			int i = 0;
			shooting.remove(i);
		}
		
		while(shootingDirection.size() > 0)
		{
			int i = 0;
			shootingDirection.remove(i);
		}
		
		for(JButton b: moving)
		{
			b.setEnabled(true);
		}
		
		buyArrow.setEnabled(true);
		buySecret.setEnabled(true);
		shootArrow.setEnabled(true);
		
		stopShooting.setVisible(false);
		
	}
	
	public void miss()
	{
		miss.setVisible(true);
	}
	
	public void clear()
	{
		miss.setVisible(false);
		completedMessage.setVisible(false);
	}
	
	public void maxCoins()
	{
		coinMax.setVisible(true);
	}
	
	public void completed()
	{
		completedMessage.setVisible(true);
	}
	
	public void setCompletedAction(String action)
	{
		if(action.equals("pits"))
		{
			completedMessage.setText("You escaped the pit!");
		}
		
		if(action.equals("wumpus"))
		{
			completedMessage.setText("You escaped the wumpus!");
		}
		
		if(action.equals("arrows"))
		{
			completedMessage.setText("You got more arrows!");
		}
		
		if(action.equals("arrow fail"))
		{
			completedMessage.setText("You failed to get more arrows!");
		}
		
		if(action.equals("secret"))
		{
			completedMessage.setText("You got a secret!");
		}
		
		if(action.equals("secret fail"))
		{
			completedMessage.setText("You failed to get a secret!");
		}
		
		if(action.equals("bats"))
		{
			completedMessage.setText("Bats moved you elsewhere!");
		}
	}
	
	public void updateShooting()
	{
		MovementDirection[] md = gui.getDoors();
		for(MovementDirection mds : md)
		{
			shootingDirection.add(mds);
		}
		
		for(int i = 0; i < md.length; i++)
		{
			if(i == 0)
				shooting.add(optOne);
			if(i == 1)
				shooting.add(optTwo);
			if(i == 2)
				shooting.add(optThree);
		}
		
		
		for(int i = 0; i < shootingDirection.size(); i++)
		{
				
				if(md[i].equals(MovementDirection.UP))
				{
					shooting.get(i).setText("Up");
				}
				
				if(md[i].equals(MovementDirection.UP_RIGHT))
				{
					shooting.get(i).setText("Up Right");
				}
				
				if(md[i].equals(MovementDirection.UP_LEFT))
				{
					shooting.get(i).setText("Up Left");
				}
				
				if(md[i].equals(MovementDirection.DOWN))
				{
					shooting.get(i).setText("Down");
				}
				
				if(md[i].equals(MovementDirection.DOWN_RIGHT))
				{
					shooting.get(i).setText("Down Right");
				}
				
				if(md[i].equals(MovementDirection.DOWN_LEFT))
				{
					shooting.get(i).setText("Down Left");
				}
					
		}
		
		chooseDirection.setVisible(true);
		
		for(JButton b : shooting)
		{
			b.setVisible(true);
		}
		
		stopShooting.setVisible(true);
		
		for(JButton b: moving)
		{
			b.setEnabled(false);
		}
		
		buyArrow.setEnabled(false);
		buySecret.setEnabled(false);
		shootArrow.setEnabled(false);
		
	}

	
	public void updatePanel(String update)
	{
		
		playerName.setText(gui.getName());
		
		triviaAnswers = new DefaultListModel<String>();
		for(String s : gui.getTriviaAnswers())
		{
			triviaAnswers.addElement(s);
		}
		
		myTriviaAnswers.setModel(triviaAnswers);
		
		
		obtainedSecrets = new DefaultListModel<String>();
		for(String s : gui.getSecrets())
		{
			obtainedSecrets.addElement(s);
		}
		mySecrets.setModel(obtainedSecrets);
		
		
		yourRoom.setText("Current Room: " + gui.getCurrentRoom());
		turnsTaken.setText("Turns Taken: " + gui.getTurns());
		coins.setText("Coins: " + gui.getCoins());
		arrows.setText("Arrows: " + gui.getArrows());
		
		buyArrow.setEnabled((gui.getCoins() >= 2));
		buySecret.setEnabled((gui.getCoins() >= 2));
		shootArrow.setEnabled((gui.getArrows() > 0));
		
		warnings.setText(gui.displayBWarn() +  gui.displayPWarn() + gui.displayWWarn());
		
		for(JButton b : moving)
		{
			b.setVisible(false);
		}
		
		for(MovementDirection md : gui.getDoors())
		{
			if(md == null)
				continue;
			
			if(md.equals(MovementDirection.UP))
			{
				moving[0].setVisible(true);
			}
			
			if(md.equals(MovementDirection.UP_RIGHT))
			{
				moving[1].setVisible(true);
			}
			
			if(md.equals(MovementDirection.UP_LEFT))
			{
				moving[2].setVisible(true);
			}
			
			if(md.equals(MovementDirection.DOWN))
			{
				moving[3].setVisible(true);
			}
			
			if(md.equals(MovementDirection.DOWN_RIGHT))
			{
				moving[4].setVisible(true);
			}
			
			if(md.equals(MovementDirection.DOWN_LEFT))
			{
				moving[5].setVisible(true);
			}
				
		}
	
		
	}
	
	private class MyCellRenderer extends DefaultListCellRenderer {
		   public static final String HTML_1 = "<html><body style='width: ";
		   public static final String HTML_2 = "px'>";
		   public static final String HTML_3 = "</html>";
		   private int width;

		   public MyCellRenderer(int width) {
		      this.width = width;
		   }

		   @Override
		   public Component getListCellRendererComponent(JList list, Object value,
		         int index, boolean isSelected, boolean cellHasFocus) {
		      String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString()
		            + HTML_3;
		      return super.getListCellRendererComponent(list, text, index, isSelected,
		            cellHasFocus);
		   }

		}
}
