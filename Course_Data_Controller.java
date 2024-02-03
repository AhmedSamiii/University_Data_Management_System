/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataAccessLayer.DataAccessLayer;
import DTOs.Courses;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class Course_Data_Controller implements Initializable {

    @FXML
    private Button se_btn;
    @FXML
    private Button in_btn;
    @FXML
    private Button up_btn;
    @FXML
    private Button de_btn;
    @FXML
    private TextField cours_id;
    @FXML
    private TextField cours_name;
    @FXML
    private TextField deptid;
    @FXML
    private TextField cours_hrs;
    @FXML
    private TextField cours_gr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         in_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Courses course = new Courses(
                            Integer.parseInt(cours_id.getText()),
                            Integer.parseInt(deptid.getText()),
                            Integer.parseInt(cours_hrs.getText()),
                            Integer.parseInt(cours_gr.getText()),
                            cours_name.getText()
                    );
                    DataAccessLayer.addCourse(course);

                    // Clear fields after adding
                    cours_id.clear();
                    deptid.clear();
                    cours_hrs.clear();
                    cours_gr.clear();
                    cours_name.clear();
                } catch (SQLException ex) {
                    Logger.getLogger(Course_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         up_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            int courseId = Integer.parseInt(cours_id.getText());
            String courseName = cours_name.getText();
            int departmentId = Integer.parseInt(deptid.getText());
            int creditHours = Integer.parseInt(cours_hrs.getText());
            int finalGrade = Integer.parseInt(cours_gr.getText());

            Courses course = new Courses(courseId, departmentId, creditHours, finalGrade, courseName);

            DataAccessLayer.updateAllCourseInfo(course);

            // Clear fields after updating
            cours_id.clear();
            cours_name.clear();
            deptid.clear();
            cours_hrs.clear();
            cours_gr.clear();
        } catch (SQLException ex) {
            Logger.getLogger(Course_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});

        

        de_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int courseId = Integer.parseInt(cours_id.getText());
                    DataAccessLayer.deleteCourse(courseId);

                    // Clear fields after deleting
                    cours_id.clear();
                    deptid.clear();
                    cours_hrs.clear();
                    cours_gr.clear();
                    cours_name.clear();
                } catch (SQLException ex) {
                    Logger.getLogger(Course_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        se_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            int courseId = Integer.parseInt(cours_id.getText());
            Courses course = DataAccessLayer.getCourseInfo(courseId);

            if (course != null) {
                // Populate text fields with course information
                cours_name.setText(course.getCOURSE_NAME());
                deptid.setText(String.valueOf(course.getDEPARTMENT_ID()));
                cours_hrs.setText(String.valueOf(course.getCREDIT_HOURS()));
                cours_gr.setText(String.valueOf(course.getFINAL_GRADE()));
            } else {
                // Handle the case where the course information is not found
                // You may show an error message or take appropriate action
                // Clear text fields
                cours_name.clear();
                deptid.clear();
                cours_hrs.clear();
                cours_gr.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});

    }    
    
}
