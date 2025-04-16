package hangman;

public class CircularQueue {
	private int front,rear;
	private Object[] elements;
	
	CircularQueue(int capacity){
		front=0;
		rear=-1;
		elements=new Object[capacity];
	}
	boolean isFull() {
		return (rear+1)%elements.length==front&&elements[front]!=null&&elements[rear]!=null;
	}
	boolean isEmpty() {
		return elements[front]==null;
	}
	void enqueue(Object data) {
		if(isFull()) {
			System.out.println("queue is full");
		}
		else {
			rear=(rear+1)%elements.length;
			elements[rear]=data;
		}
	}
	Object dequeue() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}
		else {
			Object retdata=elements[front];
			elements[front]=null;
			front=(front+1)%elements.length;
			return retdata;
		}
	}
	Object peek() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}
		else {
			return elements[front];
		}
	}
	int size() {
		if(rear>=front) {
			return rear-front+1;
		}
		else if(elements[front]!=null) {
			return elements.length-(front-rear)+1;
		}
		else
			return 0;
	}

}
