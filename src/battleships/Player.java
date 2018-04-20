package battleships;

public class Player {

	int x;
	int y;
	String name;
	int lives = 14;

	public Player(String name, int lives, int gridSize) {

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void userSelection(int x, int y) {

	}

	public void computerSelection(int x, int y) {

	}

	public boolean shoot(int x, int y) {
		return false;
	}

	public int livesRemaining() {
		return lives;
	}

	public void loseLife() {
		lives--;
	}

}
