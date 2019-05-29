package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Canvas;
import wumpus.MovementDirection;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

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
	
	private JList<String> mySecrets;
	private DefaultListModel<String> secret = new DefaultListModel<String>();
	
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
	private JLabel inventory;
	private JLabel coins;
	private JLabel arrows;
	private JLabel turnsTaken;
	private JLabel secretsObtained;
	private JLabel lblWarnings;
	private JTextPane warnings;
	private JLabel coinMax;
		
	public GamePanel(GUI guiObject)
	{
		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 800);
		
		
		JLabel lblTitle = new JLabel("Game Panel");
		lblTitle.setBounds(468, 0, 68, 16);
		add(lblTitle);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				gui.mainWindow.changeView(GUI.titleScreen);
				
			}
		});
		btnMainMenu.setBounds(12, 481, 97, 25);
		add(btnMainMenu);
		
		buyArrow = new JButton("Buy Arrow");
		buyArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.PURCHASE_ARROW, true));
				gui.mainWindow.triviaScreen.updatePanel("arrows");
				gui.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buyArrow.setBounds(839, 46, 111, 25);
		add(buyArrow);
		
		buySecret = new JButton("Buy Secret");
		buySecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.PURCHASE_SECRET, true));
				gui.mainWindow.triviaScreen.updatePanel("secret");
				gui.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buySecret.setBounds(839, 84, 111, 25);
		add(buySecret);
		
		JButton shootArrow = new JButton("Shoot Arrow");
		shootArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRequired = "shoot";
				clear();
				updateShooting();
			}
		});
		shootArrow.setBounds(839, 122, 111, 25);
		add(shootArrow);
		
		chooseDirection = new JLabel("Choose a direction to shoot");
		chooseDirection.setBounds(817, 224, 158, 16);
		add(chooseDirection);
		
		optOne = new JButton("");
		optOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(0)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optOne.setBounds(839, 253, 111, 25);
		optOne.setVisible(false);
		add(optOne);
		
		optTwo = new JButton("");
		optTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(1)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optTwo.setBounds(839, 281, 111, 25);
		optTwo.setVisible(false);
		add(optTwo);
		
		optThree = new JButton("");
		optThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.notifyControl(new Update(UpdateType.SHOOT_ARROW, true, shootingDirection.get(2)));
				shoot();
				updatePanel("Shooting");
			}
		});
		optThree.setBounds(839, 309, 111, 25);
		optThree.setVisible(false);
		add(optThree);

		chooseDirection.setVisible(false);
		
		
		yourRoom = new JLabel("Current Room: ");
		yourRoom.setBackground(Color.GRAY);
		yourRoom.setForeground(Color.BLACK);
		yourRoom.setBounds(431, 380, 133, 16);
		add(yourRoom);
		
		//----------------------------------//
		//--------Movement Buttons----------//
		//----------------------------------//
		moveUp = new JButton("Up");
		moveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP));
			}
		});
		moveUp.setBounds(444, 122, 97, 25);
		add(moveUp);
		
		moveUpRight = new JButton("Up Right");
		moveUpRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_RIGHT));
			}
		});
		moveUpRight.setBounds(682, 202, 97, 25);
		add(moveUpRight);
		
		moveUpLeft = new JButton("Up Left");
		moveUpLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_LEFT));
			}
		});
		moveUpLeft.setBounds(201, 202, 97, 25);
		add(moveUpLeft);
		
		moveDown = new JButton("Down");
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN));
			}
		});
		moveDown.setBounds(444, 671, 97, 25);
		add(moveDown);
		
		moveDownRight = new JButton("Down Right");
		moveDownRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_RIGHT));
			}
		});
		moveDownRight.setBounds(669, 616, 110, 25);
		add(moveDownRight);
		
		moveDownLeft = new JButton("Down Left");
		moveDownLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_LEFT));
			}
		});
		moveDownLeft.setBounds(201, 616, 97, 25);
		add(moveDownLeft);
		
		moving[0] = moveUp;
		moving[1] = moveUpRight;
		moving[2] = moveUpLeft;
		moving[3] = moveDown;
		moving[4] = moveDownRight;
		moving[5] = moveDownLeft;
		
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setBounds(177, 122, 622, 574);
		add(canvas);
		
		playerName = new JLabel();
		playerName.setBounds(12, 17, 79, 16);
		add(playerName);
		
		inventory = new JLabel("Inventory");
		inventory.setBounds(213, 17, 56, 16);
		add(inventory);
		
		coins = new JLabel("Coins:");
		coins.setBounds(219, 46, 79, 16);
		add(coins);
		
		arrows = new JLabel("Arrows:");
		arrows.setBounds(219, 70, 79, 16);
		add(arrows);
		
		turnsTaken = new JLabel("Turns Taken:");
		turnsTaken.setBounds(12, 55, 97, 16);
		add(turnsTaken);
		
		secretsObtained = new JLabel("Secrets Obtained:");
		secretsObtained.setBounds(12, 126, 111, 16);
		add(secretsObtained);
		
		lblWarnings = new JLabel("WARNINGS");
		lblWarnings.setBounds(825, 497, 68, 16);
		add(lblWarnings);
		
		warnings = new JTextPane();
		warnings.setEditable(false);
		warnings.setBounds(817, 526, 158, 80);
		add(warnings);
		
		miss = new JLabel("Oh no! You missed!");
		miss.setBounds(839, 160, 127, 16);
		miss.setVisible(false);
		add(miss);
		
		coinMax = new JLabel("Max coins reached!");
		coinMax.setBounds(299, 46, 111, 16);
		coinMax.setVisible(false);
		add(coinMax);
		
		mySecrets = new JList<String>();
		mySecrets.setModel(secret);
		mySecrets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mySecrets.setBounds(12, 155, 138, 223);
		add(mySecrets);
		
		//-----------Making Them Rotate---------------//
		
//		Canvas c = new Canvas();
//		c.setBackground(Color.LIGHT_GRAY);
//		c.setBounds(177, 122, 622, 574);
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
			g.setColor(Color.BLACK);

		    Polygon h = new Polygon();
		    
		    int xPos = 311;
		    int yPos = 287;
		    int side = 292;
			for (int i = 0; i < 6; i++)
			{
				h.addPoint((int) (xPos + side * Math.cos(i * Math.PI / 3)),
						  (int) (yPos + side * Math.sin(i * Math.PI / 3)));
			}
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
		
		//TODO Add handler if it hits
	}
	
	public void miss()
	{
		miss.setVisible(true);
	}
	
	public void clear()
	{
		miss.setVisible(false);
	}
	
	// FIXME This is utterly borked when there are fewer than three doors from a room
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
		
	}

	
	public void updatePanel(String update)
	{
		
		playerName.setText(gui.getName());
		
		secret = new DefaultListModel<String>();
		for(String s : gui.getSecrets())
		{
			secret.addElement(s);
		}
		
		mySecrets.setModel(secret);
		
		yourRoom.setText("Current Room: " + gui.getCurrentRoom());
		turnsTaken.setText("Turns Taken: " + gui.getTurns());
		coins.setText("Coins: " + gui.getCoins());
		arrows.setText("Arrows: " + gui.getArrows());
		
		if(gui.getCoins() < 100)
		{
			coinMax.setVisible(false);
		}
		
		if(gui.getCoins() == 100)
		{
			coinMax.setVisible(true);
		}
		
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
}
