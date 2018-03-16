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
	GridPane grid = new GridPane();
	private int turnCounter = 0;

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
		Patrol_Boat patrolBoat = new Patrol_Boat();
		int pb = patrolBoat.getLength();

		// Create new instance of each object, with parameters as chosen coordinates

		// Make buttons clickable, record coordinates for initial selection
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int x = i;
				int y = j;

				b[i][j].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (turnCounter == 0) {
							// User's selection
							for (int c = 0; c < 2; c++) {
								System.out.println("Select coordinates for Patrol Boat");
								b[x][y].setText("X");
							}
							System.out.println("Battleship selection complete");

							// Computer's selection
							int l = computerSelectionX();
							int m = computerSelectionY();
							int n = m + 1;
							int o = n + 1;
							b[l][m].setText("X");
							b[n][o].setText("X");
							turnCounter++;
						}

						if (turnCounter == 2) {

							System.out.println("Select a coordinate to shoot");
							b[x][y].setText("*");

						}
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