/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SubControllers;

import Controller.MainControllers.MainController;
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
public class AddEmployeeController {

    static AddEmployeeController addEmployeeController = null;
    FrameFactory aFactory = new FrameFactory();
    AppFrame addFrame;
    DBFacade db = DBFacade.getDBFacade();

    private AddEmployeeController() {
        addFrame = aFactory.getFrame("add");
        addFrame.setConfirmActionListener(addEmployeeActionListener());
        addFrame.setCancelActionListener(cancelEmployeeActionListener());
        showAddEmployeeFrame();
    }

    public static AddEmployeeController getAddEmployeeController() {
        if (addEmployeeController == null) {
            addEmployeeController = new AddEmployeeController();
        }
        addEmployeeController.showAddEmployeeFrame();
        return addEmployeeController;
    }

    public void showAddEmployeeFrame() {
        addFrame.showFrame();
    }

    public void hideAddEmployeeFrame() {
        addFrame.hideFrame();
    }

    public void addEmployee() throws SQLException, ClassNotFoundException {

        if (addFrame.validateEmployeeFields()) {
            if (db.addEmployee(addFrame.getEmployeeData())) {
                JOptionPane.showMessageDialog(null, "Employee Added Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Employee Not Added Successfully");

            }
        }else{
            JOptionPane.showMessageDialog(null, "Invalid Data!");
        }
    }

    public ActionListener addEmployeeActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addEmployee();
                    MainController.fillTable();
                } catch (SQLException ex) {
                    Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    public ActionListener cancelEmployeeActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideAddEmployeeFrame();
            }
        };
    }

}
