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
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Battleships_Main extends Application {

	// Create variables needed throughout game
	private GridPane selectionGrid = new GridPane();
	private GridPane hitsGrid = new GridPane();
	private int turnCounter = 0;
	private int selectionCounter = 0;
	private int gridSize;
	private Button selectionButtons[][];
	private Button hitButtons[][];
	static Scanner scan;

	private int compX;
	private int compY;

	private boolean selection;
	private boolean patrolBoatSelected;
	private boolean destroyerBoatSelected;
	private boolean battleshipSelected;
	private boolean aircraftCarrierSelected;

	/* Create instances of classes */
	private Player user;
	private Player computer;
	private Ship userPatrolBoat;
	private Ship computerPatrolBoat;
	private Ship userAircraftCarrier;
	private Ship computerAircraftCarrier;
	private Ship userBattleship;
	private Ship computerBattleship;
	private Ship userDestroyer;
	private Ship computerDestroyer;

	/* Computer selection methods get random number for X and Y coordinates */
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

	public void buildSelectionGrid(int gridSize) {

		for (int l = 0; l < gridSize; l++) {
			for (int m = 0; m < gridSize; m++) {
				selectionButtons[l][m] = new Button();
				selectionButtons[l][m].setId("grid-buttons");
				selectionButtons[l][m].setMinWidth(50);
				selectionButtons[l][m].setMinHeight(50);
				selectionGrid.add(selectionButtons[l][m], l, m);
			}
		}
	}

	public void buildHitsGrid(int gridSize) {

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				hitButtons[x][y] = new Button();
				hitButtons[x][y].setId("grid-buttons");
				hitButtons[x][y].setMinWidth(50);
				hitButtons[x][y].setMinHeight(50);
				hitsGrid.add(hitButtons[x][y], x, y);
			}
		}
	}

	public void makeSelection(int gridSize) {

		/* Make boat location selections for user and computer */

		if (selectionCounter == 0) {
			System.out.println("Patrol Boat Setup:");
			System.out.println("Select location for patrol boat.");
		}

		for (int l = 0; l < gridSize; l++) {
			for (int m = 0; m < gridSize; m++) {
				int n = l;
				int o = m;
				selectionButtons[l][m].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						if (selectionCounter == 0) {

							userPatrolBoat.setLocation(n, o);
							selectionButtons[n][o].setId("button-select");
							patrolBoatSelected = true;
							if (patrolBoatSelected == true) {
								System.out.println("Vertical or Horizontal?");
								String position = scan.next();
								if (position.equals("H") || (position.equals("h"))) {
									userPatrolBoat.setLocation(n + 1, o);
									selectionButtons[n + 1][o].setId("button-select");
								} else if (position.equals("V") || position.equals("v")) {
									userPatrolBoat.setLocation(n, o + 1);
									selectionButtons[n][o + 1].setId("button-select");
								}

								// Computer's selection
								compX = computerSelectionX();
								compY = computerSelectionY();
								computerPatrolBoat.setLocation(compX, compY);
								computerPatrolBoat.setLocation(compX, compY + 1);

								System.out.println("Destroyer Boat Setup:");
								System.out.println("Select location for destroyer boat.");
							}
						}

						if (selectionCounter == 1) {

							userDestroyer.setLocation(n, o);
							selectionButtons[n][o].setId("button-select");
							destroyerBoatSelected = true;
							if (destroyerBoatSelected == true) {
								System.out.println("Vertical or Horizontal?");
								String position = scan.next();
								if (position.equals("H") || (position.equals("h"))) {
									userDestroyer.setLocation(n + 1, o);
									userDestroyer.setLocation(n + 2, o);
									selectionButtons[n + 1][o].setId("button-select");
									selectionButtons[n + 2][o].setId("button-select");
								} else if (position.equals("V") || position.equals("v")) {
									userDestroyer.setLocation(n, o + 1);
									userDestroyer.setLocation(n, o + 2);
									selectionButtons[n][o + 1].setId("button-select");
									selectionButtons[n][o + 2].setId("button-select");
								}

								// Computer's selection
								compX = computerSelectionX();
								compY = computerSelectionY();
								computerDestroyer.setLocation(compX, compY);
								computerDestroyer.setLocation(compX + 1, compY);
								computerDestroyer.setLocation(compX + 2, compY);

								System.out.println("Battleship Setup:");
								System.out.println("Select location for battleship.");
							}
						}

						if (selectionCounter == 2) {

							userBattleship.setLocation(n, o);
							selectionButtons[n][o].setId("button-select");
							battleshipSelected = true;
							if (battleshipSelected == true) {
								System.out.println("Vertical or Horizontal?");
								String position = scan.next();
								if (position.equals("H") || (position.equals("h"))) {
									userBattleship.setLocation(n + 1, o);
									userBattleship.setLocation(n + 2, o);
									userBattleship.setLocation(n + 3, o);
									selectionButtons[n + 1][o].setId("button-select");
									selectionButtons[n + 2][o].setId("button-select");
									selectionButtons[n + 3][o].setId("button-select");
								} else if (position.equals("V") || position.equals("v")) {
									userBattleship.setLocation(n, o + 1);
									userBattleship.setLocation(n, o + 2);
									userBattleship.setLocation(n, o + 3);
									selectionButtons[n][o + 1].setId("button-select");
									selectionButtons[n][o + 2].setId("button-select");
									selectionButtons[n][o + 3].setId("button-select");
								}

								// Computer's selection
								compX = computerSelectionX();
								compY = computerSelectionY();
								computerBattleship.setLocation(compX, compY);
								computerBattleship.setLocation(compX, compY + 1);
								computerBattleship.setLocation(compX, compY + 2);
								computerBattleship.setLocation(compX, compY + 3);

								System.out.println("Aircraft Carrier Setup:");
								System.out.println("Select location for aircraft carrier.");
							}

						}

						if (selectionCounter == 3) {

							userAircraftCarrier.setLocation(n, o);
							selectionButtons[n][o].setId("button-select");
							aircraftCarrierSelected = true;
							if (aircraftCarrierSelected == true) {
								System.out.println("Vertical or Horizontal?");
								String position = scan.next();
								if (position.equals("H") || (position.equals("h"))) {
									userAircraftCarrier.setLocation(n + 1, o);
									userAircraftCarrier.setLocation(n + 2, o);
									userAircraftCarrier.setLocation(n + 3, o);
									selectionButtons[n + 1][o].setId("button-select");
									selectionButtons[n + 2][o].setId("button-select");
									selectionButtons[n + 3][o].setId("button-select");
									selectionButtons[n + 4][o].setId("button-select");
								} else if (position.equals("V") || position.equals("v")) {
									userAircraftCarrier.setLocation(n, o + 1);
									userAircraftCarrier.setLocation(n, o + 2);
									userAircraftCarrier.setLocation(n, o + 3);
									selectionButtons[n][o + 1].setId("button-select");
									selectionButtons[n][o + 2].setId("button-select");
									selectionButtons[n][o + 3].setId("button-select");
									selectionButtons[n][o + 4].setId("button-select");
								}

								// Computer's selection
								compX = computerSelectionX();
								compY = computerSelectionY();
								computerAircraftCarrier.setLocation(compX, compY);
								computerAircraftCarrier.setLocation(compX, compY + 1);
								computerAircraftCarrier.setLocation(compX, compY + 2);
								computerAircraftCarrier.setLocation(compX, compY + 3);
								computerAircraftCarrier.setLocation(compX, compY + 4);

								/* Once boat selections have been made, continue with game */
								selection = true;

								System.out
										.println("Selection complete. Select a place to shoot to commence the battle.");

								if (selection == true) {
									gamePlay(gridSize);
								}
							}
						}
						selectionCounter++;
					}
				});
			}
		}
	}

	public void gamePlay(int gridSize) {

		/* Main rules for battleship game play */

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {
				int i = x;
				int j = y;

				hitButtons[x][y].setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {

						/* User's turn to shoot */
						user.shoot(i, j);

						if ((computerPatrolBoat.isHit(i, j) == true) || (computerDestroyer.isHit(i, j) == true)
								|| (computerBattleship.isHit(i, j) == true)
								|| (computerAircraftCarrier.isHit(i, j) == true)) {
							System.out.println("You hit the computer.");
							computer.loseLife();
							hitButtons[i][j].setId("button-hit");

							if (computer.livesRemaining() == 0) {
								System.out.println("Game is over. You won!");
							}

						} else {
							System.out.println("You missed.");
							hitButtons[i][j].setId("button-miss");
						}

						Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), delayEvent -> {

							/* Computer's turn to shoot */
							int shootX = computerSelectionX();
							int shootY = computerSelectionY();
							computer.shoot(shootX, shootY);

							/* TODO: Announce which boat has been sunk */
							/* TODO: Put dialogue in window, not console */

							if ((userPatrolBoat.isHit(shootX, shootY) == true)
									|| (userDestroyer.isHit(shootX, shootY) == true)
									|| (userBattleship.isHit(shootX, shootY) == true)
									|| (userAircraftCarrier.isHit(shootX, shootY) == true)) {
								System.out.println("Computer hit you.");
								user.loseLife();

								if (user.livesRemaining() == 0) {
									System.out.println("Game is over. Computer won!");
								}

							} else {
								System.out.println("Computer missed.");
							}

						}));
						tl.play();

						turnCounter++;
					}
				});
			}
		}
	}

	@Override
	public void start(Stage primaryStage) {

		/* Allow user to choose grid size */
		System.out.println("What size grid would you like?");
		gridSize = scan.nextInt();
		System.out.println("Welcome to Battleships!");

		/* Create instances of players and ships */
		user = new User("User", 1, gridSize);
		computer = new Computer("Computer", 1, gridSize);
		userPatrolBoat = new Ship("Patrol Boat", 2, user, gridSize);
		computerPatrolBoat = new Ship("Patrol Boat", 2, computer, gridSize);
		userDestroyer = new Ship("Destroyer", 3, user, gridSize);
		computerDestroyer = new Ship("Destroyer", 3, computer, gridSize);
		userBattleship = new Ship("Battleship", 4, user, gridSize);
		computerBattleship = new Ship("Battleship", 4, computer, gridSize);
		userAircraftCarrier = new Ship("Aircraft Carrier", 5, user, gridSize);
		computerAircraftCarrier = new Ship("Aircraft Carrier", 5, computer, gridSize);

		selectionButtons = new Button[gridSize][gridSize];
		hitButtons = new Button[gridSize][gridSize];

		/* Create HBox and add 2 grids to it (one for selection, one for hits/misses) */
		HBox hbox = new HBox();
		Label selection = new Label("Selection Grid");
		selection.setTranslateX(75);
		selection.setTranslateY(520);
		Label hits = new Label("Hits/Misses Grid");
		hits.setTranslateX(100);
		hits.setTranslateY(520);

		hbox.getChildren().add(selection);
		hbox.getChildren().add(selectionGrid);
		hbox.getChildren().add(hits);
		hbox.getChildren().add(hitsGrid);

		buildSelectionGrid(gridSize);
		buildHitsGrid(gridSize);

		makeSelection(gridSize);

		/* Create scene */
		Scene scene = new Scene(hbox, 50 * (gridSize * 2) + 250, 50 * (gridSize) + 100);
		scene.getStylesheets().add(this.getClass().getResource("Design.css").toExternalForm());
		primaryStage.setTitle("Battleships");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {

		scan = new Scanner(System.in);

		/* Launch the game */
		launch(args);

		scan.close();

	}

}