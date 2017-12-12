/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Frames;

import Entities.Employee;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 *
 * @author aburom
 */
public interface AppFrame {
    
    public void showFrame();
    public void hideFrame();
    
    default public void setConfirmActionListener(ActionListener listener){}
    default public void setConfirmActionListener(MouseListener listener){}
    default public void setDeleteActionListener(ActionListener listener){}
    default public void setCancelActionListener(ActionListener listener){}
    
    default public void clearFields(){}
    
    default public void fillTable(Object[][] employeeData, String[] employeeColumns){}
    
    default public String getUsername(){
        return null;
    }
    default public String getPassword(){
        return null;
    }
    default public String getID(){
        return null;
    }
    default public Employee getEmployeeData(){
        return null;
    }
    default public void setEmployeeData(Employee emp){}
    
    default public void setLogoutListener(MouseListener listener){}
    
    default public void setAddIconListener(MouseListener listener){}
//    
//    default public void setUpdateIconListener(MouseListener listener){}
//    
//    default public void setDeleteIconListener(MouseListener listener){}
    
    default public void setSearchIconListener(MouseListener listener){}
    
    default public boolean validateFields(){
        return true;
    }
    
}
