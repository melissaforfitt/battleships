package battleships;

import java.util.Random;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Battleships_Main extends Application {

	GridPane grid = new GridPane();
	private int turnCounter = 0;
	private int selectionCounter = 0;
	private int gridSize;
	private Button b[][];

	boolean selection = false;
	boolean patrolBoatSelected = false;

	// Create instances of classes
	Player user;
	Player computer;
	Ship userPatrolBoat;
	Ship computerPatrolBoat;
	Ship userAircraftCarrier;
	Ship computerAircraftCarrier;
	Ship userBattleship;
	Ship computerBattleship;
	Ship userDestroyer;
	Ship computerDestroyer;

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

	public boolean checkValid(int i, int j) {

		boolean checkValid = false;
		return checkValid;

	}

	public void buildGrid(int gridSize) {

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				b[x][y] = new Button();
				b[x][y].setId("grid-buttons");
				b[x][y].setMinWidth(50);
				b[x][y].setMinHeight(50);
				grid.add(b[x][y], x, y);
			}
		}

	}

	public void makeSelection(int gridSize) {

		/* Make boat location selections */
		/* TODO: Create game playable with multiple boat selections */

		Scanner scan = new Scanner(System.in);

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int i = x;
				int j = y;
				b[x][y].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						System.out.println(selectionCounter);
						if (selectionCounter == 0) {
							System.out.println("Patrol Boat Setup:");
							System.out.println("Select location for patrol boat.");
							userPatrolBoat.setLocation(i, j);
							b[i][j].setId("button-select");
							patrolBoatSelected = true;
							if (patrolBoatSelected == true) {
								System.out.println("Vertical or Horizontal?");
								System.out.print(scan.hasNextLine());
								if (scan.hasNextLine()) {
									String input = scan.next();
									if (input.equals("H") || (input.equals("h"))) {
										userPatrolBoat.setLocation(i + 1, j);
										b[i + 1][j].setId("button-select");
									} else if (input.equals("V") || input.equals("v")) {
										userPatrolBoat.setLocation(i, j + 1);
										b[i][j + 1].setId("button-select");
									}
								}

								// Computer's selection
								int compX = computerSelectionX();
								int compY = computerSelectionY();
								computerPatrolBoat.setLocation(compX, compY);
								computerPatrolBoat.setLocation(compX, compY + 1);
								b[compX][compY].setId("button-select");
								b[compX][compY + 1].setId("button-select");

								// selectionCounter++;
								selection = true;
							}
						}
					}
				});
			}

			if (selectionCounter == 1) {

				// code for second type of boat

			}

			if (selectionCounter == 2) {

				// code for third type of boat

			}

			if (selectionCounter == 3) {

				// code for fourth type of boat

			}
		}
	}

	public void gamePlay(int gridSize) {

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int i = x;
				int j = y;

				b[x][y].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						/* User's turn to shoot */
						user.shoot(i, j);

						if (computerPatrolBoat.isHit(i, j) == true) {
							System.out.println("You hit the computer.");
							computer.loseLife();
							b[i][j].setId("button-hit");

							if (computer.livesRemaining() == 0) {
								System.out.println("Game is over. You won!");
							}

						} else {
							System.out.println("You missed.");
							b[i][j].setId("button-miss");
						}

						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), delayEvent -> {

							/* Computer's turn to shoot */
							int shootX = computerSelectionX();
							int shootY = computerSelectionY();
							computer.shoot(shootX, shootY);

							/* TODO: If computer has already selected button, select another button */

							if (userPatrolBoat.isHit(shootX, shootY) == true) {
								System.out.println("Computer hit you.");
								user.loseLife();
								b[shootX][shootY].setId("button-hit");

								if (user.livesRemaining() == 0) {
									System.out.println("Game is over. Computer won!");
								}

							} else {
								System.out.println("Computer missed.");
								b[shootX][shootY].setId("button-miss");
							}
						}));
						tl.play();

						System.out.println(turnCounter);

						turnCounter++;
					}
				});
			}
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/* Allow user to choose grid size */
		System.out.println("What size grid would you like?");
		Scanner scan = new Scanner(System.in);
		gridSize = scan.nextInt();

		user = new User("User", 1, gridSize);
		computer = new Computer("Computer", 1, gridSize);
		userPatrolBoat = new Ship("Patrol Boat", 2, user, gridSize);
		computerPatrolBoat = new Ship("Patrol Boat", 2, computer, gridSize);
		userAircraftCarrier = new Ship("Aircraft Carrier", 5, user, gridSize);
		computerAircraftCarrier = new Ship("Aircraft Carrier", 5, computer, gridSize);
		userBattleship = new Ship("Battleship", 4, user, gridSize);
		computerBattleship = new Ship("Battleship", 4, computer, gridSize);
		userDestroyer = new Ship("Destroyer", 3, user, gridSize);
		computerDestroyer = new Ship("Destroyer", 3, computer, gridSize);

		scan.close();

		b = new Button[gridSize][gridSize];

		buildGrid(gridSize);

		makeSelection(gridSize);

		if (selection == true) {
			gamePlay(gridSize);
		}

		// Create scene
		Scene scene = new Scene(grid, 50 * gridSize, 50 * gridSize);
		scene.getStylesheets().add(this.getClass().getResource("Design.css").toExternalForm());
		primaryStage.setTitle("Battleships");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		// Launch the game
		launch(args);

	}

}