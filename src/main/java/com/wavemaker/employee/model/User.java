package com.wavemaker.employee.model;

public class User  {
    private String name;
    private int id;
    private String company_name;
    public User(int userId,String name,String company_name) {
        this.id =userId;
        this.name =name;
        this.company_name =company_name;
    }
    public User() {
    }

    public String toString(){

        return name +""+ id;
    }
    public String getName() {

        return name;
    }
    public int getId(){

        return id;
    }
    public void setId(int id){

        this.id =id;
    }
    public void setName(String n){

        this.name =n;
    }
    public String getCompany_name() {

        return company_name;
    }
    public void setCompany_name(String company_name) {

        this.company_name=company_name;
    }
}

