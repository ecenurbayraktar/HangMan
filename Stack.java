package hangman;

public class Stack {
	private int top;
	private Object[] elements;
	
	Stack(int capacity){
		top=-1;
		elements=new Object[capacity];
	}
	void Push(Object data) {
		if(isFull()) {
			System.out.println("Stack is full");
		}
		else {
			top++;
			elements[top]=data;
		}
	}
	Object Pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		else {
			Object retData=elements[top];
			top--;
			return retData;
		}
	}
	Object Peek() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		else {
			return elements[top];
		}
	}
	boolean isFull() {
		return (top+1==elements.length);
	}
	boolean isEmpty() {
		return (top==-1);
	}
	int size() {
		return (top+1);
	}
	
	
	

}
