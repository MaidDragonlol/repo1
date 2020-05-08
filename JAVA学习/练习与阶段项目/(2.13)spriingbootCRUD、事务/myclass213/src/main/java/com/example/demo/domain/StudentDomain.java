package com.example.demo.domain;


public class StudentDomain {
    /*stuid INTEGER NOT NULL,
    stuname VARCHAR(20) NOT NULL,
    stusex BIT DEFAULT 1,
    stubirth DATETIME NOT NULL*/
    private Integer stuid;
    private String stuname;
    private Byte stusex;
    private String stubirth;
    private ClassDomain classDomain;

    public ClassDomain getClassDomain() {
        return classDomain;
    }

    public void setClassDomain(ClassDomain classDomain) {
        this.classDomain = classDomain;
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

    public Byte getStusex() {
        return stusex;
    }

    public void setStusex(Byte stusex) {
        this.stusex = stusex;
    }

    public String getStubirth() {
        return stubirth;
    }

    public void setStubirth(String stubirth) {
        this.stubirth = stubirth;
    }

    @Override
    public String toString() {
        return "StudentDomain{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stusex=" + stusex +
                ", stubirth='" + stubirth + '\'' +
                ", classDomain=" + classDomain +
                '}';
    }
}
