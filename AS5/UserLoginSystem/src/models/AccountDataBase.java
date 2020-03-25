package models;

import java.util.HashMap;
import java.util.Map;

public class AccountDataBase {

	private static Map<String, Account> accounts = new HashMap<>();
	
	public void addAccount(Account acc) {
		accounts.put(acc.getUserName(), acc);
	}
	
	public Account getAccount(String userName) {
		return accounts.get(userName);
	}
	
	static {
        accounts.put("Patrick", new Account("Patrick", "1234"));
        accounts.put("Molly", new Account("Molly", "FloPup"));
    }
}
