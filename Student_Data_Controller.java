/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DTOs.Enrollments;
import DTOs.Students;
import DataAccessLayer.DataAccessLayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ahmed Samy
 */
public class Student_Data_Controller implements Initializable {

    @FXML
    private TextField stu_id;
    @FXML
    private TextField stu_fname;
    @FXML
    private TextField stu_lname;
    @FXML
    private TextField stud_dept;
    @FXML
    private TextField stu_pho;
    @FXML
    private TextField stu_add;
    @FXML
    private TextField stu_dob;
    @FXML
    private Button se_btn;
    @FXML
    private Button in_btn;
    @FXML
    private Button up_btn;
    @FXML
    private Button de_btn;
    @FXML
    private TableView<Students> studentsTableView;
    @FXML
    private TableColumn<Students, Integer> id;
    @FXML
    private TableColumn<Students, String> fname;
    @FXML
    private TableColumn<Students, String> lname;
    @FXML
    private TableColumn<Students, Integer> dept_id;
    @FXML
    private TableColumn<Students, String> phone;
    @FXML
    private TableColumn<Students, String> add;
    @FXML
    private TableColumn<Students, String> dob;
    @FXML
    private TableView<Enrollments> coursesTableView;
    @FXML
    private TableColumn<Enrollments, Integer> course_id;
    @FXML
    private TableColumn<Enrollments, String> Course_name;
    @FXML
    private TableColumn<Enrollments, Integer> Numeric_Grade;
    @FXML
    private TableColumn<Enrollments, String> Grade;
    @FXML
    private TableColumn<Enrollments, Integer> Year;
    @FXML
    private Text GPA;
    @FXML
    private TableColumn<Enrollments, Integer> semester;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             
        try {
            // Fetch all students from the database
            List<Students> allStudents = DataAccessLayer.getAllStudents();
            
            // Populate the Students_table
            ObservableList<Students> studentsList = FXCollections.observableArrayList(allStudents);
            id.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
            fname.setCellValueFactory(new PropertyValueFactory<>("FNAME"));
            lname.setCellValueFactory(new PropertyValueFactory<>("LNAME"));
            dept_id.setCellValueFactory(new PropertyValueFactory<>("Department_ID"));
            phone.setCellValueFactory(new PropertyValueFactory<>("PHONE"));
            add.setCellValueFactory(new PropertyValueFactory<>("ADDRESS"));
            dob.setCellValueFactory(new PropertyValueFactory<>("DOB"));
            studentsTableView.setItems(studentsList);
            
        } catch (SQLException ex) {
            Logger.getLogger(Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        studentsTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if (newValue != null) {
        try {
            System.out.println(newValue.getStudent_ID());
            List<Enrollments> allEnrollments = DataAccessLayer.getEnrollmentsWithCourseInfo(newValue.getStudent_ID());
            double gpa = DataAccessLayer.calculateStudentGPA(newValue.getStudent_ID());
            ObservableList<Enrollments> enrollmentsList = FXCollections.observableArrayList(allEnrollments);
            
            // Use uppercase property names in PropertyValueFactory
            course_id.setCellValueFactory(new PropertyValueFactory<>("COURSE_ID"));
            Course_name.setCellValueFactory(new PropertyValueFactory<>("COURSE_NAME"));
            Numeric_Grade.setCellValueFactory(new PropertyValueFactory<>("NUMERIC_GRADE"));
            Grade.setCellValueFactory(new PropertyValueFactory<>("GRADE"));
            Year.setCellValueFactory(new PropertyValueFactory<>("ASSIGN_DATE"));
            semester.setCellValueFactory(new PropertyValueFactory<>("SEMESTER"));
            GPA.setText(String.valueOf(gpa));
            
            
            
            coursesTableView.setItems(enrollmentsList);
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle the exception appropriately
        }
    }
});



                in_btn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        if (!validateInput("insert")) {
            return; // Stop execution if validation fails
        }

