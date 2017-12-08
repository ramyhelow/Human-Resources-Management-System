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
public class DeleteEmployeeController {

    static DeleteEmployeeController deleteEmployeeController = null;
    FrameFactory aFactory = new FrameFactory();
    AppFrame deleteFrame;
    DBFacade db = DBFacade.getDBFacade();

    private DeleteEmployeeController() {
        //     aFrameFacade.getAddEmployeeFrame();
        deleteFrame = aFactory.getFrame("delete");
        deleteFrame.setConfirmActionListener(deleteEmployeeActionListener());
        deleteFrame.setCancelActionListener(cancelEmployeeActionListener());
        showDeleteEmployeeFrame();
    }

    public static DeleteEmployeeController getDeleteEmployeeController() {
        if (deleteEmployeeController == null) {
            deleteEmployeeController = new DeleteEmployeeController();
        }
        deleteEmployeeController.showDeleteEmployeeFrame();
        return deleteEmployeeController;
    }

    public void showDeleteEmployeeFrame() {
        deleteFrame.showFrame();
    }

    public void hideDeleteEmployeeFrame() {
        deleteFrame.hideFrame();
    }

    public void deleteEmployee() throws SQLException, ClassNotFoundException {

        if (db.deleteEmployee(deleteFrame.getID())) {
            JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Employee Doesnt Exist!");

        }
    }

    public ActionListener deleteEmployeeActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteEmployee();
                    MainController.getMainController();
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
                hideDeleteEmployeeFrame();
            }
        };
    }
}
