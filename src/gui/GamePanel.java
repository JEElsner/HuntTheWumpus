package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Canvas;
import wumpus.MovementDirection;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class GamePanel extends JPanel implements UpdateScreen
{
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
	
	private JButton[] moving = new JButton[6];
	private JLabel playerName;
	private JLabel lblInventory;
	private JLabel lblCoins;
	private JLabel lblArrows;
	private JLabel lblTurnsTaken;
	private JLabel lblSecretsObtained;
	private JLabel lblWarnings;
	private JTextPane warnings;
		
	public GamePanel(GUI guiObject)
	{
		gui = guiObject;
		setLayout(null);
		this.setSize(1000, 800);
		
		JLabel lblTitle = new JLabel("Game Panel");
		lblTitle.setBounds(299, 0, 68, 16);
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
		
		JButton buyArrow = new JButton("Buy Arrow");
		buyArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.mainWindow.triviaScreen.updatePanel("q");
				gui.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buyArrow.setBounds(651, 13, 111, 25);
		add(buyArrow);
		
		JButton buySecret = new JButton("Buy Secret");
		buySecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.mainWindow.triviaScreen.updatePanel("q");
				gui.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buySecret.setBounds(651, 51, 111, 25);
		add(buySecret);
		
		JButton shootArrow = new JButton("Shoot Arrow");
		shootArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRequired = "shoot";
				updatePanel(updateRequired);
			}
		});
		shootArrow.setBounds(651, 91, 111, 25);
		add(shootArrow);
		
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
		moveUp.setBounds(311, 46, 97, 25);
		add(moveUp);
		
		moveUpRight = new JButton("Up Right");
		moveUpRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_RIGHT));
			}
		});
		moveUpRight.setBounds(489, 91, 97, 25);
		add(moveUpRight);
		
		moveUpLeft = new JButton("Up Left");
		moveUpLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.UP_LEFT));
			}
		});
		moveUpLeft.setBounds(151, 104, 97, 25);
		add(moveUpLeft);
		
		moveDown = new JButton("Down");
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN));
			}
		});
		moveDown.setBounds(321, 431, 97, 25);
		add(moveDown);
		
		moveDownRight = new JButton("Down Right");
		moveDownRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_RIGHT));
			}
		});
		moveDownRight.setBounds(476, 383, 110, 25);
		add(moveDownRight);
		
		moveDownLeft = new JButton("Down Left");
		moveDownLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
				gui.notifyControl(new Update(UpdateType.MOVE, true, MovementDirection.DOWN_LEFT));
			}
		});
		moveDownLeft.setBounds(151, 383, 97, 25);
		add(moveDownLeft);
		
		moving[0] = moveUp;
		moving[1] = moveUpRight;
		moving[2] = moveUpLeft;
		moving[3] = moveDown;
		moving[4] = moveDownRight;
		moving[5] = moveDownLeft;
		
		//-----------Making Them Rotate---------------//
		
//		Canvas c = new Canvas();
//		c.setBackground(Color.LIGHT_GRAY);
//		c.setBounds(146, 46, 440, 410);
//		add(c);
		
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setBounds(146, 46, 440, 410);
		add(canvas);
		
		playerName = new JLabel();
		playerName.setBounds(12, 17, 79, 16);
		add(playerName);
		
		lblInventory = new JLabel("Inventory");
		lblInventory.setBounds(12, 95, 56, 16);
		add(lblInventory);
		
		lblCoins = new JLabel("Coins:");
		lblCoins.setBounds(12, 119, 56, 16);
		add(lblCoins);
		
		lblArrows = new JLabel("Arrows:");
		lblArrows.setBounds(12, 134, 56, 16);
		add(lblArrows);
		
		lblTurnsTaken = new JLabel("Turns Taken:");
		lblTurnsTaken.setBounds(12, 55, 97, 16);
		add(lblTurnsTaken);
		
		lblSecretsObtained = new JLabel("Secrets Obtained:");
		lblSecretsObtained.setBounds(12, 188, 111, 16);
		add(lblSecretsObtained);
		
		lblWarnings = new JLabel("WARNINGS");
		lblWarnings.setBounds(677, 208, 68, 16);
		add(lblWarnings);
		
		JTextArea secrets = new JTextArea();
		secrets.setEditable(false);
		secrets.setText("secret 1\nSecret 2\na\na\na\na\na\na\na\na\na\na\na\na");
		secrets.setBounds(12, 205, 97, 162);
		add(secrets);
		
		warnings = new JTextPane();
		warnings.setEditable(false);
		warnings.setBounds(677, 225, 158, 57);
		add(warnings);
				
		updatePanel("Refresh");

	}
	
	private class MyCanvas extends Canvas
	{
		@Override
		public void paint(Graphics g)
		{
			g.setColor(Color.BLACK);

		    Polygon h = new Polygon();
		    
		    int xPos = 220;
		    int yPos = 205;
		    int side = 200;
			for (int i = 0; i < 6; i++)
			{
				h.addPoint((int) (xPos + side * Math.cos(i * Math.PI / 3)),
						  (int) (yPos + side * Math.sin(i * Math.PI / 3)));
			}
			g.drawPolygon(h);
		}
	}
	
	public void updatePanel(String update)
	{
		
		playerName.setText(gui.getName());
		//System.out.println(update);
		
		lblCoins.setText("Coins: " + gui.getCoins());
		lblArrows.setText("Arrows: " + gui.getArrows());
		
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
