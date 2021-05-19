package minesweeper;

/**
* a square in the game board.
*/
public class Square {

	// --------------------------- Variables ----------------------------

	private int number;
	private boolean isBomb;
	private boolean isOpen;
	private boolean isFlag;
	private boolean isUpdated;

	// --------------------------- Methods ----------------------------

	//gets the symbol that represents the state of the square
	public char getSymbol() {
		if (this.isFlag == true) {
			return 'F';
		} else if (this.isOpen == false) {
			return '#';
		} else if (this.isBomb == true) {
			return '*';
		} else {
			return (char)(number + '0');
		}
	}

	//resets the square to default status
	public void reset() {
		this.number = 0;
		this.isBomb = false;
		this.isOpen = false;
		this.isFlag = false;
		this.isUpdated = false;
	}

	// --------------------------- Getters and Setters ----------------------------

	public void setNumber(int newNumber) {
		this.number = newNumber;
	}

	public int getNumber() {
		return this.number;
	}

	public void updateSquare() {
		this.isUpdated = true;
	}

	public void unUpdateSquare() {
		this.isUpdated = false;
	}

	public boolean getUpdate() {
		return this.isUpdated;
	}

	public void setBomb() {
		this.isBomb = true;
	}

	public boolean getBomb() {
		return this.isBomb;
	}

	public void setOpen() {
		this.isOpen = true;
	}

	public boolean getOpen() {
		return this.isOpen;
	}

	public void setFlag() {
		this.isFlag = true;
	}

	public void removeFlag() {
		this.isFlag = false;
	}

	public boolean getFlag() {
		return this.isFlag;
	}

	// --------------------------- Constructors ----------------------------

	//default
	public Square() {
		this.number = 0;
		this.isBomb = false;
		this.isOpen = false;
		this.isFlag = false;
		this.isUpdated = false;
	}

	//declare if bomb
	public Square(boolean bomb) {
		this.number = 0;
		this.isBomb = bomb;
		this.isOpen = false;
		this.isFlag = false;
		this.isUpdated = false;
	}

}
