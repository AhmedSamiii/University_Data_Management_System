package DTOs;

public class Courses {
    private Integer COURSE_ID, DEPARTMENT_ID, CREDIT_HOURS, FINAL_GRADE;
    private String COURSE_NAME;
     private double AVG_GPA;

    public Courses(Integer COURSE_ID, Integer DEPARTMENT_ID, Integer CREDIT_HOURS, Integer FINAL_GRADE, String COURSE_NAME) {
        this.COURSE_ID = COURSE_ID;
        this.DEPARTMENT_ID = DEPARTMENT_ID;
        this.CREDIT_HOURS = CREDIT_HOURS;
        this.FINAL_GRADE = FINAL_GRADE;
        this.COURSE_NAME = COURSE_NAME;
    }

    public Courses(Integer COURSE_ID, String COURSE_NAME) {
        this.COURSE_ID = COURSE_ID;
        this.COURSE_NAME = COURSE_NAME;
    }

    public Courses(Integer COURSE_ID, String COURSE_NAME, double AVG_GPA) {
        this.COURSE_ID = COURSE_ID;
        this.COURSE_NAME = COURSE_NAME;
        this.AVG_GPA = AVG_GPA;
    }

    public double getAVG_GPA() {
        return AVG_GPA;
    }

    public void setAVG_GPA(double AVG_GPA) {
        this.AVG_GPA = AVG_GPA;
    }
    
    

    public Courses(Integer COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }



    public Integer getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(Integer COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public Integer getDEPARTMENT_ID() {
        return DEPARTMENT_ID;
    }

    public void setDEPARTMENT_ID(Integer DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public Integer getCREDIT_HOURS() {
        return CREDIT_HOURS;
    }

    public void setCREDIT_HOURS(Integer CREDIT_HOURS) {
        this.CREDIT_HOURS = CREDIT_HOURS;
    }

    public Integer getFINAL_GRADE() {
        return FINAL_GRADE;
    }

    public void setFINAL_GRADE(Integer FINAL_GRADE) {
        this.FINAL_GRADE = FINAL_GRADE;
    }

    public String getCOURSE_NAME() {
        return COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }
}
