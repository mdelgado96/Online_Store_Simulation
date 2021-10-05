/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package delgadom_a3;

/**
 *
 * @author marvindelgado
 */
public class Employee {
    // Instance variables
    private String _name;       // Name of employee
    private int _ID;            // ID of employee
    private int _age;           // Age of employee
    private double _salary;     // Salary of employee
    private String _title;      // Title of employee
    
    // Constructor
    public Employee (){}
    // Constructor
    public Employee(String name, String title, int ID, 
            int age, double salary)
    {
        _name = name;
        _ID = ID;
        _age = age;
        _salary = salary;
        _title = title;
    }
    
    // Accessors
    
    // returns Employee's name
    public String getName()
    {
        return _name;
    }
    // returns Employee's ID
    public int getID()
    {
        return _ID;
    }
    // returns Employee's age
    public int getAge()
    {
        return _age;
    }
    // returns Employee's salary
    public double getSalary()
    {
        return _salary;
    }
    // returns Employee's title
    public String getTitle()
    {
        return _title;
    }
    
    // Mutators
    // sets new Employee name
    public void setName(String Newname)
    {
        _name = Newname;
    }
    
    // sets new Employee ID
    public void setID(int ID)
    {
            _ID = ID;
    }
    
    // sets new Employee age
    public void setName(int newAge)
    {
        _age = newAge;
    }
    
    // sets new Employee salary
        public void setSalary(double newSalary)
    {
        _salary = newSalary;
    }
    
    // sets new Employee title
    public void setTitle(String newTitle)
    {
        _title = newTitle;
    }
    
    // toString method
    // Prints out all attributes of an Employee
    @Override
    public String toString( )
    {
       return String.format("%-15s %-15s %-10d %-5d %-10.2f\n", 
               _name, _title, _ID, _age, _salary);
    }
    
    // Change Salary method
    // Changes the salary of an employee with a given percentage
    public double changeSalary(double percentChange)
    {
        double s = (1.0 + (percentChange/100.0)) * _salary;
        return s;
    }
    
}
