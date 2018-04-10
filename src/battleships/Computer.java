package battleships;

import java.util.Random;

public class Computer extends Player {

	private String name = "computer";
	private boolean computerChosen[][];
	private boolean computerShots[][];

	public Computer(String name, int lives) {
		super(name, lives);

	}

	public int computerSelectionX() {
		Random r = new Random();
		int a = r.nextInt(9) + 0;

		return a;
	}

	public int computerSelectionY() {
		Random r = new Random();
		int b = r.nextInt(9) + 0;

		return b;
	}

	public void computerSelection() {
		x = computerSelectionX();
		y = computerSelectionY();
		computerChosen[x][y] = true;
	}

	public String getName() {
		return name;
	}

	public boolean shoot(int x, int y) {
		return computerShots[x][y] = true;
	}

	public boolean isHit() {

		boolean isHit;
		if (computerShots[x][y] = true) {
			isHit = true;
		} else {
			isHit = false;
		}

		return isHit;
	}

}
