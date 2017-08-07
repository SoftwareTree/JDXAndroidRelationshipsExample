package com.softwaretree.jdxandroidrelationshipsexample.model;

/**
 * A class describing a department.
 * <p>
 * @author Damodar
 *
 */
public class SimpleDept {
    
    private int deptId;
    private String companyId;
    private String deptName;

    public SimpleDept() {
        super();
    }
    
    public SimpleDept(int deptId, String companyId, String deptName) {
        super();
        this.deptId = deptId;
        this.companyId = companyId;
        this.deptName = deptName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
 
}
