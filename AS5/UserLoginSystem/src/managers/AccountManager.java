package managers;

import models.Account;
import models.AccountDataBase;

public class AccountManager {
	private AccountDataBase data;
	private static AccountManager manager;
	
	private AccountManager() {
		data = new AccountDataBase();
	}
	
	public static AccountManager getInstance(){
        if (manager == null){
            synchronized (AccountManager.class){
                if (manager == null){
                	manager = new AccountManager();
                }
            }
        }
        return manager;
    }
	
	/**
	 * Returns null if account doesn't exist or password is wrong.
	 * Returns the account otherwise.
	 */
	public Account login(String userName, String password) {
		Account cur = data.getAccount(userName);
		if(cur == null || !cur.getPassword().equals(password)) {
			return null;
		}
		return cur;
	}
	
	/**
	 * Returns true if an account with specified user name exists
	 */
	public boolean containsUser(String userName) {
		return (data.getAccount(userName) != null);
	}
	
	/**
	 * Returns false if account already exists.
	 * Adds the account to the database and returns true otherwise.
	 */
	public boolean register(String userName, String password) {
		Account cur = data.getAccount(userName);
		if(cur == null) {
			data.addAccount(new Account(userName, password));
			return true;
		}
		return false;
	}

}






