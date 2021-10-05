/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package delgadom_a3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author marvindelgado
 */
public class BusinessTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
    ArrayList<Employee> employeeAL = new ArrayList<>();
    ArrayList<Manager> managerAL = new ArrayList<>();
    ArrayList<Executive> executiveAL = new ArrayList<>();
    
    // read what user enters
    Scanner user = new Scanner(System.in);
    System.out.printf("Please enter the name of the file to read in data from: ");
    
    String inputFileName = user.next();
    File inputFile = new File(inputFileName);
    
    if(!inputFile.exists())
    {
	System.out.printf("File %s is not found\n", inputFileName);
	System.exit(0);
    }
	
        try (Scanner input = new Scanner(inputFile)) {
            while (input.hasNext())
            {
                String name = input.next();
                String title = input.next();
                int id = input.nextInt();
                int age = input.nextInt();
                double salary = input.nextDouble();
                
                if(id > 0 && id <= 2000)
                    employeeAL.add(new Employee(name, title, id,
                            age, salary));
                else if(id > 2000 && id <= 3000)
                    managerAL.add(new Manager(name, title, id,
                            age, salary));
                else if(id > 3000 && id <= 3500)
                    executiveAL.add(new Executive(name, title, id,
                            age, salary));
		//else 
		//    System.out.printf("The Employee ID %d is not legal\n", ID);
                
            }
        }
        // Loop to add an employee to a manager
        Employee x;
        for(int i=0; i < employeeAL.size(); i++)
        {
            int j = i % managerAL.size();
            x = employeeAL.get(i);
            managerAL.get(j).addEmployee(x);
        }
        
        // Loop to add a manager to an executive
        Manager y;
        for(int i=0; i < managerAL.size(); i++)
        {
            int j = i % executiveAL.size();
            y = managerAL.get(i);
            executiveAL.get(j).addManager(y);
        }
        
        // Prompt user for total company profit
        System.out.printf("Please enter the total "
                    + "company profit for the year: ");
        double profit = user.nextDouble();
        
        // Prompt user for annual bonus
        System.out.printf("Please enter the executive "
                    + "bonus percentage for the year "
                    + "correct to 1 decimal place: ");
        double bonus = user.nextDouble();
        
        // Loop to set total compensation
        for(int i = 0; i < executiveAL.size(); i++)
        {
            executiveAL.get(i).setTotalComp(profit, bonus);
        }
        
        // Prompt user for ouput file name
        System.out.print("Please enter the name of the output file: ");
        String outputFileName = user.next();
        
        // Prepare output file
        FileWriter outputFile = new FileWriter(outputFileName, true);
        PrintWriter outputWriter = new PrintWriter(outputFile);
        
        // Prompt user for salary change for the managers
        System.out.printf("Please enter the salary change for "
                    + "a Manager as a percentage:");
        double newSalary = user.nextDouble();
        
        // Initiates each variable
        double totPayRoll = 0;
        double totPayRoll1 = 0;
        double totPayRoll2 = 0;
        double totPayRoll3 = 0;
        double totBonus = 0;
        
        // Loops to get the sum for each of their salaries
        for(int i = 0; i < employeeAL.size(); i++)
        {
            totPayRoll1 += employeeAL.get(i).getSalary();
        }
        for(int i = 0; i < managerAL.size(); i++)
        {
            totPayRoll2 += managerAL.get(i).getSalary();
        }
        for(int i = 0; i < executiveAL.size(); i++)
        {
            totPayRoll3 += executiveAL.get(i).getSalary();
        }
        for(int i = 0; i < executiveAL.size(); i++)
        {
            totBonus += executiveAL.get(i).getTotalComp();
        }
        
        // Prints the total payroll for the business
        totPayRoll = totPayRoll1 + totPayRoll2 + totPayRoll3 + totBonus;
        outputWriter.println("The total payroll for the business is $" + totPayRoll);
        
        // Prints out Compensation Table
        // Executives
        outputWriter.println("\nCompensation Table");
        outputWriter.println("Executives");
        outputWriter.println("Name            Title           ID         Age   Salary     Total Comp");
        for(int i = 0; i < executiveAL.size(); i++)
        {
            outputWriter.print(executiveAL.get(i).toString());
        }
        // Skip line
        outputWriter.print("\n");
        
        // Managers
        outputWriter.println("Managers");
        outputWriter.println("Name            Title           ID         Age   Salary");
        for(int i = 0; i < managerAL.size(); i++)
        {
            outputWriter.print(managerAL.get(i).toString());
        }
        // Skip line
        outputWriter.print("\n");
        
        // Employees
        outputWriter.println("Employess");
        outputWriter.println("Name            Title           ID         Age   Salary");
        for(int i = 0; i < employeeAL.size(); i++)
        {
            outputWriter.print(employeeAL.get(i).toString());
        }
        // Skip muliple lines
        outputWriter.print("\n\n");
        
        // Reporting Structures
        // Executive Reporters
        outputWriter.println("Reporting Structure\n");
        outputWriter.println("Direct Reports To Executive");
        int g;
        for(int i = 0; i < executiveAL.size(); i++)
        {
            g = executiveAL.get(i).getManagedManagersListSize();
            outputWriter.println("Executive " + executiveAL.get(i).getName()+  " has "
                    + "the following direct reports");
            for(int j = 0; j < g; j++)
            {
                outputWriter.println(executiveAL.get(i).getManagedManager(j).getName() + "\t\t"
                    + executiveAL.get(i).getManagedManager(j).getID());
            }
            outputWriter.print("\n");
        }
        // Skip line
        outputWriter.print("\n\n");
        
        // Manager Reporters
        outputWriter.println("Direct Reports To Managers");
        int h;
        for(int i = 0; i < managerAL.size(); i++)
        {
            h = managerAL.get(i).getManagedEmployeesListSize();
            outputWriter.println("Manager " + managerAL.get(i).getName()+  " has "
                    + "the following direct reports");
            for(int j = 0; j < h; j++)
            {
                outputWriter.println(managerAL.get(i).getManagedEmployee(j).getName() + "\t\t"
                    + managerAL.get(i).getManagedEmployee(j).getID());
            }
            outputWriter.print("\n");
        }
        
        // Loop to print out the managers with new salary
        for (int i = 0; i < managerAL.size(); i++) 
        {
            managerAL.get(i).changeSalary(newSalary);
            outputWriter.println("The manager name and tile are "+ 
                    managerAL.get(i).getName() + " "
                    + managerAL.get(i).getTitle());
            
            outputWriter.print("The manager ID is " +
                    managerAL.get(i).getID() );
            outputWriter.println("  The manager age is " + managerAL.get(i).getAge());
            
            outputWriter.println("The manager salary is " + managerAL.get(i).getSalary());
            outputWriter.print("\n");
        }
        
        //outputWriter.println();
        
        
        outputWriter.close();
        
        
    }
    
}
