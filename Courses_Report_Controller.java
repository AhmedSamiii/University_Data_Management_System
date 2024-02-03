package Controllers;

import DTOs.Courses;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Courses_Report_Controller implements Initializable {
    @FXML
    private TableView<Courses> coursesTable;
    @FXML
    private TableColumn<Courses, Integer> courseIdColumn;
    @FXML
    private TableColumn<Courses, String> courseNameColumn;

    @FXML
    private TableView<Students> studentsTable;
    @FXML
    private TableColumn<Students, Integer> studentIdColumn;
    @FXML
    private TableColumn<Students, String> studentNameColumn;
    @FXML
    private TableColumn<Students, String> departmentColumn;
    @FXML
    private TableColumn<Students, String> gradeColumn;

    @FXML
    private Text averageGPALabel;

    // Data lists for TableView
    private ObservableList<Courses> coursesList = FXCollections.observableArrayList();
    private ObservableList<Students> studentsList = FXCollections.observableArrayList();
    @FXML
    private Pane coursesReport;
    @FXML
    private TableColumn<Students, Integer> NgradeColumn;
    @FXML
    private ScatterChart<Number, Number> Grades_chart;
    @FXML
    private NumberAxis Numeric_Grade;
    @FXML
    private NumberAxis Stud_id;
    public void initialize(URL url, ResourceBundle rb) {

        // Initialize columns
        Stud_id.setLabel("Student ID");
        Numeric_Grade.setLabel("Grade");
            courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("COURSE_ID"));
       courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("COURSE_NAME"));

       studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
       studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("FNAME"));
       departmentColumn.setCellValueFactory(new PropertyValueFactory<>("DEPARTMENT_NAME"));
       gradeColumn.setCellValueFactory(new PropertyValueFactory<>("GRADE"));
       NgradeColumn.setCellValueFactory(new PropertyValueFactory<>("NUMERIC_GRADE"));


        // Load courses into the TableView
        loadCourses();

        // Set listener to coursesTable selection
        TableSelectionModel<Courses> selectionModel = coursesTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Load students and update average GPA for the selected course
                loadStudents(newSelection.getCOURSE_ID());
                updateAverageGPA(newSelection.getCOURSE_ID());
            }
        });
        
        studentsTable.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile = getClass().getResource("blueTable.css").toExternalForm();
        studentsTable.getStylesheets().add(cssFile);
        
        coursesTable.getColumns().forEach(column -> {
        column.setStyle(null);
        });
        String cssFile1 = getClass().getResource("blueTable.css").toExternalForm();
        coursesTable.getStylesheets().add(cssFile1);
    }
    
    // Inside the initialize method or a new method called updateScatterChart
private void updateScatterChart(List<Students> students) {
    Grades_chart.getData().clear(); // Clear previous data

    XYChart.Series<Number, Number> series = new XYChart.Series<>();

    for (Students student : students) {
        // Add data points to the series
        series.getData().add(new XYChart.Data<>(student.getStudent_ID(), student.getNUMERIC_GRADE()));
    }

    // Set the series to the ScatterChart
    Grades_chart.getData().add(series);
}


    // Load courses into the TableView
    private void loadCourses() {
        try {
            coursesList.addAll(DataAccessLayer.getAllCourses());
            System.out.println("getcourses called");
            coursesTable.setItems(coursesList);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Load students into the TableView based on the selected course
    private void loadStudents(int courseId) {
        try {
            studentsList.clear(); // Clear previous data
            studentsList.addAll(DataAccessLayer.getStudentsByCourse(courseId));
            studentsTable.setItems(studentsList);
            updateScatterChart(studentsList);   
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    // Update the average GPA label for the selected course
    private void updateAverageGPA(int courseId) {
        try {
            double averageGPA = DataAccessLayer.calculateAverageGPA(courseId);
            averageGPALabel.setText(String.format("Average GPA: %.2f", averageGPA));
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
