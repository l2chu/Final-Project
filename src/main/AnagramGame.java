package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class AnagramGame {
	private ChildUser child;
	private int maxSeconds;
	private String userInput;
	private int score = 0;

	public AnagramGame(ChildUser child) {
		this.child = child;
		maxSeconds = child.getParent().getTime() * 1000;
	}

	/**
	 * Simulates a game being played
	 * 
	 * @param scrambled
	 *            the array of scrambled words
	 * @param data
	 *            the array of non scrambled words
	 * @return true if dont want to continue playing, false if not.
	 */
	public boolean playGame(ArrayList<String> scrambled, ArrayList<String> data) {
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		// go through five different random words
		for (int i = 0; i < 5; i++) {
			// generate random number
			int randomNum = r.nextInt(scrambled.size());
			// print out how many seconds they have to solve
			System.out.println("\nYou have " + maxSeconds / 1000 + " seconds to solve this word:");
			System.out.println(scrambled.get(randomNum));
			// keep track of how long it took to answer
			long start = System.currentTimeMillis();
			System.out.println(System.currentTimeMillis());
			userInput = s.next();
			long end = System.currentTimeMillis();
			// if too long, cannot get score
			if (end - start > maxSeconds) {
				System.out.println("Oh no! You did not get the answer on time.");
			} else {
				if (userInput != null) {
					// check if user input is correct
					if (userInput.equals(data.get(randomNum))) {
						System.out.println("\nCongratulations! You got the right answer on time.");
						score++;
					} else {
						System.out.println("\nI'm sorry, you did not get the right answer.");
					}
				}
			}

		}
		// add the new score to the child's scores
		child.addScore(new Score(score, new Date()));
		// ask if want to play again
		System.out.println("Would you like to play again? (y/n)");
		s.nextLine(); // need an extra line to make sure no empty lines
		String choice = s.next();
		while (!choice.equals("y") && !choice.equals("n")) {
			System.out.println("\nPlease type either y or n for yes or no.\n");
			choice = s.next();
		}
		if (choice.equals("n")) {
			return true;
		}
		return false;

	}

}
