/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author aburom
 */
public class DBUsers {

    private DBConnection aDBConnection;
    private Statement aStatement;
    private ResultSet aResultSet;

    public boolean verifyUser(String username, String password) throws SQLException, ClassNotFoundException {
        aDBConnection = DBConnection.getInstance();
        aStatement = aDBConnection.getStatement();
        aResultSet = aStatement.
                executeQuery("Select * from USERS where USERNAME='" + username + "' and PASSWORD='" + password + "'");
        if (aResultSet.next()) {
            return true;
        }
        return false;
    }
}
