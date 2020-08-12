/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Model;

import java.util.Random;

import Queue.Queue;
import Stack.Stack;

public class Deck {
	int deckLimit=52;



	public Deck() {
		super();

	}

	public Card[] Shuffle(Card[] deck, int size) {
		Random random = new Random();

		for (int i=0; i<size; i++) {
			int RPos = random.nextInt(size);
			Card temp = deck[i];
			deck[i]= deck[RPos];
			deck[RPos] = temp;

		}

		return deck;

	}

	public Player[]  Deal(Stack deck) {

		Player[] players= new Player[2];
		Queue p1Hand= new Queue();
		Queue cpuHand= new Queue();

		players[0]= new Player();
		players[1]= new Player();


		for(int i=0; i<8; i++) {
			if(i%2==0) {
				//				System.out.println("-----PLAYER 1 CARD----");
				//				System.out.println(i);
				//				deck.Peek().Display();
				p1Hand.Enqueue(deck.Pop()); 
				//				System.out.println("-----------------");
			}else {
				//				System.out.println("-----CPU CARD----");
				//				System.out.println(i);
				//				deck.Peek().Display();
				cpuHand.Enqueue(deck.Pop());

				//				System.out.println("-----------------");
			}

		}
		//		cpuHand.ShowHand();
		//		System.out.println("-----------------");
		//		p1Hand.ShowHand();
		//		System.out.println("-----------------");
		//		deck.Display();
		players[0].setHand(p1Hand);
		players[1].setHand(cpuHand);

		return players;

	}

	public Queue  FillHand(Stack deck) {


		Queue hand= new Queue();

		if(deck.getTop()>=4) {
			for(int i=0; i<4;i++) {
				hand.Enqueue(deck.Pop());
			}
		}else {
			for(int i=0; i<deck.getTop();i++) {
				hand.Enqueue(deck.Pop());
			}
		}



		return hand;

	}



	public Stack FillDeck() {
		Card card = new Card();
		Card[] pack = new Card[52];
		Stack deck = new Stack();
		int num=0;

		for(int i = 1; i <= deckLimit/13	; i++)
		{
			for(int j = 1; j <= deckLimit/4; j++)
			{
				card = new Card(i, j); 
				pack[num]=card;
				num++;
			}
		}

		pack=this.Shuffle(pack, pack.length);

		for (Card x:pack) { 
			deck.Push(x); 
		}

		return deck;

	}

}
