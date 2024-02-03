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
public class Enrollments {
  private Integer  STUDENT_ID, COURSE_ID, NUMERIC_GRADE, ASSIGN_DATE,SEMESTER ;
  private String  GRADE, COURSE_NAME ;

    public Enrollments(Integer STUDENT_ID, Integer COURSE_ID, Integer NUMERIC_GRADE, Integer ASSIGN_DATE, String GRADE) {
        this.STUDENT_ID = STUDENT_ID;
        this.COURSE_ID = COURSE_ID;
        this.NUMERIC_GRADE = NUMERIC_GRADE;
        this.ASSIGN_DATE = ASSIGN_DATE;
        this.GRADE = GRADE;
    }
     public Enrollments(Integer NUMERIC_GRADE ,Integer STUDENT_ID, Integer COURSE_ID, Integer ASSIGN_DATE,Integer SEMESTER) {
        this.NUMERIC_GRADE = NUMERIC_GRADE;
         this.STUDENT_ID = STUDENT_ID;
        this.COURSE_ID = COURSE_ID;
        this.ASSIGN_DATE = ASSIGN_DATE;
         this.SEMESTER = SEMESTER;
        
    }
     public Enrollments(Integer STUDENT_ID, Integer COURSE_ID, Integer ASSIGN_DATE,Integer SEMESTER) {
       
         this.STUDENT_ID = STUDENT_ID;
        this.COURSE_ID = COURSE_ID;
        this.ASSIGN_DATE = ASSIGN_DATE;
         this.SEMESTER = SEMESTER;
        
    }

    public Enrollments(Integer STUDENT_ID, Integer COURSE_ID) {
        this.STUDENT_ID = STUDENT_ID;
        this.COURSE_ID = COURSE_ID;
    }
     public Enrollments(int COURSE_ID, int NUMERIC_GRADE, String GRADE, String COURSE_NAME, Integer ASSIGN_DATE) {
        this.COURSE_ID = COURSE_ID;
        this.NUMERIC_GRADE = NUMERIC_GRADE;
        this.GRADE = GRADE;
        this.COURSE_NAME = COURSE_NAME;
        this.ASSIGN_DATE = ASSIGN_DATE;
    }
     
     public Enrollments(int COURSE_ID, int NUMERIC_GRADE, String GRADE, String COURSE_NAME, Integer ASSIGN_DATE,Integer SEMESTER) {
        this.COURSE_ID = COURSE_ID;
        this.NUMERIC_GRADE = NUMERIC_GRADE;
        this.GRADE = GRADE;
        this.COURSE_NAME = COURSE_NAME;
        this.ASSIGN_DATE = ASSIGN_DATE;
        this.SEMESTER = SEMESTER;
    }

    public Enrollments(int aInt, int aInt0, int aInt1, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCOURSE_NAME() {
        return COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }

    public Integer getSEMESTER() {
        return SEMESTER;
    }

    public void setSEMESTER(Integer SEMESTER) {
        this.SEMESTER = SEMESTER;
    }
     


    public Integer getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(Integer STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public Integer getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(Integer COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public Integer getNUMERIC_GRADE() {
        return NUMERIC_GRADE;
    }

    public void setNUMERIC_GRADE(Integer NUMERIC_GRADE) {
        this.NUMERIC_GRADE = NUMERIC_GRADE;
    }

    public Integer getASSIGN_DATE() {
        return ASSIGN_DATE;
    }

    public void setASSIGN_DATE(Integer ASSIGN_DATE) {
        this.ASSIGN_DATE = ASSIGN_DATE;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE;
    }
  
}
