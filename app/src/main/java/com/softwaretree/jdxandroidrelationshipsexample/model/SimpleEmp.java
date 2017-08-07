package com.softwaretree.jdxandroidrelationshipsexample.model;

/**
 * A class describing an Employee.
 * <p>
 * Special note: the deptId attribute is declared as an IMPLICIT_ATTRIB in the mapping specification 
 * for the class SimpleEmp; and so, it will automatically be initialized with the related value in 
 * a referenced (SimpleDept) object when an instance of SimpleEmp is persisted by JDX ORM. 
 * If deptId had not been declared as an IMPLICIT_ATTRIB in the mapping specification, it should 
 * be explicitly initialized outside of JDX before the instance of a SimpleEmp is persisted.
 * <p>
 * @author Damodar
 *
 */
public class SimpleEmp {
    private String empId;
    private String empName;
    private String title;
    private String ssn;
    private float salary;
    private int deptId;
    private SimpleDept dept;     // identified by deptId
    private SimpleAddr address;  // identified by empId
    
    public SimpleEmp() {
        super();
    }
    
    public SimpleEmp(String empId, String empName, String title, String ssn, float salary, SimpleDept dept,
            SimpleAddr address) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.title = title;
        this.ssn = ssn;
        this.salary = salary; 
        this.dept = dept;
        this.address = address;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public SimpleDept getDept() {
        return dept;
    }

    public void setDept(SimpleDept dept) {
        this.dept = dept;
    }

    public SimpleAddr getAddress() {
        return address;
    }

    public void setAddress(SimpleAddr address) {
        this.address = address;
    }
    
}
