/*Shemar Williams ID#: 1704317
Dana-Lee Powell  ID#: 1800995
Kimani Groucher ID#: 1803859
Matthew Ferguson  ID#: 1704735 */


package Queue;

import Model.Card;

public class QueueNode {
	private Card data;
	private QueueNode prevNode; 
	private QueueNode  nextNode;

	public QueueNode() { 
		this.data =null; 
		prevNode = null; 
		nextNode = null; 
		}
	
	public QueueNode(Card data){
		this.data=data;
		this.prevNode=null;
		this.nextNode=null;
		}

	public Card getData() {
		return data;
	}

	public void setData(Card data) {
		this.data = data;
	}

	public QueueNode getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(QueueNode prevNode) {
		this.prevNode = prevNode;
	}

	public QueueNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(QueueNode nextNode) {
		this.nextNode = nextNode;
	}
	
	

}
