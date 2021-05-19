package minesweeper;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* the game controller.
*/
public class Minesweeper {

	// --------------------------- Variables ----------------------------

	private GameBoard game;

	//runs the game in the console
	public void runGameText() {
		System.out.println("Hello");
		System.out.println("input: X,Y,M");
		System.out.println("X = x position");
		System.out.println("Y = y position");
		System.out.println("M = O to open, F to Flag");
		System.out.println(game.getDisplayString());

		Scanner scan = new Scanner(System.in);
		String[] inputs = new String[3];
		int x = 0;
		int y = 0;
		char move = ' ';

		while (!game.isGameOver()) {
			inputs = scan.nextLine().split(",");
			x = Integer.parseInt(inputs[0]);
			y = Integer.parseInt(inputs[1]);
			move = inputs[2].charAt(0);
			if (move == 'f' || move == 'F') {
				game.flagSquare(x,y);
			} else if (move == 'o' || move == 'O') {
				game.makeMove(x,y);
			} else {
				System.out.println("Error: please enter a valid move");
			}
			System.out.println(game.getDisplayString());
		}

		System.out.println("Game over?");
	}

	protected void paintComponent(Graphics g) {

   	}

	//runs the game in the window
	public void runGame(JPanel gamePanel, String diff) {

	}

	//creates and runs the game in a new window
	public void runGameGUI() {

		//create window
		JFrame gameFrame = new JFrame("Minesweeper");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(800, 600);

		//create panel to hold game
		JPanel windowPanel = new JPanel(new BorderLayout());
		windowPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    	gameFrame.getContentPane().add(windowPanel);

		//create title of game
		JLabel title = new JLabel("Minesweeper");
		title.setFont(new Font("Verdana", Font.PLAIN, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		windowPanel.add(title, BorderLayout.PAGE_START);

		//create the panel to hold the game
		JPanel gamePanel = new JPanel();

		//create options menu
		JPanel menu = new JPanel(new FlowLayout());
		menu.setBorder(BorderFactory.createLineBorder(Color.black));
		windowPanel.add(menu, BorderLayout.PAGE_END);

		//create difficulty selection
		JLabel selDif = new JLabel("Difficulty: ");
		selDif.setHorizontalAlignment(SwingConstants.CENTER);
		menu.add(selDif);
		String[] diffs = {"Beginer", "Intermediate", "Expert"};
		JComboBox diffList = new JComboBox(diffs);
		menu.add(diffList);

		//create start button
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

     		public void actionPerformed(ActionEvent e) {
				runGame(gamePanel, (String)diffList.getSelectedItem());
			}

    	});
		menu.add(startButton);


		//display window
		gameFrame.setVisible(true);

	}

	// --------------------------- Constructors ----------------------------

	public Minesweeper() {

	}

	// --------------------------- Main ----------------------------

    public static void main(String[] args) {
		Minesweeper gameRunner = new Minesweeper();
		game = new GameBoard(15, 15, 10);
		//gameRunner.runGameText();
		gameRunner.runGameGUI();
    }

}

/**
* the game panel class.
*/
private static class GamePanel extends JPanel {
  protected void paintComponent(Graphics g) {

  }

}
