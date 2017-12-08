/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.MainControllers;

import Model.DBFacade;
import View.Frames.AppFrame;
import View.Frames.FrameFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aburom
 */
public class LoginController {

    static LoginController loginController = null;
    FrameFactory aFactory = new FrameFactory();
    DBFacade db = DBFacade.getDBFacade();
    AppFrame loginFrame;
    
    
    public static void main(String[] args) {
        new LoginController();
    }
    

    private LoginController() {
        loginFrame = aFactory.getFrame("login");
        loginFrame.setConfirmActionListener(LoginButtonAction());
        showLoginFrame();
    }
    
    public static LoginController getLoginController(){
        if(loginController==null){
            loginController=new LoginController();
        }
        return loginController;
    }

    public void showLoginFrame() {
        loginFrame.showFrame();
    }
//
    public void hideLoginFrame() {
        loginFrame.hideFrame();
    }

    public void loginVerification() throws SQLException, ClassNotFoundException {
        String username = loginFrame.getUsername();
        String password = loginFrame.getPassword();

        boolean verified = db.verifyUser(username, password);
        
        if(verified){
//            JOptionPane.showMessageDialog(null, "Success!");
            loginFrame.hideFrame();
            MainController.getMainController();
//            aFrameFacade.hideLoginFrame();
//            aFrameFacade.showMainFrame();
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }

    }

    public MouseListener LoginButtonAction() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    loginVerification();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }
}
