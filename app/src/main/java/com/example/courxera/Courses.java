package com.example.courxera;


public class Courses {
    private String courseName;
    private String timeSchedule;
    private double credits;
    private int courseId;

    public Courses(String courseName, String timeSchedule, double credits, int courseId) {
        this.courseName = courseName;
        this.timeSchedule = timeSchedule;
        this.credits = credits;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
