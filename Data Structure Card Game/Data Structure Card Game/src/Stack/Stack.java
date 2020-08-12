/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Stack;

import Model.Card;

public class Stack {
	int top=0;
	Card stack[] = new Card[52];
	
	public Stack() {
		top=0;
	}

	public void Push(Card data) {
		stack[top]=data;
		top++;
//		System.out.println(top);
		//Push... you take the data and you put it at the top of the stack..
		//. then you move the pointer for the top by 1 hence top++
		
	}

	
	public Card Pop() {
		Card data;	
		this.top--;
		 
		data= stack[top];
		stack[top]= null; 
		
		return data;
		
	}

	public Card Peek() {
		Card data=new Card();	 
		 
//		System.out.println(top);
		data= stack[top--]; 
		
		return data;
		
		
	}
	
	public void Display() {
		for(Card x: stack) {
			if(x!=null)x.Display();
			//for each card in stack if its not empty you display it
		}
	}

	public int getTop() {
		return top;	
	}

	public void setTop(int top) {
		this.top = top;
	}

	public Card[] getStack() {
		return stack;
	}

	public void setStack(Card[] stack) {
		this.stack = stack;
	}
	
	
}
