package main;

import java.util.Date;

public class Score {
	private int score;
	private Date date;
	
	/**
	 * Constructs a Score object using score and Date
	 * @param score
	 * @param date
	 */
	public Score(int score, Date date) {
		this.score = score;
		this.date = date;
	}
	
	/**
	 * getter for score
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * getter for date
	 * @return
	 */
	public Date getDate() {
		return date;
	}
}
