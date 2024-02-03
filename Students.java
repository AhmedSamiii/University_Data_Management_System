/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author Ahmed Samy
 */
public class Students {
    
    private Integer Student_ID,Department_ID,NUMERIC_GRADE;
    private String FNAME,LNAME,PHONE,ADDRESS,DOB,DEPARTMENT_NAME,GRADE ;
    private double GPA ;

    public Students(Integer Student_ID, Integer Department_ID, String FNAME, String LNAME, String PHONE, String ADDRESS, String DOB) {
        this.Student_ID = Student_ID;
        this.Department_ID = Department_ID;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.DOB = DOB;
    }

    public Students(Integer Student_ID, Integer Department_ID, String FNAME, String LNAME, String PHONE, String ADDRESS, String DOB ,double GPA) {
        this.Student_ID = Student_ID;
        this.Department_ID = Department_ID;
        this.GPA = GPA;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
        this.DOB = DOB;
    }

    public Students(Integer Student_ID,String FNAME, String LNAME, String DEPARTMENT_NAME ,String GRADE, Integer NUMERIC_GRADE) {
        this.Student_ID = Student_ID;
        this.GRADE = GRADE;
        this.FNAME = FNAME;
        this.LNAME = LNAME;
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        this.NUMERIC_GRADE = NUMERIC_GRADE;
        
    }

    public Students(Integer Student_ID, String FNAME, double GPA) {
        this.Student_ID = Student_ID;
        this.GPA = GPA;
        this.FNAME = FNAME;
    }

    public String getDEPARTMENT_NAME() {
        return DEPARTMENT_NAME;
    }

    public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE;
    }

    public Integer getNUMERIC_GRADE() {
        return NUMERIC_GRADE;
    }

    public void setNUMERIC_GRADE(Integer NUMERIC_GRADE) {
        this.NUMERIC_GRADE = NUMERIC_GRADE;
    }
    

    public double getGPA() {
        return GPA;
    }

    public void setGPA(Integer GPA) {
        this.GPA = GPA;
    }
    

    public Students(Integer Student_ID, Integer Department_ID, String PHONE, String ADDRESS) {
        this.Student_ID = Student_ID;
        this.Department_ID = Department_ID;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
    }

    public Students(Integer Student_ID) {
        this.Student_ID = Student_ID;
    }

    public Integer getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(Integer Student_ID) {
        this.Student_ID = Student_ID;
    }

    public Integer getDepartment_ID() {
        return Department_ID;
    }

    public void setDepartment_ID(Integer Department_ID) {
        this.Department_ID = Department_ID;
    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getLNAME() {
        return LNAME;
    }

    public void setLNAME(String LNAME) {
        this.LNAME = LNAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    
}
