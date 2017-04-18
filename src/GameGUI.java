import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameGUI extends JFrame implements ActionListener{
	Game game = new Game();
	JButton buttons[][] = new JButton[game.getBoardRow()][game.getBoardCols()];
	
	public GameGUI(){
		setTitle("SeaBattle");
		setSize(80 * game.getBoardRow(), 80 * game.getBoardCols());
		setVisible(true);
		setLayout(new GridLayout(game.getBoardRow(), game.getBoardCols()));
		game.newGame();
	}
	
	public void gameStart(){
		//JButton buttons[][] = new JButton[game.getBoardRow()][game.getBoardCols()];
		for (int row = 0; row < game.getBoardRow(); row++){
			for (int col = 0; col < game.getBoardCols(); col++){
				buttons[row][col] = new JButton("");
				this.add(buttons[row][col]);
				buttons[row][col].addActionListener(this);
			}
		}
	}
	
	public void actionPerformed(ActionEvent event){
		for (int row = 0; row < game.getBoardRow(); row++){
			for (int col = 0; col < game.getBoardCols(); col++){
				if (event.getSource() == buttons[row][col]){
					int playerChoice[] = {row, col};
					game.fireCannon(playerChoice);
					buttons[playerChoice[0]][playerChoice[1]].setText("" + game.gameBoard[playerChoice[0]][playerChoice[1]]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameGUI newGame = new GameGUI();
		newGame.gameStart();
	}

}
