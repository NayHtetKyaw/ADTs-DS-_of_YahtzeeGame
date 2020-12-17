package DSAassignmentYahtzee;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import YahtzeeGame.Die;


public class OptionMenu implements YahtzeeGameInfo{

	private static final String Opt_Ee = null;
	
	
	
	Scanner reader = new Scanner(System.in);
	
	ArrayList<GameOption> optionlist;
	int space = 0;
	int playerScore = 0;
	Stack<Dies>dieNum;
	String categories = null;
	int rollLeft;
	
	

	//constructor
	public OptionMenu() {
		super();
		optionlist = new ArrayList();
		dieNum = new Stack();
		
	}
	
	//welcome menu
	public void gameMenu() {
		System.out.print("Welcome to Yahtzee Game Option Menu \n");
		System.out.println("___________________________________\n");
		System.out.println("Choose option 1: Display Yahtzee Game Description");
		System.out.println("Choose option 2: Roll Dice & choose Category / Add score");
		System.out.println("Choose option 3: Search your score by Category");
		System.out.println("Choose option 4: Review your score information");
		System.out.println("Choose option 5: Update Your Game Category");
		System.out.println("Choose option 6: Exit");
		choose();
	}
	
	public void gameInfo() {
		System.out.println("\n"+"Yahtzee is a dice game made by Milton"
				+ "Bradley (now owned by Hasbro), which was first marketed as "
				+ "Yatzie by the\nNational Association Service of Toledo Ohio,"
				+ "in the early 1940s. It was marketed under the name of Yahtzee\n"
				+ "by game entrepreneur Edwin S. Lowe in 1956.The game is a development"
				+ "of earlier dice games such as Poker Dice,\nYacht and Generala."
				+ "It is also similar to Yatzy, which is popular in Scandinavia.\n");
		
		System.out.println("How to play Step by Step Guide");
		System.out.println("____________________________\n");
		Array[] array = new Array[6];
		
		
			System.out.println("1 : Aces   - Sum of all dice number 1");
			System.out.println("2 : Twos   - Sum of all dice number 2");
			System.out.println("3 : Threes - Sum of all dice number 3");
			System.out.println("4 : Fours  - Sum of all dice number 4");
			System.out.println("5 : Fives  - Sum of all dice number 5");
			System.out.println("6 : Sixs   - Sum of all dice number 6");
			System.out.println("7 : ThreeKind - Sum of all dice with at lest 3 same dice number");
			System.out.println("8 : FourKind  - Sum of all dice with at lest 4 same dice number");
			System.out.println("9 : Yahtzee   - Sum of five same dice number");
			System.out.println("10: Chance    - Sum of all five dice with any number");
			
			System.out.println("____________________________\n");
			System.out.println("Step 1: Choose option 2 to start the game.");
			System.out.println("Step 2: Once the game is started first set of five dice will be rolled automatically.\n"+
								" Then if you like the numbers based on the category, Enter category name and add score");
			System.out.println("Step 3: The dice can be rolled 3 times per round and the game will end after choosing 10 category or rule.");
			System.out.println("Step 4: Choose Option 5: If you want to change the score in-case you accidenctly add wrong score, then");
			System.out.println("Step 5: Choose Option 3: If you can view you score by category");
			System.out.println("Step 6: Choose Option 4: If you want to view all your score in once and your Total score at the end of the game");
			System.out.println("Note: You can't choose the same category(rule) twice in a same(one) game");
				
	}
	
	
	public void categories() {
		
		rollLeft=2;
		if(!isFull()) {
			
			dieNum = new Stack<Dies>();
			dicRoll();
		    System.out.println();
			boolean checkCat = false;
			
			while(!checkCat) {
				
				System.out.print("Enter the Category Name :");
				categories = reader.next();			
				
				try {
					if(isCategories(categories)) {
						checkCat=true;
					}
				}catch (Exception e){
					System.out.println(e.getMessage());
				}	
				
				for(int i = 0; i< optionlist.size(); i++) {
						if(categories.equals(optionlist.get(i).categories)) {
							System.out.println("This Category '"+categories+"' is already used!!");
							if(doAnother()) {
								categories();
							}else choose();
						}
					}
				
				}
			
			}		
			boolean checkScore = false;
			while(!checkScore) {
				System.out.print("Enter the score :");
				playerScore = reader.nextInt();
				
				
				try {
					if(isScore(playerScore)) {
						checkScore=true;
					}
				}catch (Exception e){
					System.out.println(e.getMessage());
				}	
			}
			
			optionlist.add(new GameOption(categories,playerScore,dieNum.pop().diceNum));
			System.out.println("Your score is added by the category \n");
			space++;
			
			if(doAnother()) {
				categories();
			}else {
				choose();
			}
	
		}

	
	public void scoreSearch() {
		
		
		
		if(!isEmpty()) {
			System.out.print("Enter the category name to search the score :");
			String cat = reader.next();
			boolean found = false;
			
			for(GameOption itr : optionlist) {
				if(itr.getCategories().equals(cat)) {
					
					System.out.println();
					System.out.println("Your score on '"+cat+"' is "+itr.getScore()+".\tDice Numbers:"+ Arrays.toString(itr.diceNum) );
					found = true;
					break;
				}
			}if(!found) {
				System.out.println("You must have enter the wrong category name");
				System.out.println("Please Try again with the right name");
				scoreSearch();
			}
			
		}else {
			System.out.println("Score list is empty");
			gameMenu();
		}
		
	}

	
	
