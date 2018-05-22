package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Represents a child gamer playing the anagram game
 * 
 * @author lchu
 *
 */
public class ChildUser implements User {
	private String mName;
	private String mUserName;
	private String mPassword;
	private int accountNum;
	private static int accountNumber = 1;
	private ArrayList<Score> topScores;
	private ParentUser parent;

	/**
	 * Constructs a ChildUser object using name, userName, password, and parent
	 * 
	 * @param name
	 *            the name
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param parent
	 *            the parent of this gamer
	 */
	public ChildUser(String name, String userName, String password, ParentUser parent) {
		mName = name;
		mUserName = userName;
		mPassword = password;
		accountNum = accountNumber;
		accountNumber++;
		topScores = new ArrayList<Score>();
		this.parent = parent;
	}

	/**
	 * getter for userName
	 * 
	 * @return the username
	 */
	public String getUserName() {
		return mUserName;
	}

	/**
	 * getter for password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return mPassword;
	}

	/**
	 * getter for accountNum
	 * 
	 * @return accountNum
	 */
	public int getAccountNum() {
		return accountNum;
	}

	/**
	 * getter for name
	 * 
	 * @return the name
	 */
	public String getName() {
		return mName;
	}

	/**
	 * getter for parent
	 * 
	 * @return the parent
	 */
	public ParentUser getParent() {
		return parent;
	}

	/**
	 * getter for scores
	 * 
	 * @return the scores
	 */
	public ArrayList<Score> getScores() {
		return topScores;
	}

	/**
	 * adds a score to the topScores list in the correct spot
	 * 
	 * @param score
	 *            the Score to add
	 */
	public void addScore(Score score) {
		boolean added = false; // keeps track if the score has been added yet
		// if it is empty, just add to the end
		if (topScores.isEmpty()) {
			topScores.add(score);
		} else {
			// go through to find out where to put the score based on the other scores
			for (int i = 0; i < topScores.size(); i++) {
				// if the current index score is higher, then just continue.
				if (topScores.get(i).getScore() > score.getScore()) {
					continue;
				} else {
					topScores.add(i, score); // add the score at the current index
					added = true;
					break;
				}
			}
			// if it hasnt been added yet, that means it is the smallest one. so add to end
			if (!added) {
				topScores.add(score);
			}
			// remove the last item in the array if bigger than 10
			if (topScores.size() > 10) {
				topScores.remove(10);
			}
		}
	}

	/**
	 * print the child's score
	 */
	public void printScore() {
		// if it is empty, print out error
		if (topScores.isEmpty()) {
			System.out.println("You have no scores yet. Go play to set some high scores!");
		} else {
			// print out the header
			System.out.println("Top Scores:");
			// print out each score with the number, the score, and the date
			for (int i = 0; i < topScores.size(); i++) {
				// need to format the Date object
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				System.out.printf("%-10d Score: %-10d Date: %-10s\n", i + 1, topScores.get(i).getScore(),
						formatter.format(topScores.get(i).getDate()));
			}
		}
	}

}
