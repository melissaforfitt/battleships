package battleships;

public class Ship {

	private Player player;
	private int length;
	private int lives = length;
	private boolean sunk;
	private boolean location[][];

	public Ship(String name, int length, Player player, int gridSize) {

		this.player = player;
		this.length = length;
		this.lives = length;
		this.sunk = false;
		location = new boolean[gridSize][gridSize];

	}

	public Player getPlayer() {
		return player;
	}

	public int getLength() {
		return length;
	}

	public int getLives() {
		return lives;
	}

	public void setLocation(int x, int y) {
		location[x][y] = true;
	}

	public boolean isHit(int x, int y) {

		boolean isHit = false;
		if (location[x][y] == true) {
			isHit = true;
		} else {
			isHit = false;
		}

		return isHit;
	}

	public boolean isBoatSunk() {

		if (lives == 0) {
			sunk = true;
		}

		return sunk;
	}

}
