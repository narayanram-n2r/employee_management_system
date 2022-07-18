package com.rps.capestone.batch11.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {
    @Id
    private String empid;
    private String empname;
    private String empemailId;
    private int empsalary;

    public Employee() {
    }

    public Employee(String empid, String empname, String emailId, int salary){
        this.empid=empid;
        this.empname=empname;
        this.empemailId=empemailId;
        this.empsalary=empsalary;
    }

    public String getEmpemailId() {
        return empemailId;
    }


    public void setEmpemailIdEmailId(String emailId) {
        this.empemailId = empemailId;
    }

    public String getEmpId() {
        return empid;
    }

    public void setEmpId(String id) {
        this.empid = empid;
    }
    public String getEmpname(){
        return empname;
    }
    public void setEmpname(String empname){
        this.empname= this.empname;
    }



    public int getEmpsalary() {
        return empsalary;
    }

    public void setEmpSalary(int empsalarysalary) {
        this.empsalary = empsalary;
    }

    @Override
    public String toString() {
         return "Employee [id="+empid+",Emplyeename="+empname+",emailId="+empemailId+"]";
    }
}
