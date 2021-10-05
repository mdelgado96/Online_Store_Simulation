import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
public class BusinessTest
{
	public static void main(String [] args) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.printf("Please enter the name of the input file with employee name and data: \n");

		String inputFileName = keyboard.next();
		File inputFile = new File(inputFileName);
		if(!inputFile.exists())
		{
			System.out.printf("Input File %s was not found.\n", inputFileName);
			System.exit(0);
		}
		Scanner inputReader = new Scanner(inputFile);
		
		
		ArrayList<Employee> employeeAL = new ArrayList<Employee>();
		ArrayList<Manager> managerAL = new ArrayList<Manager>();
		ArrayList<Executive> executiveAL = new ArrayList<Executive>();
		
		//Three useful temporary variables
		Employee emp;
		Manager mgr;
		Executive exec;
		//Add the employees
		String name;
		String title;
		int ID;
		int age;
		double salary;
		while(inputReader.hasNext())
		{
			name = inputReader.next();
			title = inputReader.next();
			ID = inputReader.nextInt();
			age = inputReader.nextInt();
			salary = inputReader.nextDouble();

			if (ID > 0 && ID <= 2000)
			{
				emp = new Employee(name, title, ID, age, salary);
				employeeAL.add(emp);
			}
			else if (ID > 2000 && ID <= 3000)
			{
				mgr = new Manager(name, title, ID, age, salary);
				managerAL.add(mgr);
			}
			else if (ID > 3000 && ID <= 3500)
			{
				executiveAL.add(new Executive(name, title, ID, age, salary));
			}	
			else
				System.out.printf("The employee ID %d is not legal\n", ID); // Alternate way to create and place an object
		}
		
		//Give employees a manager as a boss
		//Must distribute the employees across the managers
		int managerIndex;
		for(int i = 0; i < employeeAL.size(); i++)
		{
			managerIndex = i%managerAL.size();
			mgr = managerAL.get(managerIndex);
			mgr.addManagedEmployee(employeeAL.get(i));
		}
		
		//Give managers an executive as a boss
		//Must distribute the managers across the executives
		int executiveIndex;
		for(int i = 0; i < managerAL.size(); i++)
		{
			executiveIndex = i%executiveAL.size();
			exec = executiveAL.get(executiveIndex);
			exec.addManagedEmployee(managerAL.get(i));
		}
		
		//Request companyProfit and Bonus Percentage
		double companyProfit, bonusPercentage;
		System.out.printf("Please enter the total company profit for the year: $");
		companyProfit = keyboard.nextDouble();
		System.out.printf("Please enter the executive bonus percentage for the year correct to 1 decimal place: ");
		bonusPercentage = keyboard.nextDouble();
		
		//Calculate the total compensation for each executive
		for(Executive execIter : executiveAL)
		{
			execIter.setTotalComp(companyProfit, bonusPercentage);
		}
		//Create the output file
		System.out.printf("Please enter the name of the output file: ");
		String outputFileName = keyboard.next();
		File outputFile = new File (outputFileName);
		PrintWriter outputWriter = new PrintWriter(outputFile);
		
		//Calculate and print total company payroll
		double totalPayroll = 0.0;
		for(Executive execI : executiveAL)
		{
			totalPayroll += execI.getTotalComp();
		}
		for(Manager mgrI : managerAL)
		{
			totalPayroll += mgrI.getSalary();
		}
		for(Employee empI : employeeAL)
		{
			totalPayroll += empI.getSalary();
		}
		outputWriter.printf("The total payroll for the business is $%,4.2f\n\n", totalPayroll);
		
		//List each Executive with the attributes
		outputWriter.printf("Compensation Table\nExecutives\n");
		outputWriter.printf("%-5s%15s%15s%10s%10s%20s\n", "Name", "Title", "ID", "Age", "Salary", "Total Comp");
		for(Executive execI : executiveAL)
		{
			outputWriter.printf("%-15s%-17s%-10d%-7d$%,-15.2f$%,-15.2f\n", execI.getName(), execI.getTitle(), execI.getID(), execI.getAge(), execI.getSalary(), execI.getTotalComp());
		}
		//List each Manager with the attributes
		outputWriter.printf("\nManagers\n");
		outputWriter.printf("%-5s%15s%15s%10s%10s\n", "Name", "Title", "ID", "Age", "Salary");
		for(Manager mgrI : managerAL)
		{
			outputWriter.printf("%-15s%-17s%-10d%-7d$%,-15.2f\n", mgrI.getName(), mgrI.getTitle(), mgrI.getID(), mgrI.getAge(), mgrI.getSalary());
		}
		//List each Manager with the attributes
		outputWriter.printf("\nEmployees\n");
		outputWriter.printf("%-5s%15s%15s%10s%10s\n", "Name", "Title", "ID", "Age", "Salary");
		for(Employee empI : employeeAL)
		{
			outputWriter.printf("%-15s%-17s%-10d%-7d$%,-15.2f\n", empI.getName(), empI.getTitle(), empI.getID(), empI.getAge(), empI.getSalary());
		}
		//For Each Executive print the name and ID of each Manager managed
		outputWriter.printf("\n\nReporting Structure\n\nDirect Reports To Executive\n");
		for(Executive execI : executiveAL)
		{
			outputWriter.printf("Executive %s has the following direct reports\n", execI.getName());
			int size = execI.getManagedEmployeesListSize();
			for (int i = 0; i < size; i++)
			{
				outputWriter.printf("%-15s%-10d\n", execI.getManagedEmployee(i).getName(), execI.getManagedEmployee(i).getID());
			}
			outputWriter.printf("\n");
		}
		//For Each Manager print the name and ID of each Employee managed
		outputWriter.printf("\n\nDirect Reports To Managers\n");
		for(Manager mgrI : managerAL)
		{
			outputWriter.printf("Manager %s has the following direct reports\n", mgrI.getName());
			int size = mgrI.getManagedEmployeesListSize();
			for (int i = 0; i < size; i++)
			{
				outputWriter.printf("%-15s%-10d\n", mgrI.getManagedEmployee(i).getName(), mgrI.getManagedEmployee(i).getID());
			}
			outputWriter.printf("\n");
		}
		
		//Check change Salary and toString
		System.out.printf("Please enter the salary change for a Manager as a percentage: ");
		double raisePercent = keyboard.nextDouble();
		for(Manager mgrI: managerAL)
		{
			mgrI.changeSalary(raisePercent);
			outputWriter.printf("%s\n", mgrI.toString());
		}
		
		inputReader.close();
		outputWriter.close();
	}
}