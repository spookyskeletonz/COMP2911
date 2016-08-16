package default_package;

import java.util.Calendar;

public class Manager extends Employee {
	
	private Calendar hireDate;
	
	/**
	 * Constructor for manager
	 * @param name is the name of the manager
	 * @param salary is the salary of manager
	 * @param hireDate is the hire date of manager
	 */
	
	public Manager(String name, int salary, Calendar hireDate){
		//constructor for Manager
		super(name, salary);
		this.hireDate = hireDate;
	}
	
	public void sethireDate(Calendar hireDate){
		//set for hireDate
		this.hireDate = hireDate;
	}
	
	public Calendar gethireDate(){
		//get for hireDate
		return this.hireDate;
	}
	
	public String toString(){
		//converts all values to a single string
		return super.toString() + "{hireDate: " + hireDate.get(5) + "/" + hireDate.get(2) + "/" + hireDate.get(1) + "}";
	}
	
	public boolean equals(Object o){
		if(!super.equals(o)) return false;
		Manager m = (Manager) o;
		return this.hireDate.equals(m.hireDate);
	}
	
	public Manager clone(){
		Manager m = (Manager) super.clone();
		m.hireDate = this.hireDate;
		return m;
	}
}
