package battleships;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Battleships_Main extends Application {

	Button b[][] = new Button[10][10];
	boolean userChosen[][] = new boolean[10][10];
	boolean computerChosen[][] = new boolean[10][10];
	GridPane grid = new GridPane();
	private int turnCounter = 0;

	// Create instances of ships
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
	Ship battleship = new Ship("Battleship", 4);
	Ship destroyer = new Ship("Destroyer", 3);
	Ship patrolBoat = new Ship("Patrol Boat", 2);

	public void buildGrid() {

		// Make a grid of 10 x 10 buttons
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				b[i][j] = new Button();
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

		buildGrid();

		// Get computer to choose coordinates for its boats
		for (int p = 0; p < patrolBoat.getLength(); p++) {

			int x = computerSelectionX();
			int y = computerSelectionY();

		}

		// Make buttons clickable, record coordinates for initial selection
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int x = i;
				int y = j;

				b[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (turnCounter == 0) {
							// User's selection
							b[x][y].setText("X");
							userChosen[x][y] = true;
							b[x][y + 1].setText("X");
							userChosen[x][y + 1] = true;
							// System.out.println(userChosen[9][7]);
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
							// System.out.println(computerChosen[0][4]);
						}
						// User's turn to shoot
						if (turnCounter % 2 == 0) {
							System.out.println("Select a coordinate to shoot");
							b[x][y].setText("*");
							if (computerChosen[x][y] == true) {
								System.out.println("Well done, you shot the boat");
							} else {
								System.out.println("That was a miss");
							}
							turnCounter++;
						}
						// Computer's turn to shoot
						if (turnCounter != 1 && turnCounter % 2 != 0) {
							int l = computerSelectionX();
							int m = computerSelectionY();
							b[l][m].setText("*");
							if (userChosen[l][m] == true) {
								System.out.println("The computer shot your boat");
							} else {
								System.out.println("The computer missed your boat");
							}
						}
						turnCounter++;
					}
				});
			}
		}

		// Create scene
		Scene scene = new Scene(grid, 500, 500);

		primaryStage.setTitle("Battleships");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		// Launch the game
		launch(args);

	}

}