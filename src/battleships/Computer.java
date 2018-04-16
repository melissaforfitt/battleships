package battleships;

public class Computer extends Player {

	private String name = "computer";
	private boolean computerChosen[][];
	private boolean computerShots[][];

	public Computer(String name, int lives, int gridSize) {
		super(name, lives, gridSize);
		computerChosen = new boolean[gridSize][gridSize];
		computerShots = new boolean[gridSize][gridSize];
	}

	public String getName() {
		return name;
	}

	public boolean shoot() {
		computerChosen[x][y] = true;
		return computerShots[x][y] = true;
	}

	public void computerSelection(int x, int y) {
		computerChosen[x][y] = true;
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
