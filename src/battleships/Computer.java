package battleships;

public class Computer extends Player {

	private String name = "computer";
	private boolean computerSelection[][];
	private boolean computerShots[][];

	public Computer(String name, int lives, int gridSize) {
		super(name, lives, gridSize);
		computerSelection = new boolean[gridSize][gridSize];
		computerShots = new boolean[gridSize][gridSize];
	}

	public String getName() {
		return name;
	}

	public boolean shoot() {
		computerSelection[x][y] = true;
		return computerShots[x][y] = true;
	}

	public void computerSelection(int x, int y) {
		computerSelection[x][y] = true;
	}

	public boolean isHit(int x, int y) {

		boolean isHit = false;
		if (computerSelection[x][y] == true) {
			isHit = true;
		} else {
			isHit = false;
		}

		return isHit;
	}

}
