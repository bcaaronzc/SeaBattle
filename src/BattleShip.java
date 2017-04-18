public class BattleShip {
	int size;
	int loc[];
	boolean isVertical;	// true when vertical, false when horizontal
	int hitNum = 0;
	boolean isSink = false;
	
	BattleShip(int initSize, int[] initLoc, boolean initIsVertical){
		size = initSize;
		loc = initLoc;
		isVertical = initIsVertical;
	}
	
	public void gotHit(){
		hitNum++;
		isSink = checkSink();
	}
	
	public boolean checkSink(){
		if (hitNum >= size){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
