public class Game {
	static int BOARDROWS = 7;
	static int BOARDCOLS = 7;
	int gameBoard[][] = new int[BOARDROWS][BOARDCOLS];
	
	int sizeTest = 3;
	int locTest[] = {2, 3};
	boolean isVerticalTest = true;
	BattleShip battleShipTest = new BattleShip(sizeTest, locTest, isVerticalTest);
	
	public void newGame(){
		// TODO Implement function to change difficulty
		// Initialize the game board
		for (int row = 0; row < 7; row++){
			for (int col = 0; col < 7; col++){
				gameBoard[row][col] = 0;
			}
		}
		
		// Add a test battle ship
		if (battleShipTest.isVertical){
			for (int row = battleShipTest.loc[0]; row < battleShipTest.loc[0] + battleShipTest.size; row++){
				gameBoard[row][battleShipTest.loc[1]] = 1;
			}
		}
		else{
			for (int col = battleShipTest.loc[1]; col < battleShipTest.loc[1] + battleShipTest.size; col++){
				gameBoard[battleShipTest.loc[0]][col] = 1;
			}
		}
	}
	
	public void showBoard(){
		for (int row = 0; row < BOARDROWS; row++){
			for (int col = 0; col < BOARDCOLS; col++){
				System.out.print(gameBoard[row][col]);
				System.out.print(" ");
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
