/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataAccessLayer.DataAccessLayer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class Home_Page_Controller implements Initializable {

    @FXML
    private Button HomePage;
    @FXML
    private Button addstud_btn;
    @FXML
    private Button adddept_btn;
    @FXML
    private Text hiUserTxt;
    @FXML
    private Pane mainPane;
    @FXML
    private Button addcourse_btn;
    @FXML
    private Button add_enrollment_btn;
    @FXML
    private Button courses_report_btn;
    @FXML
    private Button Departments_report_btn;
    @FXML
    private Label SN;
    @FXML
    private Label DN;
    @FXML
    private Label CN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addstud_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Student_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        adddept_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Department_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        addcourse_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Courses_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Course_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        HomePage.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Welcome.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        add_enrollment_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Enrollments_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        courses_report_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Courses_Report_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        Departments_report_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent newContent = FXMLLoader.load(getClass().getResource("/Server/Departments_Report_Data.fxml"));
                    mainPane.getChildren().setAll(newContent);
                } catch (IOException ex) {
                    Logger.getLogger(Controllers.Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
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
