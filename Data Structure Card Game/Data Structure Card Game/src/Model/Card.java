/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Model;


public class Card {
	private int suit; 
	private int value;
	private boolean match;


	public Card(int suit,  int value) {

		this.suit = suit; 
		this.value = value;
		this.setMatch(false);
	}

	public Card() {

		this.suit = 0; 
		this.value = 0;
		this.setMatch(false);
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public boolean isMatch() {
		return match;
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void Display() {
		System.out.println(this.getCardName()); 
	}

	//Assigning the suits and values base on inputs.
	public String getCardName()	{ 
		String name = "";

		if(value > 10)
		{
			switch(value)
			{
			case 11:
				name = "11) Jack of ";
				break;
			case 12:
				name = "12) Queen of ";
				break;
			case 13:
				name = "13) King of ";
				break;
			}
			//	         name =royalValue;
		}
		else if(value == 1)
		{
			name = "1) Ace of ";
		}else

			name = value + " of ";


		switch(suit)
		{
		case 1:
			name += "Clubs";
			break;
		case 2:
			name += "Diamonds";
			break;
		case 3:
			name += "Hearts";
			break;
		case 4:
			name += "Spades";
			break;

		}

		return name;
	}

}
