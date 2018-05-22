package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Anagram {
	private static int userIndex = -1;

	public static void main(String[] args) {
		ArrayList<ParentUser> parentArr = new ArrayList<ParentUser>();
		ArrayList<ChildUser> childArr = new ArrayList<ChildUser>();
		Random r = new Random();
		ArrayList<String> scrambled = new ArrayList<String>(); // list of scrambled words
		String accountBool = "no"; // if there is a account or not
		boolean endGame = false; // if user wants to end game
		String username = ""; // user username
		String password = ""; // user password
		boolean typeBool = false; // if child or parent type of account

		// relative path for the data
		FileIO fileIO = new FileIO("/src/main/AnagramData.txt");
		// get the data from file
		ArrayList<String> data = fileIO.readData(); // list of normal words

		// scramble the data
		for (int i = 0; i < data.size(); i++) {
			scrambled.add(scramble(r, data.get(i)));
		}

		Scanner s = new Scanner(System.in);
		// keep going until user wants to stop
		while (endGame != true) {
			System.out.println(
					"\n\nWelcome to the Anagram Game!\nIf you are a child, please ask your parents to create an account for you.\nIf you have an account, please type yes. If you do not have account, please type no.\n");
			accountBool = s.next();
			while (!accountBool.equals("yes") && !accountBool.equals("no")) {
				System.out.println("Please print yes or no: ");
				accountBool = s.next();
			}
			// if no account, then make a new parent account, then child account
			if (accountBool.equals("no")) {
				newParent(parentArr, childArr);
			} else {
				// ask for user name
				System.out.print("\nUsername: ");
				username = s.next();
				int valid = validUserName(username, parentArr, childArr);
				while (valid == -1) {
					System.out.println("\nI'm sorry, I could not find your username. Please try again.\n");
					System.out.print("\nUsername: ");
					username = s.next();
					valid = validUserName(username, parentArr, childArr);
				}
				if (valid == 0) {
					// a parent
					typeBool = true;
				} else {
					// a child
					typeBool = false;
				}
				// ask for the corresponding password
				System.out.print("Password: ");
				password = s.next();
				// if parent account
				if (typeBool) {
					// get the correct parent
					ParentUser parent = parentArr.get(userIndex);
					while (!password.equals(parent.getPassword())) {
						System.out.println("\nI'm sorry, that is not the correct password. Please try again.\n");
						password = s.next();
					}
					System.out.println(
							"\nIf you would like to view your child's scores, please type s.\nIf you would like to change the time limit, please time t.\n");
					String parentChoice = s.next();
					while (!parentChoice.equals("s") && !parentChoice.equals("t")) {
						System.out.println("\nPlease type either s for scores or t for time limit.\n");
						parentChoice = s.next();
					}
					if (parentChoice.equals("s")) {
						parent.printChildScores();
					} else {
						System.out.println("Please type a whole number between 10 and 60 for the new time limit:");
						int time = 0;
						while (!s.hasNextInt()) {
							System.out.println("Please enter a valid number from 10 to 60: ");
							s.next();
						}
						time = s.nextInt();
						while (time < 10 || time > 60) {
							System.out.println("Please enter a whole number from 10 to 60: ");
							time = s.nextInt();
							s.nextLine();
						}
						parent.setTime(time);
					}

				} else {
					// if child account
					ChildUser child = childArr.get(userIndex);
					while (!password.equals(child.getPassword())) {
						System.out.println("\nI'm sorry, that is not the correct password. Please try again.\n");
						password = s.next();
					}
					// create a new game, and play
					AnagramGame game = new AnagramGame(child);
					endGame = game.playGame(scrambled, data);
					if (!endGame) {
						continue;
					} else {
						System.out.println("Sad to see you leave. Good bye!");
						break;
					}
				}

			}

		}
		s.close();

	}

	/**
	 * Scramble the words in the array using Fisher-Yates shuffle
	 * 
	 * @param random
	 *            the random object
	 * @param inputString
	 *            the string to scramble
	 * @return the scrambled string
	 */
	public static String scramble(Random random, String inputString) {
		// Convert your string into a simple char array:
		char a[] = inputString.toCharArray();

		// Scramble the letters using the standard Fisher-Yates shuffle,
		for (int i = 0; i < a.length; i++) {
			int j = random.nextInt(a.length);
			// Swap letters
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}

		return new String(a);
	}

	/**
	 * create a new parent and child account
	 * 
	 * @param parentArr
	 *            array of parents
	 * @param childArr
	 *            array of gamers
	 */
	public static void newParent(ArrayList<ParentUser> parentArr, ArrayList<ChildUser> childArr) {
		String parentUser = "";
		String parentPass = "";
		String childUser = "";
		String childPass = "";
		String childName = "";
		int time = 0;

		Scanner s = new Scanner(System.in);
		System.out.println("\nPlease enter a username: ");
		parentUser = s.next();
		System.out.println("\nPlease enter a password: ");
		parentPass = s.next();
		System.out.println("\nPlease enter your child's name: ");
		childName = s.next();
		System.out.println("\nPlease enter your child's username: ");
		childUser = s.next();
		System.out.println("\nPlease enter your child's password: ");
		childPass = s.next();
		System.out.println("\nPlease enter a time limit for how long your child has to solve an anagram (10 to 60): ");
		while (!s.hasNextInt()) {
			System.out.println("Please enter a valid number from 10 to 60: ");
			s.next();
		}
		time = s.nextInt();
		while (time < 10 || time > 60) {
			System.out.println("Please enter a whole number from 10 to 60: ");
			time = s.nextInt();
		}

		// add a new parent and child
		ParentUser parent = new ParentUser(parentUser, parentPass, time);
		parent.addChild(childName, childUser, childPass);
		parentArr.add(parent);
		childArr.add(parent.getChild());
	}

	/**
	 * check if the username is valid or not
	 * 
	 * @param user
	 *            the username to check
	 * @param parentArr
	 *            the parent array
	 * @param childArr
	 *            the gamer array
	 * @return -1 if not in either array, 0 if in parent array, 1 if in child array
	 */
	public static int validUserName(String user, ArrayList<ParentUser> parentArr, ArrayList<ChildUser> childArr) {
		for (int i = 0; i < parentArr.size(); i++) {
			if (user.equals(parentArr.get(i).getUserName())) {
				userIndex = i;
				return 0;
			} else if (user.equals(childArr.get(i).getUserName())) {
				userIndex = i;
				return 1;
			}
		}
		return -1;

	}

}
