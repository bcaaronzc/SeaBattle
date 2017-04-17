public class Game {
	static int BOARDROWS = 10;
	static int BOARDCOLS = 10;
	static int difficulty = 3;
	int gameBoard[][] = new int[BOARDROWS][BOARDCOLS];
	
	int maxShipNum = ((BOARDROWS + 1) / 2) * ((BOARDCOLS + 1) / 2);
	BattleShip battleShips[] = new BattleShip[maxShipNum];
	
	public void newGame(){
		// TODO Implement function to change difficulty, default 3
		// Initialize the game board
		for (int row = 0; row < 7; row++){
			for (int col = 0; col < 7; col++){
				gameBoard[row][col] = 0;
			}
		}
		
		for (int i = 0; i < difficulty; i++){
			int randomSize = (int)(Math.random() * 4);
			boolean randomDirectionBool;
			int randomDirection = (int)(Math.random() * 2);
			if (randomDirection == 0){
				randomDirectionBool = true;
			}
			else{
				randomDirectionBool = false;
			}
			addShip(randomSize, randomDirectionBool, i);
		}
	}
	
	private void addShip(int initSize, boolean initIsVertical, int number){
		boolean repeat = false;
		int randomRow, randomCol;
		do{
			// Generate a random position under two conditions (vertical and horizontal)
			if (initIsVertical){
				randomRow = (int)(Math.random() * (BOARDROWS - initSize + 1));
				randomCol = (int)(Math.random() * (BOARDCOLS + 1));
			}
			else{
				randomRow = (int)(Math.random() * (BOARDROWS + 1));
				randomCol = (int)(Math.random() * (BOARDCOLS - initSize + 1));
			}
			
			// Check if overlap
			// when vertical
			if (initIsVertical){
				for (int row = randomRow; row < randomRow + initSize; row++){
					if (gameBoard[row - 1][randomCol - 1] == 1 && gameBoard[row - 1][randomCol] == 1 && gameBoard[row - 1][randomCol + 1] == 1 &&
							gameBoard[row][randomCol - 1] == 1 && gameBoard[row][randomCol] == 1 && gameBoard[row][randomCol + 1] == 1 &&
							gameBoard[row + 1][randomCol - 1] == 1 && gameBoard[row + 1][randomCol] == 1 && gameBoard[row - 1][randomCol + 1] == 1){
						repeat = true;
					}
				}
			}
			// when horizontal
			else{
				for (int col = randomCol; col < randomCol + initSize; col++){
					if (gameBoard[randomRow - 1][col - 1] == 1 && gameBoard[randomRow - 1][col] == 1 && gameBoard[randomRow - 1][col + 1] == 1 &&
							gameBoard[randomRow][col - 1] == 1 && gameBoard[randomRow][col] == 1 && gameBoard[randomRow][col + 1] == 1 &&
							gameBoard[randomRow + 1][col - 1] == 1 && gameBoard[randomRow + 1][col] == 1 && gameBoard[randomRow + 1][col + 1] == 1){
						repeat = true;
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
				gameBoard[row][battleShips[number].loc[1]] = 1;
			}
		}
		else{
			for (int col = battleShips[number].loc[1]; col < battleShips[number].loc[1] + battleShips[number].size; col++){
				gameBoard[battleShips[number].loc[0]][col] = 1;
			}
		}
	}
	
	public void showBoard(){
		for (int row = 0; row < BOARDROWS; row++){
			for (int col = 0; col < BOARDCOLS; col++){
				System.out.print(gameBoard[row][col]);
				System.out.print("  ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.newGame();
		game.showBoard();
	}
}
