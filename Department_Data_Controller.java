/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DTOs.Departments;
import DataAccessLayer.DataAccessLayer;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class Department_Data_Controller implements Initializable {

    @FXML
    private TextField dept_id;
    @FXML
    private TextField deptname;
    @FXML
    private Button se_btn;
    @FXML
    private Button in_btn;
    @FXML
    private Button up_btn;
    @FXML
    private Button de_btn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
       in_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
           
            @Override
            public void handle(ActionEvent event) {
                if (!validateInput("insert")) {
            return; // Stop execution if validation fails
        }
                try {
                    Departments department = new Departments(
                            Integer.parseInt(dept_id.getText()),
                            // Assuming Department_ID is an integer, adjust accordingly
                            deptname.getText()
                    );

                    DataAccessLayer.addDept(department);
                    dept_id.clear();
                    deptname.clear();

                        } catch (SQLException ex) {
                    if (ex.getMessage().contains("SYS_C007055")) {
                        showAlert("Error", "Duplicate Department ID. Please provide a unique Department ID.");
                    } else {
                        showAlert("Error", "Database error. " + ex.getMessage());
                    }
                }
            }
        });
       
       se_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validateInput("select")) {
            return; // Stop execution if validation fails
        }
                try {

                    String Depatmnet_Name =DataAccessLayer.getDepartmentName(Integer.parseInt(dept_id.getText()));
                    deptname.setText(Depatmnet_Name);       

                } catch (SQLException ex) {
                    Logger.getLogger(Department_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
       up_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validateInput("update")) {
            return; // Stop execution if validation fails
        }
                try {
                    Departments department = new Departments(
                            Integer.parseInt(dept_id.getText()),
                            // Assuming Department_ID is an integer, adjust accordingly
                            deptname.getText()
                    );

                    DataAccessLayer.updateDepartmentName(department);
                    dept_id.clear();
                    deptname.clear();

                } catch (SQLException ex) {
                  
                    Logger.getLogger(Department_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
       
       de_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        if (!validateInput("delete")) {
            return; // Stop execution if validation fails
        }
        try {
            int departmentId = Integer.parseInt(dept_id.getText());

            Departments department = new Departments(departmentId);

            
                DataAccessLayer.deldepartment(department);
                dept_id.clear();
            

        } catch (SQLException ex) {
            Logger.getLogger(Department_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});



       
    }
private boolean validateInput(String operation) {

    // Specific validation based on the operation
    switch (operation.toLowerCase()) {
        case "insert":
            if (dept_id.getText().isEmpty() || deptname.getText().isEmpty()) {
        showAlert("Error", "All fields must be filled in.");
        return false;
    }

    try {
        Integer.parseInt(dept_id.getText()); 
    } catch (NumberFormatException e) {
        showAlert("Error", " Department ID must be valid integer.");
        return false;
    }
            break;
        case "update":
            
                    if (dept_id.getText().isEmpty() || deptname.getText().isEmpty()) {
                showAlert("Error", "All fields must be filled in.");
                return false;
            }
                    try {
                Integer.parseInt(dept_id.getText()); 
            } catch (NumberFormatException e) {
                showAlert("Error", " Department ID must be valid integer.");
                return false;
            }
            // Add more specific validation for update if needed
            break;
        case "select":
                    if (dept_id.getText().isEmpty()) {
                showAlert("Error", "Department id field must be filled in.");
                return false;
            }
                    try {
                Integer.parseInt(dept_id.getText());
            } catch (NumberFormatException e) {
                showAlert("Error", "Department ID must be valid integer.");
                return false;
            }
            break;
        case "delete":
        if (dept_id.getText().isEmpty()) {
                showAlert("Error", "Department id field must be filled in.");
                return false;
            }
                    try {
                Integer.parseInt(dept_id.getText());
            } catch (NumberFormatException e) {
                showAlert("Error", "Department ID must be valid integer.");
                return false;
            }
            break;
        default:
            // Handle unsupported operation
            showAlert("Error", "Unsupported operation: " + operation);
            return false;
    }

    return true;
}




  private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }    
    // Method to show an alert
private void showAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
}
}