        try {
            Students student = new Students(
                    Integer.parseInt(stu_id.getText()),
                    // Assuming Department_ID is an integer, adjust accordingly
                    Integer.parseInt(stud_dept.getText()),
                    stu_fname.getText(),
                    stu_lname.getText(),
                    stu_pho.getText(),
                    stu_add.getText(),
                    stu_dob.getText()
            );

            DataAccessLayer.addStudent(student);

            stu_id.clear();
            stu_fname.clear();
            stu_lname.clear();
            stud_dept.clear();
            stu_pho.clear();
            stu_add.clear();
            stu_dob.clear();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("SYS_C007055")) {
                showAlert("Error", "Duplicate Student ID. Please provide a unique Student ID.");
            } else if (ex.getMessage().contains("SYS_C007078")) {
                showAlert("Error", "Duplicate Phone Number. Please provide a unique Phone Number.");
            } else {
                showAlert("Error", "Database error. " + ex.getMessage());
            }
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
                    Students student = new Students(
                            Integer.parseInt(stu_id.getText()),
                            // Assuming Department_ID is an integer, adjust accordingly
                            Integer.parseInt(stud_dept.getText()),
                            stu_pho.getText(),
                            stu_add.getText()
                    );

                    DataAccessLayer.upStudent(student);

                    stu_id.clear();
                    stud_dept.clear();
                    stu_pho.clear();
                    stu_add.clear();
                } catch (SQLException ex) {
              if (ex.getMessage().contains("SYS_C007078")) {
                showAlert("Error", "Duplicate Phone Number. Please provide a unique Phone Number.");
            } else {
                showAlert("Error", "Database error. " + ex.getMessage());
            }
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
                    Students student = new Students(
                            Integer.parseInt(stu_id.getText())
                    );
                        DataAccessLayer.delStudent(student);
                    stu_id.clear();

                } catch (SQLException ex) {
                    Logger.getLogger(Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
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
                Students student = DataAccessLayer.getStudentInfo(Integer.parseInt(stu_id.getText()));
                stu_fname.setText(student.getFNAME());
                stu_lname.setText(student.getLNAME());
                stud_dept.setText(String.valueOf(student.getDepartment_ID()));
                stu_pho.setText(student.getPHONE());
                stu_add.setText(student.getADDRESS());
                stu_dob.setText(student.getDOB());
                    
                  
                } catch (SQLException ex) {
                    Logger.getLogger(Student_Data_Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        studentsTableView.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile = getClass().getResource("blueTable.css").toExternalForm();
        studentsTableView.getStylesheets().add(cssFile);
        
        coursesTableView.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile1 = getClass().getResource("blueTable.css").toExternalForm();
        coursesTableView.getStylesheets().add(cssFile1);
        
        
    }


    
    
    private boolean validateInput(String operation) {

    // Specific validation based on the operation
    switch (operation.toLowerCase()) {
        case "insert":
            if (stu_id.getText().isEmpty() || stud_dept.getText().isEmpty() || stu_fname.getText().isEmpty()
            || stu_lname.getText().isEmpty() || stu_pho.getText().isEmpty() || stu_add.getText().isEmpty()
            || stu_dob.getText().isEmpty()) {
        showAlert("Error", "All fields must be filled in.");
        return false;
    }

    try {
        Integer.parseInt(stu_id.getText());
        Integer.parseInt(stud_dept.getText());
        Integer.parseInt(stu_pho.getText());
    } catch (NumberFormatException e) {
        showAlert("Error", "Student ID, Department ID, Phone Number must be valid integers.");
        return false;
    }
            break;
        case "update":
            
            if (stu_id.getText().isEmpty() || stud_dept.getText().isEmpty() 
            || stu_pho.getText().isEmpty() || stu_add.getText().isEmpty()) {
        showAlert("Error", "student id , phone , department number , student address fields must be filled in.");
        return false;
    }
            try {
        Integer.parseInt(stu_id.getText());
        Integer.parseInt(stud_dept.getText());
        Integer.parseInt(stu_pho.getText());
    } catch (NumberFormatException e) {
        showAlert("Error", "Student ID, Department ID, Phone Number must be valid integers.");
        return false;
    }
            // Add more specific validation for update if needed
            break;
        case "select":
            if (stu_id.getText().isEmpty()) {
        showAlert("Error", "student id field must be filled in.");
        return false;
    }
            try {
        Integer.parseInt(stu_id.getText());
    } catch (NumberFormatException e) {
        showAlert("Error", "Student ID must be valid integer.");
        return false;
    }
            break;
        case "delete":
if (stu_id.getText().isEmpty()) {
        showAlert("Error", "student id field must be filled in.");
        return false;
    }
            try {
        Integer.parseInt(stu_id.getText());
    } catch (NumberFormatException e) {
        showAlert("Error", "Student ID must be valid integer.");
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
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}
