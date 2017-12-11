/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SubControllers;

import Controller.MainControllers.MainController;
import Entities.Employee;
import Model.DBFacade;
import View.Frames.AppFrame;
import View.Frames.FrameFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aburom
 */
public class UpdateEmployeeController {
    static UpdateEmployeeController updateEmployeeController = null;
    FrameFactory aFactory = new FrameFactory();
    AppFrame searchFrame;
    AppFrame updateFrame;
    DBFacade db = DBFacade.getDBFacade();

    private UpdateEmployeeController() {
        searchFrame = aFactory.getFrame("search");
        updateFrame = aFactory.getFrame("update");
        searchFrame.setConfirmActionListener(searchAction());
        searchFrame.setCancelActionListener(cancelSearchAction());
        updateFrame.setConfirmActionListener(updateAction());
        updateFrame.setCancelActionListener(cancelUpdateAction());
        showSearchFrame();
    }

    public static UpdateEmployeeController getUpdateEmployeeController() {
        if (updateEmployeeController == null) {
            updateEmployeeController = new UpdateEmployeeController();
        }
        updateEmployeeController.showSearchFrame();
        return updateEmployeeController;
    }

    public void showSearchFrame() {
        searchFrame.showFrame();
    }

    public void hideSearchFrame() {
        searchFrame.hideFrame();
    }

    public void showUpdateFrame() {
        updateFrame.showFrame();
    }

    public void hideUpdateFrame() {
        updateFrame.hideFrame();
    }
    
    
    
    public ActionListener searchAction(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    Employee emp = db.getEmployeeByID(searchFrame.getID());
                 
                    if(emp.getID()!="0"){
                        JOptionPane.showMessageDialog(null, "Employee Exists");
                        hideSearchFrame();
                        updateFrame.setEmployeeData(emp);
                        showUpdateFrame();
                        searchFrame.clearFields();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Employee Doesn't Exist");
                    }
                        
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdateEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        };
    }
    
        public ActionListener updateAction(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                  
                    Employee emp = updateFrame.getEmployeeData();
                    if(db.updateEmployee(emp)){
                        JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
                        hideUpdateFrame();
                        MainController.getMainController();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Update Not Successful");
                    }
                        
                } catch (SQLException ex) {
                    Logger.getLogger(UpdateEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UpdateEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        };
    }

    private ActionListener cancelSearchAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideSearchFrame();
            }
        };
    }
    
    private ActionListener cancelUpdateAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideUpdateFrame();
            }
        };
}
}
