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

	private int gridSize;
	private Button b[][];
	private boolean userChosen[][];
	private boolean computerChosen[][];
	private int aircraftCarrierAmount;
	private int battleshipAmount;
	private int destroyerAmount;
	private int patrolBoatAmount;

	// Create instances of ships
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5, aircraftCarrierAmount);
	Ship battleship = new Ship("Battleship", 4, battleshipAmount);
	Ship destroyer = new Ship("Destroyer", 3, destroyerAmount);
	Ship patrolBoat = new Ship("Patrol Boat", 2, patrolBoatAmount);

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

		// Make buttons clickable, record coordinates for initial selection
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				int x = i;
				int y = j;
				b[i][j] = new Button();
				b[i][j].setId("grid-buttons");
				b[i][j].setMinWidth(50);
				b[i][j].setMinHeight(50);
				grid.add(b[i][j], i, j);
				b[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (turnCounter == 0) {
							// User's selection
							b[x][y].setText("X");
							userChosen[x][y] = true;
							turnCounter++;
						}
						// Computer's boat selection
						if (turnCounter == 1) {
							int l = computerSelectionX();
							int m = computerSelectionY();
							b[l][m].setText("X");
							computerChosen[l][m] = true;
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
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.out.println("What size grid would you like?");
		Scanner scan = new Scanner(System.in);
		gridSize = scan.nextInt();
		System.out.println("How many Aircraft Carriers would you like?");
		aircraftCarrierAmount = scan.nextInt();
		System.out.println("How many Battleships would you like?");
		battleshipAmount = scan.nextInt();
		System.out.println("How many Destroyers would you like?");
		destroyerAmount = scan.nextInt();
		System.out.println("How many Patrol Boats would you like?");
		patrolBoatAmount = scan.nextInt();

		scan.close();

		b = new Button[gridSize][gridSize];
		userChosen = new boolean[gridSize][gridSize];
		computerChosen = new boolean[gridSize][gridSize];

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