import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameGUI extends JFrame{
	Game game = new Game();
	
	public GameGUI(){
		setTitle("SeaBattle");
		setSize(80 * game.getBoardRow(), 80 * game.getBoardCols());
		setVisible(true);
		setLayout(new GridLayout(game.getBoardRow(), game.getBoardCols()));
	}
	
	public void gameStart(){
		int numButton = game.getBoardRow() * game.getBoardCols();
		JButton buttons[] = new JButton[numButton];
		for (int i = 0; i < numButton; i++){
			buttons[i] = new JButton("" + i);
			this.add(buttons[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameGUI newGame = new GameGUI();
		newGame.gameStart();
	}

}
