/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.Home_Page_Controller;
import DataAccessLayer.DataAccessLayer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class WelcomeController implements Initializable {

    @FXML
    private Label SN;
    @FXML
    private Label DN;
    @FXML
    private Label CN;
    @FXML
    private Label SN1;
    @FXML
    private Label SN11;
    @FXML
    private Label SN111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Integer student_no = DataAccessLayer.getNoOfStudents();
            System.out.println(student_no);
            SN.setText(String.format("%d", student_no)); // Convert Integer to String using String.format
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                try {
            Integer Department_no = DataAccessLayer.getNoOfDepartments();
            System.out.println(Department_no);
            DN.setText(String.format("%d", Department_no)); // Convert Integer to String using String.format
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                try {
            Integer Course_no = DataAccessLayer.getNoOfCourses();
            System.out.println(Course_no);
            CN.setText(String.format("%d", Course_no)); // Convert Integer to String using String.format
        } catch (SQLException ex) {
            Logger.getLogger(Home_Page_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
