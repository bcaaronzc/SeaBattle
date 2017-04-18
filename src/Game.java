import java.util.Scanner;

public class Game {
	static int smallBoardRows = 10;
	static int smallBoardCols = 10;
	static int BOARDROWS = smallBoardRows + 2;
	static int BOARDCOLS = smallBoardCols + 2;
	static int difficulty = 3;
	static int sinkShip = 0;
	int gameBoard[][] = new int[BOARDROWS][BOARDCOLS];
	
	int maxShipNum = ((BOARDROWS + 1) / 2) * ((BOARDCOLS + 1) / 2);
	BattleShip battleShips[] = new BattleShip[maxShipNum + 1];
	
	public void newGame(){
		// TODO Implement function to change difficulty, default 3
		// Initialize the game board
		for (int row = 0; row < BOARDROWS; row++){
			for (int col = 0; col < BOARDCOLS; col++){
				gameBoard[row][col] = 9;
			}
		}
		for (int row = 1; row < BOARDROWS - 1; row++){
			for (int col = 1; col < BOARDCOLS - 1; col++){
				gameBoard[row][col] = 0;
			}
		}
		
		// Add ships
		for (int i = 1; i <= difficulty; i++){
			boolean randomDirectionBool;
			int randomDirection = (int)(Math.random() * 2);
			if (randomDirection == 0){
				randomDirectionBool = true;
			}
			else{
				randomDirectionBool = false;
			}
			addShip(3, randomDirectionBool, i);
		}
	}
	
	public void addShip(int initSize, boolean initIsVertical, int number){
		boolean repeat = false;
		int randomRow, randomCol;
		
		// Temp Counter
		int counter = 1;
		//
		
		do{
			// Generate a random position under two conditions (vertical and horizontal)
			if (initIsVertical){
				randomRow = (int)(1 + Math.random() * (smallBoardRows - initSize));
				randomCol = (int)(1 + Math.random() * (smallBoardCols));
			}
			else{
				randomRow = (int)(1 + Math.random() * (smallBoardRows));
				randomCol = (int)(1 + Math.random() * (smallBoardCols - initSize));
			}
			
			// Check if overlap
			// when vertical
			if (initIsVertical){
				for (int row = randomRow - 1; row < randomRow + 1 + initSize; row++){
					for (int col = randomCol - 1; col < randomCol + 1; col++){
						if (gameBoard[row][col] == 1){
							System.out.println("Try " + counter++);
							repeat = true;
							break;
						}
						else {
							repeat = false;
						}
					}
				}
			}
			// when horizontal
			else{
				for (int row = randomRow - 1; row < randomRow + 1; row++){
					for (int col = randomCol - 1; col < randomCol + 1 + initSize; col++){
						if (gameBoard[row][col] == 1){
							System.out.println("Try " + counter++);
							repeat = true;
							break;
						}
						else {
							repeat = false;
						}
					}
				}
			}
		} while (repeat);
		
		// Location determined
		int initLoc[] = {randomRow, randomCol};
		
		// create a battle ship
		battleShips[number] = new BattleShip(initSize, initLoc, initIsVertical);
		
		// Add this ship to game board
		if (battleShips[number].isVertical){
			for (int row = battleShips[number].loc[0]; row < battleShips[number].loc[0] + battleShips[number].size; row++){
				gameBoard[row][battleShips[number].loc[1]] = number;
			}
		}
		else{
			for (int col = battleShips[number].loc[1]; col < battleShips[number].loc[1] + battleShips[number].size; col++){
				gameBoard[battleShips[number].loc[0]][col] = number;
			}
		}
	}

	public void fireCannon(){
		int playerChoice[] = playerChoice();
		int choice = gameBoard[playerChoice[0]][playerChoice[1]];
		switch (choice){
		case -1:
			System.out.println("You aleady hit this point.");
			break;
		case 0:
			System.out.println("You missed.");
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			System.out.println("You hit battle ship " + choice + "!");
			gameBoard[playerChoice[0]][playerChoice[1]] = -1;
			hitShip(choice, playerChoice);
			break;
		default: System.out.println("This should not happen, there must be something wrong...");
		}
	}
	
	// Read data from the player, used to send data to fireCannon(int[])
	public int[] playerChoice(){
		Scanner input = new Scanner(System.in);
		int choice[] = new int[2];
		System.out.print("Please enter the row number: ");
		choice[0] = input.nextInt() + 1;
		System.out.print("Please enter the col number: ");
		choice[1] = input.nextInt() + 1;
		//input.close();
		return choice;
	}
	
	// Things happen when hit the ship, should be used in fireCannon(int[])
	public void hitShip(int shipNumber, int[] playerChoice){
		battleShips[shipNumber].gotHit();
		gameBoard[playerChoice[0]][playerChoice[1]] = -1;
		if (battleShips[shipNumber].checkSink()){
			System.out.println("Congratulations! You elimated battle ship " + shipNumber + "!");
		}
	}
	
	public void showBoard(){
		for (int row = 0; row < BOARDROWS; row++){
			for (int col = 0; col < BOARDCOLS; col++){
				System.out.printf("%3d", gameBoard[row][col]);
			}
			System.out.print("\n");
		}
	}
	
	public boolean checkWin(){
		for (int row = 0; row < BOARDROWS; row++){
			for (int col = 0; col < BOARDCOLS; col++){
				for (int i = 1; i <= 8; i++){
					if (gameBoard[row][col] == i){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.newGame();
		game.showBoard();
		do {
			game.fireCannon();
			game.showBoard();
		} while(game.checkWin() == false);
		System.out.println("You win!");
	}
}
