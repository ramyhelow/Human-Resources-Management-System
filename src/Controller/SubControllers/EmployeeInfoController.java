/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SubControllers;

import Controller.MainControllers.MainController;
import Entities.Employee;
import Entities.Report;
import Model.DBFacade;
import View.Frames.AppFrame;
import View.Frames.FrameFactory;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author aburom
 */
public class EmployeeInfoController {

    static EmployeeInfoController EmployeeInfoController = null;
    FrameFactory aFactory = new FrameFactory();
    AppFrame searchFrame;
    static AppFrame employeeInfoFrame;
    static DBFacade db = DBFacade.getDBFacade();

    private EmployeeInfoController() throws SQLException, ClassNotFoundException {
        searchFrame = aFactory.getFrame("search");
        employeeInfoFrame = aFactory.getFrame("update");
        searchFrame.setConfirmActionListener(searchAction());
        searchFrame.setCancelActionListener(cancelSearchAction());
        employeeInfoFrame.setConfirmActionListener(updateAction());
        employeeInfoFrame.setCancelActionListener(cancelUpdateAction());
        employeeInfoFrame.setDeleteActionListener(deleteAction());
        employeeInfoFrame.setSaveReportActionListener(saveReport());
        employeeInfoFrame.setClearReportActionListener(clearAction());
        employeeInfoFrame.setJTableMouseAdapter(JTableAction());
        showSearchFrame();
    }

    public static EmployeeInfoController getEmployeeInfoController() throws SQLException, ClassNotFoundException {
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

    public static void showEmployeeFrame(Employee emp) throws SQLException, ClassNotFoundException {
        getEmployeeInfoController();
        employeeInfoFrame.setEmployeeData(emp);
        EmployeeInfoController.hideSearchFrame();
        employeeInfoFrame.showFrame();
        fillTable(employeeInfoFrame.getID());
    }

    public void hideEmployeeFrame() throws SQLException, ClassNotFoundException {
        employeeInfoFrame.hideFrame();
        MainController.getMainController();
    }

    public ActionListener searchAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Employee emp = db.getEmployeeByID(searchFrame.getID());

                    if (emp.getID() != "0") {
                        hideSearchFrame();
                        showEmployeeFrame(emp);
                        searchFrame.clearFields();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
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
                    if (employeeInfoFrame.validateEmployeeFields()) {
                        if (db.updateEmployee(emp)) {
                            JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
                            hideEmployeeFrame();
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
                try {
                    hideEmployeeFrame();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EmployeeInfoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    private ActionListener deleteAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(employeeInfoFrame.getID());
                if (JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete Employee No. " + id + " ?") == JOptionPane.YES_OPTION) {
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

    private ActionListener saveReport() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Report report = employeeInfoFrame.getReportData();
                    if (employeeInfoFrame.validateReportFields()) {
                        if (db.addReport(report)) {
                            JOptionPane.showMessageDialog(null, "Report Added Successfully");
                            fillTable(employeeInfoFrame.getID());
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

    public static void fillTable(String EMP_ID) throws SQLException, ClassNotFoundException {
        employeeInfoFrame.fillTable(db.getReports(EMP_ID), db.getReportColumns());
    }

    private ActionListener clearAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeeInfoFrame.clearFields();
            }
        };
    }
    
    public MouseAdapter JTableAction() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    try {
                        ResultSet rs = db.getReportByID(String.valueOf(table.getValueAt(row, 0)));
                        rs.first();
                        JOptionPane.showMessageDialog(null, rs.getString("TEXT"));
                    } catch (SQLException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

}
