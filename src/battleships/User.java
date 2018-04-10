package battleships;

public class User extends Player {

	private String name = "user";
	private boolean userChosen[][];
	private boolean userShots[][];

	public User(String name, int lives) {
		super(name, lives);
	}

	public void userSelection(int x, int y) {
		userChosen[x][y] = true;
	}

	public boolean shoot(int x, int y) {
		return userShots[x][y] = true;
	}

	public boolean isHit() {

		boolean isHit;
		if (userShots[x][y] = true) {
			isHit = true;
		} else {
			isHit = false;
		}

		return isHit;
	}

}
