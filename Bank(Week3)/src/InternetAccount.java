

import java.util.Calendar;

public class InternetAccount extends BankAccount {
	private int monthlyPayments;
	private Calendar lastPaymentDate;

	/**
	 * Constructor for InternetAccount
	 * @param monthlyPayments is the number of payments per month(decreases)
	 * @param lastPaymentDate is the last time a payment was done on the internet account
	 */
	
	public InternetAccount() {
		super();
		monthlyPayments = 10;
		lastPaymentDate = null;
	}
	
	public boolean payment(int amount){
		if(isSameMonth()){
			if(super.withdraw(amount)&&monthlyPayments>0){
				monthlyPayments-=1;
				lastPaymentDate = Calendar.getInstance();
				return true;
			} else return false;
		} else if(super.withdraw(amount)){
			monthlyPayments = 9;
			lastPaymentDate = Calendar.getInstance();
			return true;
		} else return false;
			
	}
	
	public boolean isSameMonth(){
		Calendar curr = Calendar.getInstance();
		if(lastPaymentDate.get(Calendar.MONTH)==curr.get(Calendar.MONTH)&&lastPaymentDate.get(Calendar.YEAR)==curr.get(Calendar.YEAR)){
			return true;
		} else return false;
	}
	
}
