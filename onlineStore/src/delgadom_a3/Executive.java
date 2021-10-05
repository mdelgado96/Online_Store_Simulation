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
class Executive extends Manager {
    private ArrayList<Manager> managedManagers = new ArrayList<>();
    private double totalComp;
    
    //Constructor
    public Executive(String name, String title,int ID, int age, double salary)
    {
        super(name, title, ID, age, salary);
        totalComp = 0.0;
    }
    public Executive(){totalComp = 0.0;};
    
    // Mutator
    public void setTotalComp(double profit, double bonus)
    {
        totalComp = getSalary() + (profit*(bonus/100.00));
    }
    
    // Accessor
    public double getTotalComp()
    {
        return totalComp;
    }
    
    // Mutator
    // Add Employee
    public void addManager(Manager emp)
    {
        managedManagers.add(emp);
    }
    
    // Accessor to get list size
    public int getManagedManagersListSize()
    {
        return managedManagers.size();
    }
    
    // Accessor to get managers managed by exectutives
    public Manager getManagedManager(int index)
    {
        return managedManagers.get(index);
    }
    
    // toString to print 
    @Override
    public String toString( )
    {
       return String.format("%-15s %-15s %-10d %-5d %-10.2f %-10.2f\n", 
               getName(), getTitle(), getID(), getAge(), getSalary(), getTotalComp());
    }
    
}
