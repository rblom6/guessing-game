package com.guessinggame;


import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GuessingGame {

    private int myGuess;
    private int nummatches;
    private int numberOfGuesses;
    ArrayList<Integer> possibleGuesses;


	public GuessingGame ( ) {
		// initialization

                myGuess = 0;
                nummatches = 0;
                numberOfGuesses = 0;

                //initializing ArrayList called possibleGuesses
                int[] temp = new int[9000];
                int n = 1000;
                for(int i = 0; i<9000; i++){            
                        temp[i] = n;
                        n++;
                }

                possibleGuesses = new ArrayList<Integer>();
                for(int i = 0; i<9000; i++){
                        possibleGuesses.add(temp[i]);
                }


	}


	public int myGuessIs() {		
                // if possibleGuesses size is zero return -1, and
                // if possibleGuesses size != zero return a random
                // number from possibleGuesses 
                if(possibleGuesses.size() == 0){
                        return -1;
                }else{
                        int index = (int) (Math.random()*(possibleGuesses.size()));
                        int num = possibleGuesses.get(index);
                        myGuess = num;
                        numberOfGuesses++;

                        return myGuess;
                }
	}
	

	public int totalNumGuesses() {
		//returns the total number of guesses taken

                return numberOfGuesses;
	}

 
	public void updateMyGuess(int nmatches) {
		// update the guess based on the number of matching digits claimed by the user
                // updates list so that it contains only nmatches with the guessed number
                Integer copy = myGuess;
                String currentGuess = copy.toString();
                ArrayList<Integer> newGuesses = new ArrayList<Integer>();
                
                for(Integer o: possibleGuesses){
                        String numList = o.toString();
                        int matches = 0;        
                        
                        for(int i = 0; i<numList.length(); i++){
                                if(numList.charAt(i) == currentGuess.charAt(i)){
                                        matches++;
                                }
                        }

                        if(matches == nmatches){
                               newGuesses.add(o);
                        }
                }
                possibleGuesses = newGuesses;
                
    }
	

	public static void main(String[] args) {

		GuessingGame gamer = new GuessingGame( );
  
		JOptionPane.showMessageDialog(null, "Think of a number between 1000 and 9999.\n Click OK when you are ready...", "Let's play a game", JOptionPane.INFORMATION_MESSAGE);
		int numMatches = 0;
		int myguess = 0;
		
		do {
			myguess = gamer.myGuessIs();
			if (myguess == -1) {
				JOptionPane.showMessageDialog(null, "I don't think your number exists.\n I could be wrong though...", "Mistake", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			String userInput = JOptionPane.showInputDialog("I guess your number is " + myguess + ". How many digits did I guess correctly?");
			// quit if the user input nothing (such as pressed ESC)
			if (userInput == null)
				System.exit(0);
			// parse user input, pop up a warning message if the input is invalid
			try {
				numMatches = Integer.parseInt(userInput.trim());
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			// the number of matches must be between 0 and 4
			if (numMatches < 0 || numMatches > 4) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			if (numMatches == 4)
				break;
			// update based on user input
			gamer.updateMyGuess(numMatches);
			
		} while (true);
		
		// the game ends when the user says all 4 digits are correct
		System.out.println("Aha, I got it, your number is " + myguess + ".");
		System.out.println("I did it in " + gamer.totalNumGuesses() + " turns.");
	}
}
