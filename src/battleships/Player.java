package battleships;

public class Player {

	int x;
	int y;
	String name;
	int lives;

	public Player(String name, int lives, int gridSize) {

		this.name = name;
		this.lives = lives;

	}

	public String getName() {
		return name;
	}

	public void userSelection(int x, int y) {

	}

	public void computerSelection() {

	}

	public boolean shoot(int x, int y) {
		return false;
	}

	public int livesRemaining() {
		return lives;
	}

	public boolean isHit() {
		return false;
	}

	public void loseLife() {
		lives--;
	}

}
