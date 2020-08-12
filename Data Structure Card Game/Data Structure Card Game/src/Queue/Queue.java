/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Queue;

import java.util.Random;

import Model.Card;

public class Queue  {

	private QueueNode front;
	private QueueNode rear;

	public Queue() {
		this.front=null;
		this.rear=null;
	}

	public Queue(Queue queue) {
		this.front=queue.front;
		this.rear=queue.rear;
	}

	public QueueNode getFront() {
		return front;
	}

	public void setFront(QueueNode front) {
		this.front = front;
	}

	public QueueNode getRear() {
		return rear;
	}

	public void setRear(QueueNode rear) {
		this.rear = rear;
	}

	public boolean isEmpty() { 
	return front==null;	
	}
	
	public boolean allPaired() {
		QueueNode temp=this.front; 
		 
		while(temp.getNextNode()!=null ){

			if(!temp.getData().isMatch())
				return false;
			
			temp=temp.getNextNode(); 
		}

		return true;
	}

	//Ask why
	//Adds card to the back of the queue.
	public void Enqueue(Card data) {
		QueueNode temp = new QueueNode(data); 

		if (temp!=null) {

			if(isEmpty()) {
				front = temp; 
				rear = temp;
			}else {
				rear.setNextNode(temp);
				temp.setPrevNode(rear); 
				rear = temp;

			}
		}
	}

	//why was a do while used instead of a while loop
	//Displays user's hand 
	public void ShowHand() {

		QueueNode temp=this.front; 

		System.out.println("\n\tYour Hand");
			do{
	
				if(!temp.getData().isMatch())
					System.out.println("\t\t"+temp.getData().getCardName());
				
				temp=temp.getNextNode(); 
			}while(temp!=null );
		System.out.println("\t\t-------------");
	}

	//Remove card from the front of the queue.
	public QueueNode Dequeue() {
		QueueNode temp=front; 

		if (!isEmpty()) { 
			front=front.getNextNode();
		}
		return temp;
	}


	//Check if there are any pairs in hand after go fish or at the beginning of a game.
	public int CheckForPairs(String name) {
		boolean pairFound=false;
		int pair=0; 
		QueueNode check=this.getFront().getNextNode();
		QueueNode temp=this.Dequeue();

		for(int i=0; i<this.getCount(); i++) {
			while(check!=null){ 
				if(check.getData().getValue()==temp.getData().getValue() ) {
					if(!check.getData().isMatch() && !temp.getData().isMatch() ) { //ignore this if
						
						temp.getData().setMatch(true);
						check.getData().setMatch(true);

						if(!name.equals("Cpu") && temp.getData().getValue()<11 || temp.getData().getValue()!=1)	
							System.out.println("\tYou pulled a pair of '"+temp.getData().getValue()+"' from your hand.");
						else if( name.equals("Cpu") && temp.getData().getValue()<11 || temp.getData().getValue()!=1)
							System.out.println("\tCpu pulled a pair of '"+temp.getData().getValue()+"' from it's hand");

						this.Dequeue();

						if(temp.getNextNode()!=null)
							temp=temp.getNextNode();
						else
							temp=null;

						pair++; 
					}
				}
				check= check.getNextNode();
			}

			if(!pairFound) {
				this.Enqueue(temp.getData());
			}
			check=this.getFront().getNextNode();
			temp=this.Dequeue();

		}
		if(!pairFound) {
			this.Enqueue(temp.getData());
		}
		return pair;
	}

	//Select a random card from the Cpu's hand. 
	public int GetRandomValue() {
		int value=0;
		QueueNode temp=front; 
		int random = new Random().nextInt(this.getCount());
		int i=0;

		
		while(temp!=null){			 
			if(!temp.getData().isMatch() && i==random) {

				value=temp.getData().getValue();
				break;
			}
			temp= temp.getNextNode();
			i++;
		}
		if(value==0 && !front.getData().isMatch())
			return front.getData().getValue();
		 

		
		return value;
	}

	//Find values in hand after a request from another player.
	public boolean FindValueInHand(int value, boolean confirm) {
		QueueNode temp=front;
		boolean found=false;

		while(temp!=null){ 
			if(temp.getData().getValue()==value ) {


				if(!temp.getData().isMatch() ) {

					if(confirm)
						temp.getData().setMatch(true); 

					found=true;
					break;
				}
			}
			temp= temp.getNextNode();
		}
		return found;
	}
	
	//returns total card in hand
	public int getCount() {
		int count=0;
		QueueNode temp=this.getFront();

		while(temp!=null ){ 
			count++;
			temp= temp.getNextNode();
		}	
		return count;
	}










}
