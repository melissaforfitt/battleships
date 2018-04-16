package battleships;

import java.util.ArrayList;
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
	private int gridSize;
	private Button b[][];

	// Create instances of classes
	Player user;
	Player computer;
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
	Ship battleship = new Ship("Battleship", 4);
	Ship destroyer = new Ship("Destroyer", 3);
	Ship patrolBoat = new Ship("Patrol Boat", 2);

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

	public void gamePlay(int gridSize) {

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int i = x;
				int j = y;
				b[x][y] = new Button();
				b[x][y].setId("grid-buttons");
				b[x][y].setMinWidth(50);
				b[x][y].setMinHeight(50);
				grid.add(b[x][y], x, y);
				b[x][y].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (turnCounter == 0) {

							/* Make boat location selections */
							/* TODO: Create array list of ships */
							
							System.out.println("Select location for patrol boat");
							
							
							
							user.userSelection(i, j);
							int compX = computerSelectionX();
							int compY = computerSelectionY();
							computer.computerSelection(compX, compY);
							b[i][j].setId("button-select");
							b[compX][compY].setId("button-select");

						}

						if (turnCounter > 0) {

							/* User's turn to shoot */
							user.shoot(i, j);

							if (computer.isHit(i, j) == true) {
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

								if (user.isHit(shootX, shootY) == true) {
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
						}
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

		scan.close();

		b = new Button[gridSize][gridSize];
		gamePlay(gridSize);

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