	public void PlayerScoreInfo() {
		
		System.out.println("Overall Score Informations by categories");
		System.out.println("________________________________________");
		System.out.println("Category \t"+"Score"+"\t\t"+"Dice Num");
		for(GameOption op:optionlist)
		{
			System.out.println(op.getCategories()+"\t\t"+op.getScore()+"\t\t"+Arrays.toString(op.getDiceNum()));
		}
		
		int total = 0;
		for(int i = 0; i < optionlist.size(); i++)
		   total = total + optionlist.get(i).playerScore;
		   System.out.println("_____________________________");
		   System.out.println("Your Total Score is "+total);
		   
		   if(doAnother()) {
			   choose();
		   }
		
		}



	public void updateCategories() {
		
		System.out.print("Which Category do you wish to update :");
		String catUpd = reader.next();
		
		int newScore = 0;
		boolean found = false;	
		for(int i=0; i<optionlist.size(); i++) {
			if(catUpd.equalsIgnoreCase(optionlist.get(i).categories)) {
				
				System.out.print("Enter the new score value for this category :");
				newScore = reader.nextInt();
				optionlist.get(i).playerScore = newScore;
				System.out.println("Your new Score on '"+catUpd+"' is '"+optionlist.get(i).playerScore);
				found = true;
				break;
				}
			  }
			if(!found) {
				System.out.println("Error 404 category not found");
				System.out.println("You must have enter the wrong category name!!");
				updateCategories();
			}
			if(doAnother()) {
				choose();
			}
		}

	
	public void exit() {
		System.out.println("GoodBye Have a nice day!!");
		
	}
	
	//options config 
	public void choose() {
		
		System.out.println();
		System.out.print("Choose Option :");
		String gameOption = reader.next();
		System.out.println();
		
		switch(gameOption) {
		case "1": gameInfo(); System.out.println(); break;
		case "2": categories(); System.out.println(); break;
		case "3": scoreSearch();  System.out.println(); break;
		case "4": PlayerScoreInfo(); System.out.println(); break;
		case "5": updateCategories(); System.out.println(); break;
		case "6": exit(); break;
		
		default :System.out.println("Invalid Input!! Please Try Again \n");
		
		}
		if(doAnother()) {
			gameMenu();
		}
		
		
	}


	private boolean doAnother() {
		
		System.out.println("Do you want to continue!?");
		System.out.print("Yes OR No : ");
		String doAnother = reader.next();
		
		if(doAnother.equalsIgnoreCase("Yes")) {
			System.out.println(); 
			return true; 
			
		}else if(doAnother.equalsIgnoreCase("No")){
			return false;
		}else
			System.out.println("Invalid Input!");
			doAnother();
		return false;
		
	}
	
	//stack operation methods
		public boolean isFull() {
			return space == 5;
		}
		
		public boolean isEmpty() {
			return space == 0;
		}
		
		public int freeSpace(int size) {
			return space-size;
		}
		
		//input valid type check 
		public boolean isCategories(String cat) throws Exception{
			if((Pattern.matches("^[A-Za-z]*$", cat))){
				return true;
			}else
				throw new Exception("Inputted Categories Type or Format is incorrect!! Please Try Again \n");
			
		}
		
		public boolean isScore(int playerScore) throws Exception{
			
			String pScore = Integer.toString(playerScore);
			if((Pattern.matches("^([1-9]|[12][0-9]|3[0-2][0-9])", pScore))){
				return true;
			
			}else if((Pattern.matches("[A-Za-z]$*", pScore))){
				throw new Exception("Score can't be character value! Invlid Input !Please Try Again!");
				
			}
			else
			
				throw new Exception("Input Score format is incorrect or Invalid Input!  "+"Please Try Again");
				
		};
		
		public void dicRoll() {
			
	        int [] diceNum = new int[5];	
	        Random rand = new Random();
	        
	        for (int i = 0; i < 5; i++) {
	     
	        	int die = rand.nextInt(6)+1;
	        	diceNum[i] = die;
	        	System.out.print(diceNum[i]+"   ");	
	        	
	        }
	        dieNum.push(new Dies(diceNum));
	        reRoll();
	        	
	      };
	       
		public void reRoll() {
				for(int i =0; i<rollLeft; i++) {
	        	
	        	System.out.println();
	        	System.out.println("Roll left = "+rollLeft);
	        	System.out.print("Do you wish to Roll again? : ");
	        	String rollAgain = reader.next();
	        	
	        	if(rollAgain.equalsIgnoreCase("Yes")) {
	        		rollLeft--;
	        		dicRoll();
	        	}else {
	        		break;
	        	}
			}
		};
		
	}
