/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.MainControllers;

import Controller.SubControllers.AddEmployeeController;
import Controller.SubControllers.DeleteEmployeeController;
import Controller.SubControllers.EmployeeInfoController;
import Model.DBFacade;
import View.Frames.AppFrame;
import View.Frames.FrameFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aburom
 */
public class MainController {

    static MainController mainController = null;
    FrameFactory aFactory = new FrameFactory();
    static AppFrame mainFrame;
    static DBFacade db = DBFacade.getDBFacade();

        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        getMainController();
    }
    
    private MainController() throws SQLException, ClassNotFoundException {
        mainFrame = aFactory.getFrame("main");
        mainFrame.setLogoutListener(LogoutButtonAction());
        mainFrame.setAddIconListener(AddEmployeeAction());
        mainFrame.setSearchIconListener(SearchEmployeeAction());
        fillTable();
        showMainFrame();
    }
    
    public static MainController getMainController() throws SQLException, ClassNotFoundException{
        if(mainController==null){
            mainController=new MainController();
        }
        mainController.showMainFrame();
        return mainController;
    }
    
    public void showMainFrame() {
        mainFrame.showFrame();
    }

    public void hideMainFrame() {
        mainFrame.hideFrame();
    }

    public static void fillTable() throws SQLException, ClassNotFoundException {
        mainFrame.fillTable(db.getEmployeeData(), db.getEmployeeColumns());
    }

    public MouseListener LogoutButtonAction() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.hideFrame();
                mainFrame.clearFields();
                LoginController.getLoginController();
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

    public MouseListener AddEmployeeAction() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddEmployeeController.getAddEmployeeController();
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

    public MouseListener DeleteEmployeeAction() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleteEmployeeController.getDeleteEmployeeController();
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
    
    public MouseListener SearchEmployeeAction() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    EmployeeInfoController.getEmployeeInfoController();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
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
//    
//    public void setConfirmAddEmployeeButtonListener(ActionListener listener){
//        aFrameFacade.confirmAddEmployeeActionListener(listener);
//    }

}
