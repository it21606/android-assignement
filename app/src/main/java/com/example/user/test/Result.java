package com.example.user.test;

public class Result {
    private String fname;
    private String lname;
    private String age;
    private String timestamp;


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAge() {
        return age;
    }

    public String getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "DataContract{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age='" + age + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public Result(String fname, String lname, String age, String timestamp) {

        this.fname = fname;
        this.lname = lname;

        this.age = age;
        this.timestamp = timestamp;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}