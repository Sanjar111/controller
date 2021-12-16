package com.student.control.model;

public class Student {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer course;
    private Boolean status;
    private String time;

    public Student(Integer studentId,String firstName,String lastName,Integer age,Integer course,Boolean status,String time ){
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
        this.status = status;
        this.time = time;
    }


    public Integer getStudentId(){
        return studentId;
    }
    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public Integer getCourse(){
        return course;
    }
    public void setCourse(Integer course){
        this.course = course;
    }
    public Boolean getStatus(){
        return status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }



}

