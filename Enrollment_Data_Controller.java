/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DTOs.Enrollments;
import DataAccessLayer.DataAccessLayer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class Enrollment_Data_Controller implements Initializable {

    private TextField cours_id;
    @FXML
    private Text GPA;
    @FXML
    private Button addcourse_btn;
    @FXML
    private Button delcourse_btn;
    @FXML
    private Button addgrade_btn;
    @FXML
    private Button caclgpa_btn;
    @FXML
    private TextField grade;
    @FXML
    private TextField year;
    @FXML
    private TextField student_id;
    @FXML
    private TextField course_id;
    @FXML
    private TextField semester;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addcourse_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            Enrollments enrollment = new Enrollments(
                // Assuming ASSIGN_DATE is an integer, adjust accordingly
                Integer.parseInt(student_id.getText()),
                Integer.parseInt(course_id.getText()),
                Integer.parseInt(year.getText()),
                Integer.parseInt(semester.getText())
                
            );

            int result = DataAccessLayer.addCourseForStudent(enrollment);
                    student_id.clear();
                    course_id.clear();
                    year.clear();
                    semester.clear();
            // Handle result accordingly (show message, update UI, etc.)
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
});

addgrade_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            Enrollments enrollment = new Enrollments(
                Integer.parseInt(grade.getText()),    
                Integer.parseInt(student_id.getText()),
                Integer.parseInt(course_id.getText()),
                Integer.parseInt(year.getText()),
                Integer.parseInt(semester.getText())
            );

            int result = DataAccessLayer.addGradeForCourse(enrollment);
                   student_id.clear();
                    course_id.clear();
                    year.clear();
                    semester.clear();
                    grade.clear();

            // Handle result accordingly (show message, update UI, etc.)
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
});

caclgpa_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        try {
            // Assuming studentId is obtained from somewhere, adjust accordingly
            int studentId = Integer.parseInt(student_id.getText());

            double gpa = DataAccessLayer.calculateStudentGPA(studentId);

            // Update the GPA text field
            GPA.setText(String.valueOf(gpa));
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
});

delcourse_btn.setOnAction(event -> {
    try {
            Enrollments enrollment = new Enrollments(
                Integer.parseInt(student_id.getText()),
                Integer.parseInt(course_id.getText())
            );
        DataAccessLayer.deldeStudentCourse(enrollment);
        student_id.clear();
        course_id.clear();

    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
});
    }    
    
}
