
package model;

import java.io.Serializable;

public class UserModel implements Serializable{
    
    private String firstName, lastName, userName, password;
    private int pNumber;
    private Boolean state;
    private String cDateTime, uDateTime, updateBy;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String userName, String password, int pNumber, Boolean state, String cDateTime, String uDateTime, String updateBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.pNumber = pNumber;
        this.state = state;
        this.cDateTime = cDateTime;
        this.uDateTime = uDateTime;
        this.updateBy = updateBy;
    }

    public UserModel(String firstName, String lastName, String userName, String password, int pNumber, Boolean state, String cDateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.pNumber = pNumber;
        this.state = state;
        this.cDateTime = cDateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
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
        return "UserModel{" + "firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password=" + password + ", pNumber=" + pNumber + ", state=" + state + ", cDateTime=" + cDateTime + ", uDateTime=" + uDateTime + ", updateBy=" + updateBy + '}';
    }

}
