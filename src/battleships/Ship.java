package battleships;

public class Ship {

	public String name;
	public int length;
	public int lives = length;

	public Ship(String name, int length, int amount) {

		this.name = name;
		this.length = length;

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

}
