package DSAassignmentYahtzee;

import YahtzeeGame.Die;

public class GameOption {
	
	String categories;
	int playerScore;
    public int[] diceNum;
	
	public int[] getDiceNum() {
		return diceNum;
	}


	public void setDiceNum(int[] diceNum) {
		this.diceNum = diceNum;
	}


	public String getCategories() {
		return categories;
	}
	
	
	public void setCategories(String categories) {
		this.categories = categories;
	}
	
	public int getScore() {
		return playerScore;
	}
	
	public void setScore(int score) {
		this.playerScore = score;
	}
	
	public GameOption(String categories,int score,int[] dies) {
		super();
		this.categories = categories;
		this.playerScore = score;
		this.diceNum = dies;
	}	
}
