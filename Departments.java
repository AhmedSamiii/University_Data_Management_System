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
public class Departments {
   private Integer DEPARTMENT_ID,STUD_NUM;
   private String DEPARTMENT_NAME;
   private double AVG_GPA;
   

    public Departments(Integer DEPARTMENT_ID, String DEPARTMENT_NAME) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }

    public Departments(Integer DEPARTMENT_ID,String DEPARTMENT_NAME,double AVG_GPA, Integer STUD_NUM) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
        this.AVG_GPA = AVG_GPA;
        this.STUD_NUM = STUD_NUM;
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }

    public Departments(Integer DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public double getAVG_GPA() {
        return AVG_GPA;
    }

    public void setAVG_GPA(Integer AVG_GPA) {
        this.AVG_GPA = AVG_GPA;
    }

    public Integer getSTUD_NUM() {
        return STUD_NUM;
    }

    public void setSTUD_NUM(Integer STUD_NUM) {
        this.STUD_NUM = STUD_NUM;
    }

    public Integer getDEPARTMENT_ID() {
        return DEPARTMENT_ID;
    }

    public void setDEPARTMENT_ID(Integer DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public String getDEPARTMENT_NAME() {
        return DEPARTMENT_NAME;
    }

    public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }
   
}
