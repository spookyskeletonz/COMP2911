
import java.util.Calendar;

public class BankAccount {
	private int balance;
	private int limit;
	private Calendar lastWithdrawDate;
	
	/**
	 * constructor for bank account
	 * @param balance 
	 * @param balance is the balance of account
	 * @param limit is the limit on withdrawals
	 * @param lastWithdrawDate is the last date there was a withdraw
	 */
	
	public BankAccount(){
		balance = 0;
		limit = 800;
		lastWithdrawDate = null;
	}
	
	public void deposit(int amount){
		/*Preconditions
		 *  - amount >= 0
		 *Postconditions
		 *	- balance > previous balance
		 */
		this.balance += amount;
	}
	
	public int getBalance(){
		/*Preconditions
		 *  -
		 *Postconditions
		 *	- 
		 */
		return this.balance;
	}
	
	public boolean withdraw(int amount){
		
		/*Preconditions
		 *  - balance <= amount
		 *  - amount >= 0
		 *Postconditions
		 *	- balance <= original balance
		 *	- balance >= 0
		 */
		
		if(isSameDay()){
			if(amount<=limit&&amount<=balance){
				limit-=amount;
				balance-=amount;
				lastWithdrawDate = Calendar.getInstance();
				return true;
			}else return false;
		} else {
			limit = 800;
			if(amount<=limit&&amount<=balance){
				limit-=amount;
				balance-=amount;
				lastWithdrawDate = Calendar.getInstance();
				return true;
			} else return false;
		}
	}
	
	public boolean isSameDay(){
		Calendar curr = Calendar.getInstance();
		if (curr.get(Calendar.DAY_OF_YEAR)==lastWithdrawDate.get(Calendar.DAY_OF_YEAR) && curr.get(Calendar.YEAR)==lastWithdrawDate.get(Calendar.YEAR)){
			return true;
		} else return false;
	}

}
