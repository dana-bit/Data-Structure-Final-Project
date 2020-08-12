/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


import java.util.Scanner;

import Model.Card;
import Model.Deck;
import Model.Player;
import Stack.Stack;

import java.io.IOException;
import java.util.Random;

public class GoFish {
	Player[] players = new Player[2];
	Card [] pack = new Card[52];
	Deck deckCTRL = new Deck();
	Stack deck  = new Stack();
	Player player1= new Player();
	Player cpu= new Player();
	Scanner sc = new Scanner(System.in);
	String dealer;
	boolean win=false; 
	String[] commend = {"Great job","Nice work","Congrats","Well Done"};
	String[] failed = {"Better luck next time!","Sorry!"};
	Random random = new Random();

	public void StartGame() {

		deck = deckCTRL.FillDeck();//Creates a shuffled deck of cards.

		players = deckCTRL.Deal(deck); //Initialize an array with two players 
		
		//Assigning players individually 
		player1= players[0];
		cpu= players[1]; 
		
		
		this.CoinToss();
		 
		//Program will run until the game is won 
		while(!win) {
			
			cpu.setPairs(cpu.getHand().CheckForPairs("Cpu")+cpu.getPairs());
			player1.setPairs(player1.getHand().CheckForPairs(player1.getName())+player1.getPairs());
			
			//Re-fill CPU's deck 
			if(cpu.getHand().allPaired())
				cpu.setHand(deckCTRL.FillHand(deck));
			
			//refill Player's deck
			if(player1.getHand().allPaired())
				player1.setHand(deckCTRL.FillHand(deck));
			
			if(!dealer.equals("Cpu")) {
				PlayerOneTurn();
				checkForGameOver();

			}else { 
				CPUTurn();
				checkForGameOver();
			}

		}
		if(win) {
			DisplayWinner();
		}
			
	}

	public void PlayerOneTurn()
	{
		boolean nextChance = false;
		boolean found;
		boolean hit;
		int value;
		do
		{  
			nextChance = false;
			if(!win)
				
			{
				//Checking for pairs add incrementing the player's point
				System.out.println("\n\tYour Turn");
				
				
				player1.getHand().ShowHand();
				
				System.out.println("Which value would you like to ask for? ");
				value = sc.nextInt();
				
				//Validation for input greater than 13
				while(value<1 || value>13) {
					 System.out.println("Invalid Input");
					 System.out.println("Enter another value: ");
			            value = sc.nextInt();
			           
				}

				//check if the user has the value that he/she is asking for 
				found= player1.getHand().FindValueInHand(value, false);

				//Validate the user input until he/she enters a value contained in their hand.
				while(!found) {
					System.out.println("\nSorry, that value is not contained in your hand!");
					System.out.println("Enter a next value: ");
					value = sc.nextInt();
					
					//Validation for input less than 1 and greater than 13 
					while(value<1 || value>13) {
					 
						 System.out.println("Invalid Input");
						 System.out.println("Enter another value: ");
						 value = sc.nextInt();
				        
				           
					}
					found= player1.getHand().FindValueInHand(value, false);
				}

				//A hit indicates that the value that the player asked for is in both hands
				hit=cpu.getHand().FindValueInHand(value, false);

				if(hit) {
					System.out.println("\n::: "+commend[random.nextInt(4)]+" "+player1.getName()+", you got a point!!. :::");
				
					//Mark value as paired in player's hand 
					player1.getHand().FindValueInHand(value, true);
					
					//Mark value as paired in Cpu's hand 
					cpu.getHand().FindValueInHand(value, true);
					
					nextChance=true;//Gives the player a next chance 
					
					System.out.println("\nGo Again!.\n");
					
					//Increments the player's point
					player1.setPairs(player1.getPairs()+1);
					
					//Refill Both player's hand when all the values are paired
					if(cpu.getHand().allPaired())
						cpu.setHand(deckCTRL.FillHand(deck));
					if(player1.getHand().allPaired())
						player1.setHand(deckCTRL.FillHand(deck));
					
					checkForGameOver();
					
				}else {
					System.out.println("\n"+failed[random.nextInt(2)]+", Go Fish!");       	
					nextChance=false; 
					
					//Gives the other player a turn 
					dealer=cpu.getName();
					
					////// Displays the card the user got from the deck 
					Card fromDeck = deck.Pop();
					
					if(fromDeck.getValue()>10 || fromDeck.getValue()==1)	
						System.out.println("\nYou got '"+fromDeck.getCardName().substring(3, fromDeck.getCardName().length())+"' from the deck.\n");
					else
						System.out.println("\nYou got '"+fromDeck.getCardName()+"' from the deck.\n");
					
					player1.getHand().Enqueue(fromDeck); 
					/////

				}
 
				//Display both player points
				System.out.println("\n"+player1.getName()+"'s total points: "+player1.getPairs());
				System.out.println("Cpu's total points: "+cpu.getPairs());
				pause();

			}
		}while(nextChance);
	}

