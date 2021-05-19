package minesweeper;

import java.util.Random;

/**
* the game board.
*/
public class GameBoard {

	// --------------------------- Variables ----------------------------

	private int width;
	private int height;
	private Square[][] board;
	private boolean isLost;

	// --------------------------- Moves ----------------------------

	//attempts to open a square then updates the board accordingly
	public void makeMove(int x, int y) {
		if (this.board[x][y].getFlag()) {
			System.out.println("Error: Square is flagged");
		} else {
			this.board[x][y].setOpen();
			if (this.board[x][y].getBomb()) {
				this.isLost = true;
			}
			setUpUpdate();
			updateBoard(x, y);
		}
	}

	//puts a flag on the square if there is not one already
	public void flagSquare(int x, int y) {
		if (this.board[x][y].getFlag()) {
			this.board[x][y].removeFlag();
		} else if (!this.board[x][y].getOpen()) {
			this.board[x][y].setFlag();
		} else {
			System.out.println("Error: Square already opened");
		}
	}

	//resets the board with givin number of bombs
	public void resetBoard(int bombNum) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.board[i][j].reset();
			}
		}
		setBombs(bombNum);
		setupNumbers();
		this.isLost = false;
	}

	// --------------------------- Methods ----------------------------

	//recursively opens up all the squares to be opened
	public void updateBoard(int x, int y) {
		if (this.board[x][y].getUpdate()) {
			return;
		}
		this.board[x][y].updateSquare();

		//if no bombs around square then open all adjacent squares
		if (this.board[x][y].getNumber() == 0) {
			for (int i = x - 1; i <= (x + 1); i++) {
				for (int j = y - 1; j <= (y + 1); j++) {
					if (i >= 0 && i < width && j >= 0 && j < height) {
						this.board[i][j].setOpen();
						updateBoard(i, j);
					}
				}
			}
		}
	}

	//resets the squares updated variable for each square
	public void setUpUpdate() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.board[i][j].unUpdateSquare();
			}
		}
	}

	//gets the game board
	public Square[][] getBoard() {
		return this.board;
	}

	//text game
	//gets the string that represents the game board
	public String getDisplayString() {
		String display = "";
		display += getTopNums(width);
		display += getBoarder(width);
		for (int j = 0; j < height; j++) {
			display += (j + getSpaces(j));
			display += "| ";
			for (int i = 0; i < width; i++) {
				display += this.board[i][j].getSymbol();
				display += ' ';
			}
			display += "|\n";
		}
		display += getBoarder(width);
		return display;
	}

	//text game
	//returns a space if number is less then 10 to account for smaller size
	public String getSpaces(int number) {
		if (number < 10) {
			return " ";
		} else {
			return "";
		}
	}

	//text game
	//gets the numbers for the x axis of the board
	public String getTopNums(int width) {
		String display = "    ";
		for (int i = 0; i < width; i++) {
			display += (i + getSpaces(i));
		}
		return (display + '\n');
	}

	//text game
	//gets the top border for the board based on the width
	public String getBoarder(int width) {
		String display = "  ";
		for (int i = 0; i < ((width * 2) + 3); i++) {
			display += '-';
		}
		return (display + '\n');
	}

	//sets the numbers for all the squares
	public void setupNumbers() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.board[i][j].setNumber(getSquareNumber(i, j));
			}
		}
	}

	//gets the number of bombs aound the square
	public int getSquareNumber(int x, int y) {
		int count = 0;
		for (int i = x - 1; i <= (x + 1); i++) {
			for (int j = y - 1; j <= (y + 1); j++) {

				//check if square exists
				if (i >= 0 && i < width && j >= 0 && j < height) {
					if (this.board[i][j].getBomb()) {
						count++;
					}
				}
			}
		}
		return count;
	}

	//randomly places the bombs on the board
	public void setBombs(int bombNum) {
		Random rdm = new Random();
		for (int i = 0; i < bombNum; i++) {
			int x = rdm.nextInt(this.width);
			int y = rdm.nextInt(this.height);

			//if bomb already exists in spot, find another
			if (board[x][y].getBomb()) {
				i--;
				continue;

			//place bomb
			} else {
				board[x][y].setBomb();
			}
		}
	}

	//creates the game board and initializes the squares
	public void makeBoard() {
		this.board = new Square[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.board[i][j] = new Square();
			}
		}
	}

	// --------------------------- Getters and Setters ----------------------------

	public void gameOver() {
		this.isLost = true;
	}

	public boolean isGameOver() {
		return this.isLost;
	}

	public void setWidth(int newWidth) {
		this.width = newWidth;
	}

	public int getWidth() {
		return this.width;
	}

	public void setHeight(int newHeight) {
		this.height = newHeight;
	}

	public int getHeight() {
		return this.height;
	}

	// --------------------------- Constructors ----------------------------

	//default settings
	public GameBoard() {
		this.width = 10;
		this.height = 10;
		makeBoard();
		setBombs(10);
		setupNumbers();
		this.isLost = false;
	}

	//custom settings
	public GameBoard(int newWidth, int newHeight, int bombNum) {
		this.width = newWidth;
		this.height = newHeight;
		makeBoard();
		setBombs(bombNum);
		setupNumbers();
		this.isLost = false;
	}

}
