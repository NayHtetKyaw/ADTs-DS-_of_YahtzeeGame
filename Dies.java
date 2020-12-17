package DSAassignmentYahtzee;

public class Dies {
	public int[] diceNum;
	
	public int[] getDiceNum(){
		return diceNum;
		
	}
	
	public void setDiceNum(int[] diceNum) {
		this.diceNum = diceNum;
	}
	
	public Dies(int[] diceNum) {
		super();
		this.diceNum = diceNum;
	}
}
