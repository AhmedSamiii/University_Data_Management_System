package Controllers;

import DTOs.Courses;
import DTOs.Departments;
import DTOs.Students;
import DataAccessLayer.DataAccessLayer;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class Departments_Report_Controller implements Initializable {
    @FXML
    private TableView<Departments> Dept_table;
    
    @FXML
    private TableColumn<Departments, Integer> dept_id_col;

    @FXML
    private TableColumn<Departments, String> dept_name_col;

    @FXML
    private TableColumn<Departments, Double> dept_avg_gpa_col;

    @FXML
    private TableColumn<Departments, Integer> Stud_numb_col;

    @FXML
    private TableView<Students> studentsTable;

    @FXML
    private TableColumn<Students, String> stud_name_col;

    @FXML
    private TableColumn<Students, Double> stud_gpa_col;

    @FXML
    private TableView<Courses> courses_table;

    @FXML
    private TableColumn<Courses, String> course_name_col;

    @FXML
    private TableColumn<Courses, Double> course_avg_gpa_col;
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        try {
            System.out.println("iam in Departments_Report_Controller ");
            loadDepartmentsInfo();

            // Add listener for department selection
            Dept_table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    int selectedDepartmentId = newValue.getDEPARTMENT_ID();

                    try {
                        loadStudentsByDepartment(selectedDepartmentId);
                        loadDepartmentCoursesAvgGPA(selectedDepartmentId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        studentsTable.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile = getClass().getResource("blueTable.css").toExternalForm();
        studentsTable.getStylesheets().add(cssFile);
        
        courses_table.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile1 = getClass().getResource("blueTable.css").toExternalForm();
        courses_table.getStylesheets().add(cssFile1);
        
        
         Dept_table.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile2 = getClass().getResource("blueTable.css").toExternalForm();
        Dept_table.getStylesheets().add(cssFile2);
        
    }

    private void loadDepartmentsInfo() throws SQLException {
        System.out.println("loadDepartmentsInfo");
    List<Departments> departmentInfoList = DataAccessLayer.getDepartmentInfo();
    ObservableList<Departments> observableDepartments = FXCollections.observableArrayList(departmentInfoList);
    Dept_table.setItems(observableDepartments);

    // Set cell value factories using PropertyValueFactory
    dept_id_col.setCellValueFactory(new PropertyValueFactory<>("DEPARTMENT_ID"));
    dept_name_col.setCellValueFactory(new PropertyValueFactory<>("DEPARTMENT_NAME"));
    dept_avg_gpa_col.setCellValueFactory(new PropertyValueFactory<>("AVG_GPA"));
    Stud_numb_col.setCellValueFactory(new PropertyValueFactory<>("STUD_NUM"));
}


    private void loadStudentsByDepartment(int departmentId) throws SQLException {
        System.out.println("loadStudentsByDepartment");
    List<Students> students = DataAccessLayer.getStudentsByDepartment(departmentId);
    ObservableList<Students> observableStudents = FXCollections.observableArrayList(students);
    studentsTable.setItems(observableStudents);

    // Set cell value factories using PropertyValueFactory
    stud_name_col.setCellValueFactory(new PropertyValueFactory<>("FNAME"));
    stud_gpa_col.setCellValueFactory(new PropertyValueFactory<>("GPA"));
}

private void loadDepartmentCoursesAvgGPA(int departmentId) throws SQLException {
    System.out.println("loadDepartmentCoursesAvgGPA");
    List<Courses> courses = DataAccessLayer.getDepartmentCoursesAvgGPA(departmentId);
    ObservableList<Courses> observableCourses = FXCollections.observableArrayList(courses);
    courses_table.setItems(observableCourses);

    // Set cell value factories using PropertyValueFactory
    course_name_col.setCellValueFactory(new PropertyValueFactory<>("COURSE_NAME"));
    course_avg_gpa_col.setCellValueFactory(new PropertyValueFactory<>("AVG_GPA"));
}

}
