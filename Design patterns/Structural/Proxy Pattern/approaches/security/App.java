/*
Imagine you a bank that has official branches and they can make basic and restricted operations. However,
the bank wants to make business with a local institution, but somehow needs this institution to have
access only to basic operations. This is just a shallow example and it is based on resource [1].

Resources:
[1] https://www.youtube.com/watch?v=cHg5bWW4nUI

*/

public class App {
	public static void main(String[] args) {
		InstitutionLocal institutionBranch = new InstitutionLocal();
		institutionBranch.deposit(999d);
		//This implementation is not the best, but maybe illustrates the purpose.
	}
}

interface Operations {
	void deposit(Double amount);
}

interface BasicOperations extends Operations {
	double checkAmount(String accountNumber);
	//boolean isMyAccountInternational();
}

interface AdvancedOperations extends Operations {
	boolean createAccount(String name, String lastName);
	//boolean deleteAccount(String accountNumber);
}

interface Branch {}

class OfficialBranch implements BasicOperations, AdvancedOperations, Branch {
	@Override
	public void deposit(Double amount) { System.out.println("Depositing the money: " + amount); }

	@Override
	public double checkAmount(String accountNumber) { return 10d; }

	@Override
	public boolean createAccount(String name, String lastName) {
		System.out.println("Account created for: " + name + " " + lastName);
		return true;
	}
}

class Proxy implements BasicOperations {
	private final OfficialBranch officialBranch = new OfficialBranch();

	@Override
	public void deposit(Double amount) { officialBranch.deposit(amount); }

	@Override
	public double checkAmount(String accountNumber) { return officialBranch.checkAmount(accountNumber); }

}
class InstitutionLocal {
	private final Proxy proxy = new Proxy();

	public void deposit(Double amount) { proxy.deposit(amount); }
	public double checkAmount(String accountNumber) { return proxy.checkAmount(accountNumber); }
}
