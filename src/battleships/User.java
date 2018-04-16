package battleships;

public class User extends Player {

	private String name = "user";
	private boolean userSelection[][];
	private boolean userShots[][];

	public User(String name, int lives, int gridSize) {
		super(name, lives, gridSize);
		userSelection = new boolean[gridSize][gridSize];
		userShots = new boolean[gridSize][gridSize];
	}

	public void userSelection(int x, int y) {
		userSelection[x][y] = true;
	}

	public boolean shoot(int x, int y) {
		return userShots[x][y] = true;
	}

}
