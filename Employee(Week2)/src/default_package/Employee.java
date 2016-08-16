package default_package;

public class Employee implements Cloneable{
	
	private String name;
	private int salary;
	
	/**
	 * Constructor for employee
	 * @param name is the name of employee
	 * @param salary is the salary of employee
	 */
	
	public Employee(String name, int salary){
		//this is the constructor. when you create it uses this
		this.name = name;
		this.salary = salary;
	}
	
	public void setSalary(int salary){
		//this is in case need to change after
		this.salary = salary;
	}
	
	public int getSalary(){
		//to return salary
		return this.salary;
	}
	
	public void setName(String name){
		//set the name of employee, change whatever
		this.name = name;
	}
	
	public String getName(){
		//get the name of employee
		return this.name;
	}
	
	public String toString(){
		//converts all values to a single string
		return "{name: " + this.name + " Salary: " + this.salary + "}";
	}
	
	public boolean equals(Object o){
		if(this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		Employee e = (Employee) o;
		//we use equals to compare strings
		//we use == for int
		return this.name.equals(e.name) && this.salary==e.salary;
	}
	
	public Employee clone(){
		try {
			Employee cloned = (Employee) super.clone();
			return cloned;
			} catch (CloneNotSupportedException e){
			return null;
		}
	}
	

}
