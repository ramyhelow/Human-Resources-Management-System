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
public class EmployeeInfoController {

    static EmployeeInfoController EmployeeInfoController = null;
    FrameFactory aFactory = new FrameFactory();
    AppFrame searchFrame;
    AppFrame employeeInfoFrame;
    DBFacade db = DBFacade.getDBFacade();

    private EmployeeInfoController() {
        searchFrame = aFactory.getFrame("search");
        employeeInfoFrame = aFactory.getFrame("update");
        searchFrame.setConfirmActionListener(searchAction());
        searchFrame.setCancelActionListener(cancelSearchAction());
        employeeInfoFrame.setConfirmActionListener(updateAction());
        employeeInfoFrame.setCancelActionListener(cancelUpdateAction());
        employeeInfoFrame.setDeleteActionListener(deleteAction());
        showSearchFrame();
    }

    public static EmployeeInfoController getEmployeeInfoController() {
        if (EmployeeInfoController == null) {
            EmployeeInfoController = new EmployeeInfoController();
        }
        EmployeeInfoController.showSearchFrame();
        return EmployeeInfoController;
    }

    public void showSearchFrame() {
        searchFrame.showFrame();
    }

    public void hideSearchFrame() {
        searchFrame.hideFrame();
    }

    public void showUpdateFrame() {
        employeeInfoFrame.showFrame();
    }

    public void hideUpdateFrame() {
        employeeInfoFrame.hideFrame();
    }

    public ActionListener searchAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Employee emp = db.getEmployeeByID(searchFrame.getID());

                    if (emp.getID() != "0") {
                        JOptionPane.showMessageDialog(null, "Employee Exists");
                        hideSearchFrame();
                        employeeInfoFrame.setEmployeeData(emp);
                        showUpdateFrame();
                        searchFrame.clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee Doesn't Exist");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
    }

    public ActionListener updateAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Employee emp = employeeInfoFrame.getEmployeeData();
                    if (employeeInfoFrame.validateFields()) {
                        if (db.updateEmployee(emp)) {
                            JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
                            hideUpdateFrame();
                            MainController.getMainController();
                        } else {
                            JOptionPane.showMessageDialog(null, "Update Not Successful");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Data!");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
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

    private ActionListener deleteAction() {
        System.out.println("Delete Button Pressed");
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(employeeInfoFrame.getID());
                if(JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete Employee No. "+id+" ?")==JOptionPane.YES_OPTION){
                    try {
                        
                        db.deleteEmployee(String.valueOf(id));
                        JOptionPane.showMessageDialog(null, "Employee Deleted");
                        employeeInfoFrame.hideFrame();
                        MainController.getMainController();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        };
    }
}
