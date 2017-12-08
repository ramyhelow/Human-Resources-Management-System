/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Frames;

import View.Frames.MainFrames.LoginFrame;
import View.Frames.MainFrames.MainFrame;
import View.Frames.SubFrames.AddEmployeeFrame;
import View.Frames.SubFrames.DeleteEmployeeFrame;
import View.Frames.SubFrames.SearchEmployeeFrame;
import View.Frames.SubFrames.UpdateEmployeeFrame;

/**
 *
 * @author aburom
 */
public class FrameFactory {
//
//    private static FrameFactory frameFacade = null;
//    private LoginFrame loginFrame;
//    private MainFrame mainFrame;
//    private AddEmployeeFrame addEmployeeFrame;
//    private DeleteEmployeeFrame deleteEmployeeFrame;
//    

    public FrameFactory() {
//        loginFrame = new LoginFrame();
//        mainFrame = new MainFrame();
//        addEmployeeFrame = new AddEmployeeFrame();
//        deleteEmployeeFrame = new DeleteEmployeeFrame();
//        
    }

    public AppFrame getFrame(String frameName) {
        if (frameName.equalsIgnoreCase("login")) {
            return new LoginFrame();
        }
        else if (frameName.equalsIgnoreCase("main")) {
            return new MainFrame();
        }
        else if (frameName.equalsIgnoreCase("add")) {
            return new AddEmployeeFrame();
        }
        else if (frameName.equalsIgnoreCase("delete")) {
            return new DeleteEmployeeFrame();
        }
        else if (frameName.equalsIgnoreCase("search")) {
            return new SearchEmployeeFrame();
        }
        else if (frameName.equalsIgnoreCase("update")) {
            return new UpdateEmployeeFrame();
        }
        else {
            return null;
        }
    }

    ////////////////////LoginFrame/////////////////////////
//    public void showLoginFrame() {
//        loginFrame.setVisible(true);
//    }
//
//    public void hideLoginFrame() {
//        loginFrame.setVisible(false);
//        loginFrame.dispose();
//    }

//    public void setActionListenerLoginButton(MouseListener listener) {
//        loginFrame.setActionListenerLoginButton(listener);
//    }
//
//    public void emptyjTextFieldUsername() {
//        loginFrame.getjTextFieldUsername().setText("");
//    }
//
//    public void emptyjPasswordField() {
//        loginFrame.getjPasswordField().setText("");
//    }
//
//    public String getjPasswordFieldValue() {
//        return String.valueOf(loginFrame.getjPasswordField().getPassword());
//    }
//
//    public String getjTextFieldUsernameValue() {
//        return loginFrame.getjTextFieldUsername().getText();
//    }
    /////////////////MainFrame///////////////////////
//    public void showMainFrame() {
//        mainFrame.setVisible(true);
//    }
//
//    public void hideMainFrame() {
//        mainFrame.setVisible(false);
//        mainFrame.dispose();
//    }
//
//    public void setActionListenerLogoutButton(MouseListener listener) {
//        mainFrame.setActionListenerLogoutButton(listener);
//    }
//
//    public void setActionListenerAddButton(MouseListener listener) {
//        mainFrame.setActionListenerAddEmployeeButton(listener);
//    }
//
//    public void setActionListenerEditButton(MouseListener listener) {
//        mainFrame.setActionListenerEditEmployeeButton(listener);
//    }
//
//    public void setActionListenerSearchButton(MouseListener listener) {
//        mainFrame.setActionListenerSearchEmployeeButton(listener);
//    }
//
//    public void setActionListenerDeleteButton(MouseListener listener) {
//        mainFrame.setActionListenerDeleteButton(listener);
//    }
//
//    public void fillEmployeeTable(Object[][] employeeData, String[] employeeColumns) {
//        mainFrame.fillEmployeeTable(employeeData, employeeColumns);
//    }
//
//    public void emptyEmployeeTable() {
//        mainFrame.emptyEmployeeTable();
//    }
//
//    //////////////////////AddEmployeeFrame////////////////////////////
//    public void showAddEmployeeFrame() {
//        addEmployeeFrame.setVisible(true);
//    }
//
//    public void hideAddEmployeeFrame() {
//        addEmployeeFrame.setVisible(false);
//        addEmployeeFrame.dispose();
//    }
//
//    public void confirmAddEmployeeActionListener(ActionListener listener) {
//        addEmployeeFrame.confirmAddEmployeeActionListener(listener);
//    }
//
//    public void cancelAddEmployeeActionListener(ActionListener listener) {
//        addEmployeeFrame.cancelAddEmployeeActionListener(listener);
//    }
//
//    public Employee getEmployeeInfo() {
//        return addEmployeeFrame.getEmployeeInfo();
//    }
//
//    public void clearEmployeeInfo() {
//        addEmployeeFrame.clearEmployeeInfo();
//    }
//
//    /////////////////////DeleteEmployeeFrame///////////////////////////////////////////
//    public void showDeleteEmployeeFrame() {
//        deleteEmployeeFrame.setVisible(true);
//    }
//
//    public void hideDeleteEmployeeFrame() {
//        deleteEmployeeFrame.setVisible(false);
//        deleteEmployeeFrame.dispose();
//    }
//
//    public void confirmDeleteEmployeeActionListener(ActionListener listener) {
//        deleteEmployeeFrame.confirmDeleteEmployeeActionListener(listener);
//    }
//
//    public void cancelDeleteEmployeeActionListener(ActionListener listener) {
//        deleteEmployeeFrame.cancelDeleteEmployeeActionListener(listener);
//    }
//
//    public String getID() {
//        return deleteEmployeeFrame.getjTextFieldID();
//    }

    //////////////////////////////////////////////////////////////////////////////////////
}
