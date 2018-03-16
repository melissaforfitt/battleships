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
		int a = r.nextInt(9) + 1;

		return a;

	}

	public int computerSelectionY() {

		Random r = new Random();
		int b = r.nextInt(9) + 1;

		return b;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		int turnCounter = 0;
		int selectionCounter = 0;
		buildGrid();

		// Create new instance of each object, with parameters as chosen coordinates
		Patrol_Boat patrolBoard = new Patrol_Boat(3, 3, 3, 3);

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
							int a = computerSelectionX();
							int b = computerSelectionY();
							int c = a + 1;
							int d = b + 1;
							b[x][y].setText(a, b);
							b[x][y].setText(c, d);
							turnCounter++;
						}

						if (turnCounter == 1) {

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