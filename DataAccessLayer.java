/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import DTOs.Courses;
import DTOs.Departments;
import DTOs.Enrollments;
import DTOs.Students;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;


/**
 *
 * @author Ahmed Samy
 */
public class DataAccessLayer {
    public static String url = "jdbc:oracle:thin:@localhost:1521:XE" ;
    public static void connect() throws SQLException{
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    //conection
    Connection con =DriverManager.getConnection(url,"UNI_CASE","root");
    
    }
    
    public static int addStudent(Students student) throws SQLException {
    int res = -1;
    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String procedureCall = "{CALL ADDSTUDENT(?, ?, ?, ?, ?, ?, to_date(?, 'DD/MM/YYYY'))}";
        try (CallableStatement cst = con.prepareCall(procedureCall)) {
            cst.setInt(1, student.getStudent_ID());
            cst.setString(3, student.getFNAME());
            cst.setString(4, student.getLNAME());
            cst.setString(5, student.getPHONE());
            cst.setString(6, student.getADDRESS());
            cst.setString(7, student.getDOB());
            cst.setInt(2, student.getDepartment_ID());
            res = cst.executeUpdate();
        }
    }
    return res;
}
        
        public static int upStudent(Students student) throws SQLException {
    int res = -1;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
    // Use the {call ...} syntax for calling stored procedures
    CallableStatement cst = con.prepareCall("{CALL UP_STUD_INFO(?, ?, ?, ?)}");
    cst.setInt(1, student.getStudent_ID());
    cst.setInt(4, student.getDepartment_ID());
    cst.setString(2, student.getPHONE());
    cst.setString(3, student.getADDRESS());
    //System.out.println("BEFORE EXECUTE");
    res = cst.executeUpdate();
    con.close();

    return res;
}
        
  public static void delStudent(Students student) throws SQLException{
          DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          Connection con =DriverManager.getConnection(url, "UNI_CASE", "root");
          String query = "Delete from students where student_id= ?" ;
          PreparedStatement preparedStatement = con.prepareStatement(query) ;
          preparedStatement.setInt(1,student.getStudent_ID()); 
          preparedStatement.executeUpdate();
          con.close();
     }
     
  public static Students getStudentInfo(int studentId) throws SQLException {
        Students student = null;
        Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
        String selectStatement = "SELECT student_id,department_id,fname,lname,phone,address,to_char(dob,'dd-mm-yyyy')as dob FROM students WHERE student_id = ?";
        PreparedStatement pst = con.prepareStatement(selectStatement);
        pst.setInt(1, studentId);
        ResultSet resultSet = pst.executeQuery();
         if (resultSet.next()) {


        student = new Students(
                resultSet.getInt("student_id"),
                resultSet.getInt("department_id"),
                resultSet.getString("fname"),
                resultSet.getString("lname"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getString("dob")
                
        );
        }

        // Close resources
        con.close();

        return student;
    }
  
  
    public static int addDept(Departments department) throws SQLException{
    int res=-1;
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    Connection con =DriverManager.getConnection(url,"UNI_CASE","root");
    PreparedStatement pst = con.prepareStatement("Insert into departments values (?,?)");
    
     pst.setInt(1,department.getDEPARTMENT_ID());
     pst.setString(2,department.getDEPARTMENT_NAME());
     
     res=pst.executeUpdate();
     con.close();
      return res;
      
    }
    
    public static String getDepartmentName(int departmentId) throws SQLException {
    String departmentName = null;

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String selectStatement = "SELECT Department_name FROM Departments WHERE department_id = ?";
        try (PreparedStatement pst = con.prepareStatement(selectStatement)) {
            pst.setInt(1, departmentId);

            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    departmentName = resultSet.getString("department_name");
                }
            }
        }
    } catch (SQLException e) {
        // Handle the exception appropriately, log or throw as needed
        e.printStackTrace();
        throw e;
    }

    return departmentName;
}
    public static int updateDepartmentName(Departments department) throws SQLException {
    int result = -1;

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String updateStatement = "UPDATE Departments SET Department_name = ? WHERE department_id = ?";

        try (PreparedStatement pst = con.prepareStatement(updateStatement)) {
            pst.setString(1, department.getDEPARTMENT_NAME());
            pst.setInt(2, department.getDEPARTMENT_ID());

            // Execute the update
            result = pst.executeUpdate();
        }
    } catch (SQLException e) {
        // Handle the exception appropriately, log or throw as needed
        e.printStackTrace();
        throw e;
    }

    return result;
}
    
    public static void deldepartment(Departments department) throws SQLException{
          DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          Connection con =DriverManager.getConnection(url, "UNI_CASE", "root");
          String query = "Delete from Departments where department_id= ?" ;
          PreparedStatement preparedStatement = con.prepareStatement(query) ;
          preparedStatement.setInt(1,department.getDEPARTMENT_ID()); 
          preparedStatement.executeUpdate();
          con.close();
     }
    
    
    public static int addCourse(Courses course) throws SQLException {
    int res = -1;
    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String procedureCall = "{CALL ADDCOURSE(?, ?, ?, ?, ?)}";
        try (CallableStatement cst = con.prepareCall(procedureCall)) {
            cst.setInt(1, course.getCOURSE_ID());
            cst.setString(2, course.getCOURSE_NAME());
            cst.setInt(3, course.getDEPARTMENT_ID());
            cst.setInt(4, course.getCREDIT_HOURS());
            cst.setInt(5, course.getFINAL_GRADE());
            res = cst.executeUpdate();
        }
    }
    return res;
}


    public static int updateAllCourseInfo(Courses course) throws SQLException {
    int result = -1;

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String updateStatement = "UPDATE Courses SET course_name = ?, department_id = ?, credit_hours = ?, final_grade = ? WHERE course_id = ?";

        try (PreparedStatement pst = con.prepareStatement(updateStatement)) {
            pst.setString(1, course.getCOURSE_NAME());
            pst.setInt(2, course.getDEPARTMENT_ID());
            pst.setInt(3, course.getCREDIT_HOURS());
            pst.setInt(4, course.getFINAL_GRADE());
            pst.setInt(5, course.getCOURSE_ID());

            // Execute the update
            result = pst.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }

    return result;
}


    public static void deleteCourse(int courseId) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM courses WHERE course_id = ?");
            pst.setInt(1, courseId);
            pst.executeUpdate();
        }
    }

    public static Courses getCourseInfo(int courseId) throws SQLException {
    Courses course = null;

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String selectStatement = "SELECT * FROM Courses WHERE course_id = ?";
        try (PreparedStatement pst = con.prepareStatement(selectStatement)) {
            pst.setInt(1, courseId);

            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    course = new Courses(
                            resultSet.getInt("course_id"),
                            resultSet.getInt("department_id"),
                            resultSet.getInt("credit_hours"),
                            resultSet.getInt("final_grade"),
                            resultSet.getString("course_name")
                    );
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }

    return course;
}
    
    public static int addCourseForStudent(Enrollments enrollment) throws SQLException {
    int res = -1;
    Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");

    try (PreparedStatement pst = con.prepareStatement("INSERT INTO enrollments (STUDENT_ID,COURSE_ID,NUMERIC_GRADE,ASSIGN_DATE,SEMESTER) VALUES (?, ?, NULL, ?, ?)")) {
        pst.setInt(1, enrollment.getSTUDENT_ID());
        pst.setInt(2, enrollment.getCOURSE_ID());
        pst.setInt(3, enrollment.getASSIGN_DATE());
         pst.setInt(4, enrollment.getSEMESTER());

        res = pst.executeUpdate();
    }

    return res;
}

