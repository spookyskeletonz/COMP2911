package default_package;

import java.util.Calendar;

public class Tester {
	public static void main(String[] args){
		
		//testing creation of employees
		Employee a = new Employee("Thomas", 1);
		System.out.println(a.toString());
		Employee b = new Employee("Pranav", 300000);
		System.out.println(b.toString());
		//testing 
		System.out.println("are thomas and pranav equal? " + a.equals(b));
		System.out.print("Difference in Salary is ");
		System.out.println(b.getSalary() - a.getSalary());
		//testing manager and manager cloning
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(1997, 4, 7);
		Employee c = new Manager("Sue", 5000000, cal);
		Manager c1 = (Manager) c;
		System.out.println("Manager: "+ c1.toString());
		Manager x = c1.clone();
		System.out.println("Checking whether manager clones are equal: " + x.equals(c1));
		System.out.println("Checking whether manager and employee are equal: " + x.equals(c));
		//testing wether employee cloning works
		Employee y = a.clone();
		System.out.println("Testing whether employee clones are equal: " + y.equals(a));
		
	}
}
