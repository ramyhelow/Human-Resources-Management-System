/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import Entities.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aburom
 */
public class DBReports {

    private DBConnection aDBConnection;
    private Statement aStatement;
    private PreparedStatement aPreparedStatement;
    private ResultSet aResultSet;
    private ResultSetMetaData aResultSetMetaData;

    public Object[][] getReports(String emp_id) throws SQLException, ClassNotFoundException {

        aDBConnection = DBConnection.getInstance();
        aStatement = aDBConnection.getStatement();
        aResultSet = aStatement.executeQuery("Select * from REPORTS where EMP_ID='" + emp_id +"'");

        aResultSetMetaData = aResultSet.getMetaData();
        int columnCount = aResultSetMetaData.getColumnCount();

        aResultSet.last();
        Object[][] data = new Object[aResultSet.getRow()][columnCount + 1];
        int i = 0;
        aResultSet.beforeFirst();
        while (aResultSet.next()) {
            data[i][0] = aResultSet.getString("REP_ID");
            data[i][1] = aResultSet.getString("NAME");
            data[i][2] = aResultSet.getString("TYPE");
            String timestamp = aResultSet.getTimestamp("DATEADDED").toString();
            data[i][3] = timestamp.substring(0, timestamp.indexOf(" "));
            i++;
        }

        return data;
    }

    public String[] getReportColumns() {
        return new String[]{
            "Rep_ID", "Name", "Type","Date"
        };
    }
    
    public boolean addReport(Report report) throws SQLException, ClassNotFoundException{
        aDBConnection = DBConnection.getInstance();
        aPreparedStatement
                = aDBConnection.getConnection().prepareStatement("INSERT INTO REPORTS (EMP_ID,NAME,TYPE,TEXT) VALUES (?,?,?,?)");
        aPreparedStatement.setString(1, report.getEmp_id());
        aPreparedStatement.setString(2, report.getName());
        aPreparedStatement.setString(3, report.getType());
        aPreparedStatement.setString(4, report.getText());

        if (!aPreparedStatement.execute()) {
            return true;
        } else {
            return false;
        }
    }
    
    public ResultSet getReportByID(String REP_ID) throws SQLException, ClassNotFoundException{
        aDBConnection = DBConnection.getInstance();
        aStatement = aDBConnection.getStatement();
        aResultSet = aStatement.
                executeQuery("Select * from REPORTS where REP_ID='" + REP_ID +"'");
        return aResultSet;
    }
}
