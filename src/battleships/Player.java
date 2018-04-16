package battleships;

import java.util.Random;

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
