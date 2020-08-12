/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Model;

import Queue.Queue;

public class Player {
	private String name;
	private Queue hand;
	private int pairs;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Queue getHand() {
		return hand;
	}
	public void setHand(Queue hand) {
		this.hand = hand;
	}
	public int getPairs() {
		return pairs;
	}
	public void setPairs(int pairs) {
		this.pairs = pairs;
	}


	public void Display() {
		System.out.println("\nName: " + name + "\nPairs: " + pairs+"\n---------");

	}




}
