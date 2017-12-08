/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.Employee;
import Model.Database.DBEmployees;
import Model.Database.DBUsers;
import java.sql.SQLException;



/**
 *
 * @author aburom
 */
public class DBFacade {

    private static DBFacade dbFacade;
    private DBUsers users;
    private DBEmployees employees;

    private DBFacade() {
        users = new DBUsers();
        employees = new DBEmployees();
    }
    
    public static DBFacade getDBFacade(){
        if (dbFacade == null) {
            dbFacade = new DBFacade();
        }
        return dbFacade;
    }
    
    public boolean verifyUser(String username, String password) throws SQLException, ClassNotFoundException{
        return users.verifyUser(username, password);
    }
    
    public Object[][] getEmployeeData() throws SQLException, ClassNotFoundException{
        return employees.getEmployeeData();
    }
    
    public String[] getEmployeeColumns(){
        return employees.getEmployeeColumns();
    }
    
    public boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException{
        return employees.addEmployee(emp);
    }
    
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException{
        return employees.deleteEmployee(id);
    }
    
    public Employee getEmployeeByID(String ID) throws SQLException, ClassNotFoundException{
        return employees.getEmployeeByID(ID);
    }
}

