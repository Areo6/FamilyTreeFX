/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.personfxeditor;

import com.asgteach.familytreefx.model.User;
import com.asgteach.familytreefx.model.UserRole;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Developer
 */

public class LoginController implements Initializable {
    @FXML
    private Button loginButon;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label errorLabel;      
     
    UserRole userRole=null;
    private final InstanceContent instanceController=new InstanceContent();
  
    
    
    @FXML
    private void loginButtonAction(ActionEvent evt) {
           
        if (userRole != null) {
            User thisUser = userRole.findUser(usernameTextField.getText(), passwordTextField.getText());
            WindowManager wm=WindowManager.getDefault();
              if (thisUser != null) {
                // switch to new role
                wm.setRole(thisUser.getRole());
            } else {
                errorLabel.setText("Incorrect login for user " + usernameTextField.getText());
                                   
                if (wm.getRole() != null) {
                   
                    wm.setRole(null);
                }
            }
        }
           
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       userRole =Lookup.getDefault().lookup(UserRole.class);
            
    }

    AbstractLookup.Content getInstanceContent() {
        return instanceController;
    }
     
}
