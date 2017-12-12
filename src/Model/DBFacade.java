/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.Employee;
import Entities.Report;
import Model.Database.DBEmployees;
import Model.Database.DBReports;
import Model.Database.DBUsers;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author aburom
 */
public class DBFacade {

    private static DBFacade dbFacade;
    private DBUsers users;
    private DBEmployees employees;
    private DBReports reports;

    private DBFacade() {
        users = new DBUsers();
        employees = new DBEmployees();
        reports = new DBReports();
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
    
    public boolean updateEmployee(Employee emp) throws SQLException, ClassNotFoundException{
        return employees.UpdateEmployee(emp);
    }
    
    public Object[][] getReports(String EMP_ID) throws SQLException, ClassNotFoundException{
        return reports.getReports(EMP_ID);
    }
    
    public String[] getReportColumns() throws SQLException, ClassNotFoundException{
        return reports.getReportColumns();
    }
    
    public boolean addReport(Report report) throws SQLException, ClassNotFoundException{
        return reports.addReport(report);
    }
    
    public ResultSet getReport(String REP_ID) throws SQLException, ClassNotFoundException{
        return reports.getReport(REP_ID);
    }
}

