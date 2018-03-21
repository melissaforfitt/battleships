package battleships;

import java.util.Random;
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
	private String currentPlayer = "";
	private String user = "User";
	private String computer = "Computer";

	// Create instances of ships
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
	Ship battleship = new Ship("Battleship", 4);
	Ship destroyer = new Ship("Destroyer", 3);
	Ship patrolBoat = new Ship("Patrol Boat", 2);

	public void buildGrid(int gridSize) {

		Button b[][] = new Button[gridSize][gridSize];

		// Make a grid of buttons according to amount defined by user
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				b[i][j] = new Button();
				b[i][j].setId("grid-buttons");
				b[i][j].setMinWidth(50);
				b[i][j].setMinHeight(50);
				grid.add(b[i][j], i, j);
			}
		}
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

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("What size grid would you like?");
		Scanner scan = new Scanner(System.in);
		int gridSize = scan.nextInt();

		Button b[][] = new Button[gridSize][gridSize];

		boolean userChosen[][] = new boolean[gridSize][gridSize];
		boolean computerChosen[][] = new boolean[gridSize][gridSize];

		buildGrid(gridSize);

		// Make buttons clickable, record coordinates for initial selection
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				int x = i;
				int y = j;
				b[i][j] = new Button();
				b[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (turnCounter == 0) {
							// User's selection
							b[x][y].setText("X");
							userChosen[x][y] = true;
							b[x][y + 1].setText("X");
							userChosen[x][y + 1] = true;
							turnCounter++;
						}
						// Computer's boat selection
						if (turnCounter == 1) {
							int l = computerSelectionX();
							int m = computerSelectionY();
							b[l][m].setText("X");
							computerChosen[l][m] = true;
							b[l + 1][m].setText("X");
							computerChosen[l + 1][m] = true;
						}
						// User's turn to shoot
						if (turnCounter % 2 == 0) {
							currentPlayer = user;
							System.out.println("Select a coordinate to shoot");
							b[x][y].setText("*");
							if (computerChosen[x][y] == true) {
								System.out.println("Well done, you shot the boat");
								b[x][y].setId("button-hit");
								patrolBoat.lives = patrolBoat.lives - 1;
							} else {
								System.out.println("That was a miss");
								b[x][y].setId("button-miss");
							}
							turnCounter++;
						}
						// Computer's turn to shoot
						if (turnCounter != 1 && turnCounter % 2 != 0) {
							currentPlayer = computer;
							int l = computerSelectionX();
							int m = computerSelectionY();
							b[l][m].setText("*");
							if (userChosen[l][m] == true) {
								System.out.println("The computer shot your boat");
								b[l][m].setId("button-hit");
								patrolBoat.lives = patrolBoat.lives - 1;
							} else {
								System.out.println("The computer missed your boat");
								b[l][m].setId("button-miss");
							}
						}

						// Check conditions for winning
						if (patrolBoat.getLives() == 0) {
							System.out.println("Game over, " + currentPlayer + " wins the game");
						}

						// Increment turn
						turnCounter++;
					}
				});
			}
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