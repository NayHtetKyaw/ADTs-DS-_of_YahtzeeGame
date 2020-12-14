package DSAassignmentYahtzee;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import YahtzeeGame.Die;


public class OptionMenu implements YahtzeeGameInfo{

	private static final String Opt_Ee = null;
	
	
	
	Scanner reader = new Scanner(System.in);
	
	ArrayList<GameOption> optionlist;
	int space = 0;
	int playerScore = 0;
	int[] diceNum = null;
	String categories = null;
	int rollLeft = 2;
	

	//constructor
	public OptionMenu() {
		super();
		optionlist = new ArrayList();
	}
	
	//welcome menu
	public void gameMenu() {
		System.out.print("Welcome to Yahtzee Game Option Menu \n");
		System.out.println("___________________________________\n");
		System.out.println("Choose option 1: Display Yahtzee Game Description");
		System.out.println("Choose option 2: Choose Category and Add score");
		System.out.println("Choose option 3: Search your score by Category");
		System.out.println("Choose option 4: Review your score information");
		System.out.println("Choose option 5: Update Your Game Category");
		System.out.println("Choose option 6: Exit");
		choose();
	}
	
	public void gameInfo() {
		System.out.println("\n"+"Yahtzee is a dice game made by Milton"
				+ "Bradley (now owned by Hasbro), which was first marketed as "
				+ "Yatzie by the National Association Service\nof Toledo Ohio,"
				+ "in the early 1940s. It was marketed under the name of Yahtzee"
				+ "by game entrepreneur Edwin S. Lowe in 1956.\nThe game is a development"
				+ "of earlier dice games such as Poker Dice, Yacht and Generala."
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
		
		
		
		
	}
	
	
	public void categories() {
		

		if(!isFull()) {
			
			
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
							doAnother();
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
			
			
			
			
			optionlist.add(new GameOption(categories,playerScore,diceNum));
			System.out.println("Your score is added by the category \n");
			space++;
			
			if(doAnother()) {
				categories();
			}
	
		}

	
	public void scoreSearch() {
		
		
		
		if(!isEmpty()) {
			System.out.print("Enter the category name to search the score :");
			String cat = reader.next();
			boolean found = false;
			
			for(GameOption itr : optionlist) {
				if(itr.getCategories().equals(cat)) {
					
					System.out.println("Your score on "+cat+" is "+itr.getScore());
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
		for(GameOption itr : optionlist) {
				 HashSet<GameOption> opt = new HashSet<GameOption>(); 
				   
			       System.out.println(itr.getCategories() +"\t\t"+ itr.getScore()+"\t\t"+itr.diceNum); 
			      
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
				System.out.println("Enter the new score value for this category :");
				newScore = reader.nextInt();
				optionlist.get(i).playerScore = newScore;
				found = true;
				break;
				}
			  }
			if(!found) {
				System.out.println("Error 404 category not found");
				System.out.println("You must have enter the wrong category name!!");
				updateCategories();
			}
			doAnother();
		}

	
	public void exit() {
		System.out.println("GoodBye Have a nice day!!");
		
	}
	
	//options config 
	public void choose() {
		
		System.out.println();
		System.out.print("Choose Option :");
		String gameOption = reader.next();
		
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
			
		}else if(doAnother.equalsIgnoreCase("No")) {
			exit();
		}
		else
			System.out.println("Invalid Input! Please Type correctly");
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
				throw new Exception("Inputed Categories Type or Format is incorrect!! Please Try Again \n");
			
		}
		
		public boolean isScore(int playerScore) throws Exception{
			
			String pScore = Integer.toString(playerScore);
			if((Pattern.matches("^([1-9]|[12][0-9]|3[0-2])", pScore))){
				return true;
			}
			else
			
				throw new Exception("Score method is incorrect !  "+"Please Try Again");
				
		};
		
		public void dicRoll() {
			
	        int [] diceNum = null;
	        int[] dieNum = new int[5];	
	        Random rand = new Random();
	        
	        for (int i = 0; i < 5; i++) {
	     
	        	int die = rand.nextInt(6)+1;
	        	dieNum[i] = die;
	        	System.out.print(dieNum[i]+"   ");
	        	 
	        }	
//	        diceNum[optionlist.get(i)] = dieNum[0];
//	        ;
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
	        
		}
	}
