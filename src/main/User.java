package main;

public interface User {
	/**
	 * three different abstract methods used in both ChildUser and ParentUser
	 * 
	 * @return
	 */
	public String getUserName(); // gets the user name of the user

	public String getPassword();// gets the password of the user

	public int getAccountNum(); // gets the account number of the user

}
