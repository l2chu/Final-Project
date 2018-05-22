package main;

/**
 * Represents a Parent user allowing the child to play the game
 * 
 * @author lchu
 *
 */
public class ParentUser implements User {
	private int time;
	private String mUserName;
	private String mPassword;
	private int accountNum;
	private static int accountNumber = 1;
	private ChildUser child;

	/**
	 * Constructs a ParentUser object
	 * 
	 * @param userName
	 * @param password
	 * @param time
	 */
	public ParentUser(String userName, String password, int time) {
		accountNum = accountNumber;
		accountNumber++;
		mUserName = userName;
		mPassword = password;
		this.time = time;
	}

	public String getUserName() {
		return mUserName;
	}

	public String getPassword() {
		return mPassword;
	}

	public int getAccountNum() {
		return accountNum;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * add a child to this parent
	 * 
	 * @param name
	 * @param userName
	 * @param password
	 */
	public void addChild(String name, String userName, String password) {
		child = new ChildUser(name, userName, password, this);
	}

	public ChildUser getChild() {
		return child;
	}

	/**
	 * print out the child's scores
	 */
	public void printChildScores() {
		child.printScore();
	}

}
