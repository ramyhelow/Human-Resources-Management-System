/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.MainControllers.LoginController;
import java.sql.SQLException;



/**
 *
 * @author aburom
 */
public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            LoginController loginController = LoginController.getLoginController();
            loginController.showLoginFrame();

//        }
    }
}
