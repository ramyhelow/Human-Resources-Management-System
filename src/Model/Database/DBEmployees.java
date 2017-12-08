/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import Entities.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aburom
 */
public class DBEmployees {

    private DBConnection aDBConnection;
    private Statement aStatement;
    private PreparedStatement aPreparedStatement;
    private ResultSet aResultSet;
    private ResultSetMetaData aResultSetMetaData;

    public Object[][] getEmployeeData() throws SQLException, ClassNotFoundException {

        aDBConnection = DBConnection.getInstance();
        aStatement = aDBConnection.getStatement();
        aResultSet = aStatement.executeQuery("Select * FROM EMPLOYEES");

        aResultSetMetaData = aResultSet.getMetaData();
        int columnCount = aResultSetMetaData.getColumnCount();

        aResultSet.last();
        Object[][] data = new Object[aResultSet.getRow()][columnCount + 1];
        int i = 0;
        aResultSet.beforeFirst();
        while (aResultSet.next()) {
            data[i][0] = aResultSet.getInt("ID");
            data[i][1] = aResultSet.getString("FName");
            data[i][2] = aResultSet.getString("MName");
            data[i][3] = aResultSet.getString("LName");
            data[i][4] = aResultSet.getString("eMail");
            data[i][5] = aResultSet.getString("PhoneNumber");
            data[i][6] = aResultSet.getString("Age");
            data[i][7] = aResultSet.getString("Position");
            data[i][8] = aResultSet.getString("Department");
            data[i][9] = aResultSet.getString("Salary");
            data[i][10] = aResultSet.getString("AbsentDays");

            String timestamp = aResultSet.getTimestamp("DateAdded").toString();
            data[i][11] = timestamp.substring(0, timestamp.indexOf(" "));

            i++;
        }

        return data;
    }

    public String[] getEmployeeColumns() {
        return new String[]{
            "ID", "FirstName", "MiddleName", "LastName", "eMail", "PhoneNumber", "Age", "Position", "Department", "Salary", "AbsentDays", "DateAdded"
        };
    }

    public boolean addEmployee(Employee emp) throws SQLException, ClassNotFoundException {
        aDBConnection = DBConnection.getInstance();
        aPreparedStatement
                = aDBConnection.getConnection().prepareStatement("INSERT INTO EMPLOYEES (FNAME,MNAME,LNAME,EMAIL,PHONENUMBER,AGE,POSITION,SALARY,DEPARTMENT) VALUES (?,?,?,?,?,?,?,?,?)");

        aPreparedStatement.setString(1, emp.getfName());
        aPreparedStatement.setString(2, emp.getmName());
        aPreparedStatement.setString(3, emp.getlName());
        aPreparedStatement.setString(4, emp.getEmail());
        aPreparedStatement.setString(5, emp.getPhoneNumber());
        aPreparedStatement.setString(6, emp.getAge());
        aPreparedStatement.setString(7, emp.getPosition());
        aPreparedStatement.setString(8, emp.getSalary());
        aPreparedStatement.setString(9, emp.getDepartment());

        if (!aPreparedStatement.execute()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        aDBConnection = DBConnection.getInstance();
        aPreparedStatement
                = aDBConnection.getConnection().prepareStatement("DELETE FROM EMPLOYEES WHERE ID=?");
        aPreparedStatement.setString(1, id);

        int affectedRows = aPreparedStatement.executeUpdate();

        return affectedRows != 0;

    }

    public Employee getEmployeeByID(String ID) throws SQLException, ClassNotFoundException {
        aDBConnection = DBConnection.getInstance();
//        aPreparedStatement
//                = aDBConnection.getConnection().prepareStatement("SELECT * FROM EMPLOYEES WHERE ID=?");
//        aPreparedStatement.setString(1, ID);
//        aResultSet = aPreparedStatement.executeQuery();
//       
        String query = ("SELECT * FROM EMPLOYEES WHERE ID="+ID);
        aStatement = aDBConnection.getStatement();
        aResultSet = aStatement.executeQuery(query);
        
        
        Employee emp = new Employee();
        emp.setID("0");
        
//        aResultSet.beforeFirst();
aResultSet.beforeFirst();
        if(aResultSet.next()){
            System.out.println("ITEM FOUND!@!!");
        String id = String.valueOf(aResultSet.getInt("ID"));
        String fName = aResultSet.getString("FName");
        String mName = aResultSet.getString("MName");
        String lName = aResultSet.getString("LName");
        String email = aResultSet.getString("eMail");
        String phone = aResultSet.getString("PhoneNumber");
        String age = aResultSet.getString("Age");
        String position = aResultSet.getString("Position");
        String dep = aResultSet.getString("Department");
        String sal = aResultSet.getString("Salary");
        String absent = aResultSet.getString("AbsentDays");
        
        emp = new Employee(id, fName, mName, lName, email, phone, position, sal, age, dep, absent);
        }
        else{
            System.out.println("NO ITEMS");
        }
        return emp;
    }
}
