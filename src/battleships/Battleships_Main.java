package battleships;

import javafx.application.Application;
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
	
	
		@Override
	public void start(Stage primaryStage) throws Exception {
		
			buildGrid();
		
	}
	
	
	public static void main(String[] args) {
		
		// Launch the game
		launch(args);

	}







}