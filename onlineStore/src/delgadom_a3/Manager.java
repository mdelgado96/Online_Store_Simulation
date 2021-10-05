/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package delgadom_a3;

import java.util.ArrayList;

/**
 *
 * @author marvindelgado
 */
class Manager extends Employee {
    private ArrayList<Employee> managedEmployees = new ArrayList<>();
    
    //Constructor
    public Manager(String name, String title, int ID, int age, double salary)
    {
        super(name, title, ID, age, salary);
    }
    public Manager(){};
    
    // Mutator
    // Add Employee
    public void addEmployee(Employee emp)
    {
        managedEmployees.add(emp);
    }
    
    // Accessor
    public int getManagedEmployeesListSize()
    {
        return managedEmployees.size();
    }
    
    // Accessor
    public Employee getManagedEmployee(int index)
    {
        return managedEmployees.get(index);
    }
    
}