public static int addGradeForCourse(Enrollments enrollment) throws SQLException {
    int res = -1;
    Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");

    try (PreparedStatement pst = con.prepareStatement("UPDATE enrollments SET numeric_grade = ? WHERE student_id = ? AND course_id = ? AND semester = ? AND ASSIGN_DATE = ? ")) {
        pst.setInt(1, enrollment.getNUMERIC_GRADE());
        pst.setInt(5, enrollment.getASSIGN_DATE());
        pst.setInt(2, enrollment.getSTUDENT_ID());
        pst.setInt(3, enrollment.getCOURSE_ID());
        pst.setInt(4, enrollment.getSEMESTER());

        res = pst.executeUpdate();
    }

    return res;
}

public static double calculateStudentGPA(int studentId) throws SQLException {
    double gpa = 0.0;

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        CallableStatement cst = con.prepareCall("select CALC_STUD_GPA(?) as GPA from students");
        cst.setInt(1, studentId);
        ResultSet resultSet = cst.executeQuery();

        if (resultSet.next()) {
            gpa = resultSet.getDouble("GPA");
        }
    }

    return gpa;
}
    public static void deldeStudentCourse(Enrollments enrollment) throws SQLException{
          DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          Connection con =DriverManager.getConnection(url, "UNI_CASE", "root");
          String query = "Delete from Enrollments where student_id= ? and course_id= ? " ;
          PreparedStatement preparedStatement = con.prepareStatement(query) ;
          preparedStatement.setInt(1,enrollment.getSTUDENT_ID()); 
          preparedStatement.setInt(2,enrollment.getCOURSE_ID()); 
          preparedStatement.executeUpdate();
          con.close();
     }
    
    public static List<Students> getAllStudents() throws SQLException {
        List<Students> students = new ArrayList<>();
        Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
        String query = "SELECT STUDENT_ID, DEPARTMENT_ID, FNAME, LNAME, PHONE,ADDRESS,to_char(DOB,'dd-mm-yyyy')as DOB,CALC_STUD_GPA(Student_ID) AS GPA FROM Students";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Students student = new Students(
                        resultSet.getInt("Student_ID"),
                        resultSet.getInt("Department_ID"),
                        resultSet.getString("FNAME"),
                        resultSet.getString("LNAME"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address"),
                        resultSet.getString("DOB"),
                         resultSet.getInt("GPA")
                        
                );
                students.add(student);
            }
        } finally {
            con.close();
        }
        return students;
    }
    
    public static List<Enrollments> getEnrollmentsWithCourseInfo(int studentId) throws SQLException {
    List<Enrollments> enrollments = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String functionCall = "{ ? = call GetEnrollmentsWithCourseInfo(?) }";

        try (CallableStatement cst = con.prepareCall(functionCall)) {
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setInt(2, studentId);

            cst.execute();

            try (ResultSet resultSet = (ResultSet) cst.getObject(1)) {
                while (resultSet.next()) {
                    Enrollments enrollment = new Enrollments(
                            resultSet.getInt("COURSE_ID"),
                            resultSet.getInt("NUMERIC_GRADE"),
                            resultSet.getString("GRADE"),
                            resultSet.getString("COURSE_NAME"),
                            resultSet.getInt("ASSIGN_DATE"),
                            resultSet.getInt("SEMESTER")
                    );
                    enrollments.add(enrollment);
                }
            }
        }
    } 

    return enrollments;
}

    
     public static List<Courses> getAllCourses() throws SQLException {
        List<Courses> courses = new ArrayList<>();
        String query = "SELECT Course_ID,Course_Name FROM Courses";
        
        try ( Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Courses course = new Courses(
                        resultSet.getInt("Course_ID"),
                        resultSet.getString("Course_Name")
                        
                );
                
                courses.add(course);
            }
        }
        return courses;
        
    }
     public static List<Students> getStudentsByCourse(int courseId) throws SQLException {
    List<Students> students = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String functionCall = "{ ? = call GetStudentsByCourse(?) }";

        try (CallableStatement cst = con.prepareCall(functionCall)) {
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setInt(2, courseId);

            cst.execute();

            try (ResultSet resultSet = (ResultSet) cst.getObject(1)) {
                while (resultSet.next()) {
                    Students student = new Students(
                            resultSet.getInt("Student_ID"),
                            resultSet.getString("FNAME"),
                            resultSet.getString("LNAME"),
                            resultSet.getString("Department_Name"),
                            resultSet.getString("Grade"),
                            resultSet.getInt("NUMERIC_GRADE")
                    );
                    students.add(student);
                }
            }
        }
    }

    return students;
}


    // Calculate average GPA for a specific course
    public static double calculateAverageGPA(int courseId) throws SQLException {
        String query = "SELECT AVG(GV.Grade_value) AS AvgGPA " +
                       "FROM Enrollments E " +
                       "JOIN GradeValues GV ON E.Grade = GV.Grade " +
                       "WHERE E.Course_ID = ?";
        
        try ( Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
             PreparedStatement preparedStatement = con.prepareStatement(query)) {
            
            preparedStatement.setInt(1, courseId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("AvgGPA");
                }
            }
        }
        return 0; // Default to 0 if no data is found
    }
    
    public static List<Students> getStudentsByDepartment(int departmentId) throws SQLException {
    List<Students> students = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String functionCall = "{ ? = call GetStudentsByDepartment(?) }";

        try (CallableStatement cst = con.prepareCall(functionCall)) {
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setInt(2, departmentId);

            cst.execute();

            try (ResultSet resultSet = (ResultSet) cst.getObject(1)) {
                while (resultSet.next()) {
                    Students student = new Students(
                            resultSet.getInt("Student_ID"),
                            resultSet.getString("FNAME"),
                            resultSet.getDouble("GPA")
                    );
                    students.add(student);
                }
            }
        }
    }

    return students;
}

    
    public static List<Courses> getDepartmentCoursesAvgGPA(int departmentId) throws SQLException {
    List<Courses> courses = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String functionCall = "{ ? = call GetDepartmentCoursesAvgGPA(?) }";

        try (CallableStatement cst = con.prepareCall(functionCall)) {
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setInt(2, departmentId);

            cst.execute();

            try (ResultSet resultSet = (ResultSet) cst.getObject(1)) {
                while (resultSet.next()) {
                    Courses course = new Courses(
                            resultSet.getInt("Course_ID"),
                            resultSet.getString("Course_Name"),
                            resultSet.getDouble("AvgGPA")
                    );
                    courses.add(course);
                }
            }
        }
    }

    return courses;
}

    
    public static List<Departments> getDepartmentInfo() throws SQLException {
    List<Departments> departmentInfoList = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(url, "UNI_CASE", "root")) {
        String functionCall = "{ ? = call GetDepartmentInfo }";

        try (CallableStatement cst = con.prepareCall(functionCall)) {
            cst.registerOutParameter(1, OracleTypes.CURSOR);

            cst.execute();

            try (ResultSet resultSet = (ResultSet) cst.getObject(1)) {
                while (resultSet.next()) {
                    Departments departmentInfo = new Departments(
                            resultSet.getInt("Department_ID"),
                            resultSet.getString("Department_Name"),
                            resultSet.getDouble("AvgDepartmentGPA"),
                            resultSet.getInt("NumStudents")
                    );
                    departmentInfoList.add(departmentInfo);
                }
            }
        }
    }

    return departmentInfoList;
}

    
    public static Integer getNoOfCourses() throws SQLException {
        Integer courses_no=0;
        String query = "SELECT count(*) as NoOfCourses FROM Courses";
        
        try ( Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
             courses_no = resultSet.getInt("NoOfCourses");
 
            }
        }
        return courses_no;
        
    }
    
    public static Integer getNoOfStudents() throws SQLException {
        Integer students_no=0;
        String query = "SELECT count(*) as NoOfStudents FROM students";
        
        try ( Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
             students_no = resultSet.getInt("NoOfStudents");
                System.out.println(students_no);
 
            }
        }
        return students_no;
        
    }
    public static Integer getNoOfDepartments() throws SQLException {
        Integer departments_no=0;
        String query = "SELECT count(*) as NoOfDepartments FROM departments";
        
        try ( Connection con = DriverManager.getConnection(url, "UNI_CASE", "root");
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
             departments_no = resultSet.getInt("NoOfDepartments");
 
            }
        }
        return departments_no;
        
    }
    
    

     
     



 
}
