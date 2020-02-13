package com.example.demo.domain;


public class Class {
    private String teachername;
    private String studentno;
    private String subjectname;
    private int classid;
    private String NAME;
    private char sex;
    private int age;

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Class{" +
                "teachername='" + teachername + '\'' +
                ", studentno='" + studentno + '\'' +
                ", subjectname='" + subjectname + '\'' +
                ", classid=" + classid +
                ", NAME='" + NAME + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
