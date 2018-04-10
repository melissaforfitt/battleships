package battleships;

public class Ship {

	private String name;
	private int length;
	private int lives = length;
	private boolean sunk;

	public Ship(String name, int length) {

		this.name = name;
		this.length = length;
		this.lives = length;
		this.sunk = false;

	}

	public String getName() {
		return name;
	}

	public int getLength() {
		return length;
	}

	public int getLives() {
		return lives;
	}
	
	public boolean isBoatSunk() {
		
		if (lives == 0) {
			sunk = true;
		}
		
		return sunk;
	}

}
