package com.java.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 逻辑视图
     * @return
     */
    public String getBirStr() {
        if (birthday != null) {
            //1.格式化日期对象
            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            //2.返回字符串
            return dateFormat.format( birthday );
        } else {
            return "";
        }
    }

    @JsonIgnore //json转换时忽略该属性
    public String a;
    protected String b;
    String c;
    private String d;


    public Person() {
    }

    public Person(String name, int age, Date birthday) {

        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }

    public void eat() {
        System.out.println( "eat..." );
    }

    public void eat(String food) {
        System.out.println( "eat..." + food );
    }
}