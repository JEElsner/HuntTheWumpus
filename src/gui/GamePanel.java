package gui;

import javax.swing.JPanel; 
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

import java.awt.Graphics;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Button;
import java.awt.Label;
import java.awt.Polygon;
import java.awt.Canvas;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;

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
	
	private boolean debugging = false;
	
	private final int UP = 0, UP_RIGHT = 1, DOWN_RIGHT = 2, DOWN = 3, DOWN_LEFT = 4, UP_LEFT = 5;
	
	
	public GamePanel(GUI guiObject)
	{
		
		setLayout(null);
		

		
		JLabel lblTitle = new JLabel("Game Panel");
		lblTitle.setBounds(299, 0, 68, 16);
		add(lblTitle);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				guiObject.mainWindow.changeView(GUI.titleScreen);
			}
		});
		btnMainMenu.setBounds(12, 481, 97, 25);
		add(btnMainMenu);
		
		JButton buyArrow = new JButton("Buy Arrow");
		buyArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buyArrow.setBounds(610, 13, 111, 25);
		add(buyArrow);
		
		JButton buySecret = new JButton("Buy Secret");
		buySecret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiObject.mainWindow.changeView(GUI.trivia);
				updatePanel(updateRequired);
			}
		});
		buySecret.setBounds(610, 51, 111, 25);
		add(buySecret);
		
		JButton shootArrow = new JButton("Shoot Arrow");
		shootArrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRequired = "shoot";
				updatePanel(updateRequired);
			}
		});
		shootArrow.setBounds(610, 91, 111, 25);
		add(shootArrow);
		
//		Canvas c = new Canvas();
//		c.setBackground(Color.LIGHT_GRAY);
//		c.setBounds(146, 46, 440, 410);
//		add(c);
		
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setBounds(146, 46, 440, 410);
		add(canvas);
		
		//----------------------------------//
		//--------Movement Buttons----------//
		//----------------------------------//
		moveUp = new JButton("Up");
		moveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveUp.setBounds(367, 13, 97, 25);
		add(moveUp);
		
		moveUpRight = new JButton("Up Right");
		moveUpRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveUpRight.setBounds(610, 172, 97, 25);
		add(moveUpRight);
		
		moveUpLeft = new JButton("Up Left");
		moveUpLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveUpLeft.setBounds(31, 156, 97, 25);
		add(moveUpLeft);
		
		moveDown = new JButton("Down");
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveDown.setBounds(323, 462, 97, 25);
		add(moveDown);
		
		moveDownRight = new JButton("Down Right");
		moveDownRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveDownRight.setBounds(592, 368, 97, 25);
		add(moveDownRight);
		
		moveDownLeft = new JButton("Down Left");
		moveDownLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePanel("Moving");
			}
		});
		moveDownLeft.setBounds(43, 381, 97, 25);
		add(moveDownLeft);
		
		//-----------Making Them Rotate---------------//

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
		int x = (int)(Math.random() * 2);
		int y = (int)(Math.random() * 2) + 2;
		int z = (int)(Math.random() * 2) + 4;
		System.out.println(update);
		if(x == UP)
			moveUp.setVisible(false);
		if(x == UP_RIGHT)
			moveUpRight.setVisible(false);
		if(y == DOWN_RIGHT)
			moveDownRight.setVisible(false);
		if(y == DOWN)
			moveDown.setVisible(false);
		if(z == UP_LEFT)
			moveUpLeft.setVisible(false);
		if(z == DOWN_LEFT)
			moveDownLeft.setVisible(false);
		
		
		if(x != UP)
			moveUp.setVisible(true);
		if(x != UP_RIGHT)
			moveUpRight.setVisible(true);
		if(y != DOWN_RIGHT)
			moveDownRight.setVisible(true);
		if(y != DOWN)
			moveDown.setVisible(true);
		if(z != UP_LEFT)
			moveUpLeft.setVisible(true);
		if(z != DOWN_LEFT)
			moveDownLeft.setVisible(true);
	}
}
