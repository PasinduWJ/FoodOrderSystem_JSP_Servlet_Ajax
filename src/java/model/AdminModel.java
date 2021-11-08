
package model;

import java.io.Serializable;

public class AdminModel implements Serializable{
    
    private String adminName;
    private int pNumber;
    private String password;
    private String Role;
    private Boolean state;
    private String cDateTime, uDateTime, updateBy;

    public AdminModel() {
    }

    public AdminModel(String adminName, int pNumber, String password, String Role, Boolean state, String cDateTime, String uDateTime, String updateBy) {
        this.adminName = adminName;
        this.pNumber = pNumber;
        this.password = password;
        this.Role = Role;
        this.state = state;
        this.cDateTime = cDateTime;
        this.uDateTime = uDateTime;
        this.updateBy = updateBy;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getcDateTime() {
        return cDateTime;
    }

    public void setcDateTime(String cDateTime) {
        this.cDateTime = cDateTime;
    }

    public String getuDateTime() {
        return uDateTime;
    }

    public void setuDateTime(String uDateTime) {
        this.uDateTime = uDateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "AdminModel{" + "adminName=" + adminName + ", pNumber=" + pNumber + ", password=" + password + ", Role=" + Role + ", state=" + state + ", cDateTime=" + cDateTime + ", uDateTime=" + uDateTime + ", updateBy=" + updateBy + '}';
    }

}
