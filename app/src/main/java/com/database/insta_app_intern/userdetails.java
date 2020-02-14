package com.database.insta_app_intern;

public class userdetails {
    String name,mobile,college,course,year,highstudy;
    public userdetails() {
    }

    public userdetails(String name, String mobile, String college, String course, String year, String highstudy) {
        this.name = name;
        this.mobile = mobile;
        this.college = college;
        this.course = course;
        this.year = year;
        this.highstudy = highstudy;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCollege() {
        return college;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }

    public String getHighstudy() {
        return highstudy;
    }
}