	public void CPUTurn()
	{
		boolean nextChance = false; 
		boolean hit;
		int value;
		do
		{  
			nextChance = false;
		

			if(!win)
			{   
				System.out.println("\n\t\tCPU Turn"); 
			
				//Retrieve a random card from the Cpu's hand
				value=cpu.getHand().GetRandomValue();
				
				while(value==0)
					value=cpu.getHand().GetRandomValue();
						
				System.out.println("The cpu ask for the value: "+value);

				hit=player1.getHand().FindValueInHand(value, false);

				if(hit) {
					System.out.println("\n::: Cpu got a point. :::");
					player1.getHand().FindValueInHand(value, true);
					cpu.getHand().FindValueInHand(value, true);
					nextChance=true;
					System.out.println("\n\t\tCPU will go again!.\n");
					cpu.setPairs(cpu.getPairs()+1);
					
					if(cpu.getHand().allPaired())
						cpu.setHand(deckCTRL.FillHand(deck));
					if(player1.getHand().allPaired())
						player1.setHand(deckCTRL.FillHand(deck));
					
					pause();
					checkForGameOver();

				}else {
					System.out.println("\n\tCpu will Go Fish!");       	
					nextChance=false; 
					dealer=player1.getName();
					cpu.getHand().Enqueue(deck.Pop());
				} 

			}
		}while(nextChance);

		pause();
	}



	public void CoinToss() {
		System.out.println("\t\t*****************************\n");
		System.out.println("\t\t***   W E L C O M E  T O  ***\n");
		System.out.println("\t\t***     KDMS Go Fish      ***\n");
		System.out.println("\t\t***    Gaming System      ***\n");
		System.out.println("\t\t*****************************\n");
		
		System.out.println("\nPlease enter your name: ");
		player1.setName(sc.next());
		cpu.setName("Cpu");
		String coin; 
		int flipNum;

		System.out.println("\n\t\t***Coin Toss***\n");
		System.out.println("Welcome "+player1.getName()+", please press (H) for head or (T) for tail: ");
		coin=sc.next();
		while(!coin.toUpperCase().equals("H") && !coin.toUpperCase().equals("T")) { //toupper converts to uppercase
			System.out.println("Invalid Input");
			System.out.println("Please press (H) for head or (T) for tail:  ");
			coin=sc.next();
		}


		flipNum=random.nextInt(2);

		if(flipNum==0 && coin.toUpperCase().equals("H") || flipNum==1 && coin.toUpperCase().equals("T")) {//0 represent head and 1 represent tail

			System.out.println("You won the toss\n");
			System.out.println("Do you want to go first Y/N: ");
			dealer=sc.next();

			if(dealer.toUpperCase().equals("Y")) {
				dealer=player1.getName();
			}else {
				dealer=cpu.getName();
			}

		}else {


			System.out.println("You loss the toss\n");
			System.out.println("\t\tCPU will go first!");
			dealer=cpu.getName();
			pause();

		}

	}


	public void pause()
	{
		System.out.println("\nPress Enter to continue..");
		try {
			System.in.read();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		sc.nextLine();
	}

	public void DisplayWinner(){
		String action="";
		if(player1.getPairs()>cpu.getPairs()) {
			System.out.println("\t\t\nCongratulations "+player1.getName()+", you won!!!");
		}else if(player1.getPairs()<cpu.getPairs()) {
			System.out.println("\t\t\nSorry "+player1.getName()+", Cpu won! ");
		}else {
			System.out.println("\t\t\nMatch is a draw, you did great "+player1.getName());
		}
		
		System.out.println("\nDo you wish to play again?  (Y/N)");
		action=sc.next();
		while(!action.toUpperCase().equals("Y") && !action.toUpperCase().equals("N")) {
			System.out.println("Invalid input Y-yes / N-no: ");
			action=sc.next();
		} 
		if(action.toUpperCase().equals("Y")) {
			GoFish game = new GoFish();
			game.StartGame();
		}else {
			System.out.println("\nGo Bye!\nPress any key to exit");	
			try {
				System.in.read();
			} catch (IOException e) { 
				e.printStackTrace();
			}
			System.exit(0);
		}
	}


	public void checkForGameOver()
	{
		win = (deck.getTop() == 0 || player1.getHand().allPaired()
				|| cpu.getHand().allPaired());
	}

}
