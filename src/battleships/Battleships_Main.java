package battleships;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Battleships_Main extends Application {

	GridPane grid = new GridPane();
	private int turnCounter = 0;
	private int gridSize;
	private Button b[][];

	// Create instances of classes
	Player user = new User("User", 1);
	Player computer = new Computer("Computer", 1);
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
	Ship battleship = new Ship("Battleship", 4);
	Ship destroyer = new Ship("Destroyer", 3);
	Ship patrolBoat = new Ship("Patrol Boat", 2);

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

							user.userSelection(i, j);
							computer.computerSelection();
							b[i][j].setId("button-select");

						}

						if (turnCounter % 2 == 0) {

							user.shoot(i, j);

							if (computer.isHit() == true) {
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

						} else {

							computer.shoot(i, j);

							if (user.isHit() == true) {
								System.out.println("Computer hit you.");
								user.loseLife();
								b[i][j].setId("button-hit");

								if (user.livesRemaining() == 0) {
									System.out.println("Game is over. Computer won!");
								}
							} else {
								System.out.println("Computer missed.");
								b[i][j].setId("button-miss");
							}
						}
					}
				});
			}
		}
		turnCounter++;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("What size grid would you like?");
		Scanner scan = new Scanner(System.in);
		gridSize = scan.nextInt();

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