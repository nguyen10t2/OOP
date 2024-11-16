package getTwitter_signAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Account {
	private String userMail = "";
	private String userPass = "";
	private String userName = "";

	public void readAccount() {
		try(Scanner scanner = new Scanner(new File(getResourcePath()))) {
			String[] line = scanner.nextLine().split(" ");
			userMail = line[0];
			userPass = line[1];
			if(line.length > 2) {
				userName = line[2];
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String getResourcePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResource("account.txt").getFile();
    }

	public String getUserMail() {
		return userMail;
	}

	public String getUserPass() {
		return userPass;
	}

	public String getUserName() {
		return userName;
	}
}
