package hangman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class hangmantest {
	
	
	public static boolean IsJokerUsedBefore(int joker) { //checks the joker if it used before
		boolean flag;
		if(joker==0) {
			flag=false;
		}
		else {
			flag=true;
		}
		return flag;
		
	}
	
	public static Stack cloneStack(Stack stack){  //prevent the stack goes reverse
        Stack tempStack = new Stack(100);
        Stack returnStack = new Stack(100);
        while(!(stack.isEmpty())) {
            tempStack.Push(stack.Pop());
        }
        while(!(tempStack.isEmpty())) {
            Object obj = tempStack.Pop();
            stack.Push(obj);
            returnStack.Push(obj);
        }
        return returnStack;
    }
	public static String JokerLetter(Stack boardStack,Stack wordStack) { //chooses the joker letter
		Stack temp=new Stack(100);
		int n=-1;
		while(!boardStack.isEmpty()) {
			n++;
			String str=boardStack.Pop().toString();
			temp.Push(str);
			if(str.equals("_")) {
				break;
			}
		}
		while(!temp.isEmpty()) {
			boardStack.Push(temp.Pop());
		}
		for(int i=0;i<n;i++) {
			temp.Push(wordStack.Pop());
		}
		
		String S=wordStack.Peek().toString();
		while(!temp.isEmpty()) {
			wordStack.Push(temp.Pop());
		}
		return S;
		
		
		
	}
	
	  public static void printStack(Stack stack, String seperator){
	        Stack tempStack = cloneStack(stack);
	        //printing the stack
	        while(!(tempStack.isEmpty())) {
	            System.out.print(tempStack.Pop() + seperator);
	        }
	    }
	  
	  
	  public static boolean playAgainIsTrue() { //ask the user if she wants to play again
	        Scanner input = new Scanner(System.in);
	        System.out.println("Play Again?");
	        String guess = "";
	        while (true) {
	            guess = input.nextLine().toUpperCase();
	            if (guess.startsWith("N")){
	                return false;
	            }else if (guess.startsWith("Y")){
	                return true;
	            }else{
	                System.out.println("Please enter Y or N");
	            }
	        }
	    }
	
	public static boolean ContainstheLetter(Stack s,String str) { //checks if searching word contains entered letter it helps to provide enter same letter again
		Stack temp=new Stack(100);
		boolean contains=false;
		while(!s.isEmpty()) {
			String top=(String)s.Peek();
			temp.Push(s.Pop());
			if(top.equals(str)) {
				contains=true;
				break;
			}
		}
		while(!temp.isEmpty()) {
			s.Push(temp.Pop());
		}
		return contains;
	}
	public static Stack placeLetterToBoardStack(Stack boardStack, Stack wordStack, String letter){ //makes the contain letter places to board
        Stack tempWordStack = cloneStack(wordStack);
        Stack tempBoardStack = cloneStack(boardStack);
        Stack newBoardStack = new Stack(100);
        Stack newBoardStackReverse  = new Stack(100);
        while (!tempWordStack.isEmpty()){
            String wordLetter = tempWordStack.Pop().toString();
            String boardLetter = tempBoardStack.Pop().toString();
            if (wordLetter.equals(letter)){
                newBoardStack.Push(wordLetter);
            }else {
                if (boardLetter.equals("-")){
                    newBoardStack.Push("-");
                }else {
                    newBoardStack.Push(boardLetter);
                }

            }
        }
        while (!newBoardStack.isEmpty()){
            newBoardStackReverse.Push(newBoardStack.Pop());
        }
        return newBoardStackReverse;


    }

	public static void main(String[] args) throws IOException {
		//please enter your file path here 
		File animal=new File("C:\\animals.txt");
		Stack AnimalStack=new Stack(14);
		Stack tempanimal=new Stack(14);

		try {
			//Reading file
			Scanner read=new Scanner(animal);
			//Reads file until there is no other line
			while(read.hasNextLine()) { 
				String data=read.nextLine();
				tempanimal.Push(data);				
			}
			read.close();
			while (!tempanimal.isEmpty()){  
				AnimalStack.Push(tempanimal.Pop());
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//your highscoretable text file creating into this path 
		File scoretable =new File("C:\\highscoretable.txt");
		try {
			if(scoretable.createNewFile()) {
				System.out.println(" ");
				
			}
			else {
				System.out.println(" ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		CircularQueue QName=new CircularQueue(12);
		CircularQueue QScore=new CircularQueue(12);
		
		
		
		
		
		
		do {
			int jokercount=0;

			Stack LetterStack=new Stack(100);
			//pushing characters into stack by their integer values
			for(int i=90;i>=65;i--) {
				char ch=(char)i;
				String l=String.valueOf(ch);
				LetterStack.Push(l);
			}
			Random random=new Random();
			int n=random.nextInt(15);
		
			String searching;
			//finds the random word from animal stack
			for(int i=1;i<n;i++) {
				tempanimal.Push(AnimalStack.Pop());
			}
			searching=AnimalStack.Peek().toString();
			//putting back
			while(!tempanimal.isEmpty()) {
				AnimalStack.Push(tempanimal.Pop());
			}
			int point=120;
			Stack WordStack=new Stack(searching.length());
			Stack BoardStack=new Stack(searching.length());
			//creating word and board stack
			for(int i=searching.length()-1;i>=0;i--) {
				WordStack.Push(searching.charAt(i));
			}
			for(int i=searching.length()-1;i>=0;i--) {
				BoardStack.Push("_");
			}
			Stack MissingLetter=new Stack(100);
			Stack guessedletters=new Stack(100);
			Stack q=new Stack(100);
			Stack qq=new Stack(100);
			Stack t=new Stack(100);
			Stack tempmiss=new Stack(100);
			

			while(point>0&&ContainstheLetter(BoardStack,"_")) {
				//game screen
				System.out.println("Word= ");
				printStack(BoardStack," ");
				System.out.print("\tMissed Word(s)= ");
				printStack(MissingLetter," ");
				System.out.print("\t\tPoint= "+point+"\t\t");
				printStack(LetterStack," ");
				System.out.println();
				
				
				Scanner input=new Scanner(System.in);
				System.out.print("Enter your guess: ");
				String guess=input.nextLine().toUpperCase();
				//a boolean variable for checking the guess contains the letter
				boolean IsContains=searching.contains(guess);
				
				if(guess.equals(" ")||guess.equals("") || guess.equals("1")||guess.equals("2")||guess.equals("3")||guess.equals("4")||
                        guess.equals("5")||guess.equals("6")||guess.equals("7")||guess.equals("8")||
                        guess.equals("9")||guess.equals("0")||guess.equals("?")||guess.equals("!")) {
                    System.out.println("Please enter a  letter.");
                }
				else if (guess.equals("JOKER")){
					if(IsJokerUsedBefore(jokercount)) {
						System.out.println("You used Joker before.");
					}
					else {
						String S=JokerLetter(BoardStack,WordStack);
	                	 BoardStack = placeLetterToBoardStack(BoardStack, WordStack,S );
	                	 jokercount++;
					}
                	
                	 

                }
                else {
                	 String  letter =  guess.substring(0,1); //make sure that guess has 1 length
                	 
                     if (ContainstheLetter(guessedletters, letter)){
                         System.out.println("You entered the same letter before.");
                     }
                     else {
                    	 if(IsContains) {
                    		 if(guess.equals((String)LetterStack.Peek())) {
             					LetterStack.Pop();
             					}
                    		 else {
                    			 //takes the letter and pop it then make the stack sorted by alphabet rules
                    			 while(!guess.equals((String)LetterStack.Peek())) {
             						String top=(String)LetterStack.Peek();
             						LetterStack.Pop();
             						qq.Push(top);
             					}
             					while(!qq.isEmpty()) {
             						
             						q.Push(qq.Pop());
             					}
             					LetterStack.Pop();
             					while(!q.isEmpty()) {
             						qq.Push(q.Pop());
             					}
             					while(!qq.isEmpty()) {
             						LetterStack.Push(qq.Pop());
             					}

                    		 
                    		 }
                             BoardStack = placeLetterToBoardStack(BoardStack, WordStack, letter);
                             printStack(BoardStack, " ");


                    	 }
                    	 else {
                    		 MissingLetter.Push(guess);
             				//decrease the point
             				if(guess.equals("A")||guess.equals("E")||guess.equals("I")||guess.equals("O")||guess.equals("U")) {
             					point=point-15;
             				}
             				else {
             					point=point-20;
             				}
             				
             				
             				//pop the letter that entered and make the letterstack by alphabet rule
             				while(!guess.equals((String)LetterStack.Peek())) {
             					String top=(String)LetterStack.Peek();
             					LetterStack.Pop();
             					qq.Push(top);
             				}
             				while(!qq.isEmpty()) {
             					
             					q.Push(qq.Pop());
             				}
             				LetterStack.Pop();
             				while(!q.isEmpty()) {
             					qq.Push(q.Pop());
             				}
             				while(!qq.isEmpty()) {
             					LetterStack.Push(qq.Pop());
             				}
                    	 }
                    	 
                         
                    	 while(!(LetterStack.isEmpty())) {
             				t.Push(LetterStack.Pop());
             			}
             			while(!(t.isEmpty())) {
             				LetterStack.Push(t.Pop());
             			}
             			System.out.println();
             			while(!MissingLetter.isEmpty()) {
             				tempmiss.Push(MissingLetter.Pop());
             			}
             			while(!tempmiss.isEmpty()) {
             				MissingLetter.Push(tempmiss.Pop());
             			}
             			System.out.println();
                     }

                }

			guessedletters.Push(guess);
			}
			if(point<=0) {
				System.out.println("You lost! :( Your point is:  "+point);
			}
			else {
				System.out.println("You win! :D Your point is:  "+point);
			}
			printStack(WordStack," ");
			QScore.enqueue(point);
							
			
			
			
		}while(playAgainIsTrue());
		SortQueue(QScore);
		Scanner scn=new Scanner(System.in);
		System.out.println("Enter your name: ");
		String name=scn.nextLine();
		QName.enqueue(name);

		
		scorewrite("C:\\highscoretable.txt",QName,QScore);
		
		
	

	}
	public static void scorewrite(String path,CircularQueue name,CircularQueue score) { //writing the score into file
		try {
			FileWriter f = new FileWriter(path, true);
			for(int i=0;i<name.size();i++) {
                f.write(name.peek() + "\t" + score.peek() + "\n");
                name.enqueue(name.dequeue());
                score.enqueue(score.dequeue());
            }
            f.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public static void SortQueue(CircularQueue score) { //sorting the QScore by the points
		if(score.size()==1) {
			score.enqueue(score.dequeue());
		}
		else {
			int min=Integer.MAX_VALUE;
			for(int i=0;i<score.size();i++) {
				if((int)score.peek()<min) {
					min=(int)score.peek();
				}
				score.enqueue(score.dequeue());
			}
			for(int i=0;i<score.size();i++) {
				if((int)score.peek()== min)
					score.dequeue();
				else
					score.enqueue(score.dequeue());
			}
			score.enqueue(min);
		}
	}
	

}
