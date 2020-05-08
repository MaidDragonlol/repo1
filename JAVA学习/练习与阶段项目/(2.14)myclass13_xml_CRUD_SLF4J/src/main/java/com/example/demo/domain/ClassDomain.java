package com.example.demo.domain;

public class ClassDomain {
   /* classid INTEGER NOT NULL,
    stuid INT NOT NULL,
    stuname VARCHAR(20) NOT NULL,
    stusubject VARCHAR(20) NOT NULL*/
   private Integer classid;
   private Integer stuid;
   private String stuname;
   private String stusubject;

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStusubject() {
        return stusubject;
    }

    public void setStusubject(String stusubject) {
        this.stusubject = stusubject;
    }
    @Override
    public String toString() {
        return "ClassDomain{" +
                "classid=" + classid +
                ", stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stusubject='" + stusubject + '\'' +
                '}';
    }
}
