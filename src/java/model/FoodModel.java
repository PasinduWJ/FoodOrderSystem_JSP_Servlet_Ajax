
package model;

import java.io.Serializable;

public class FoodModel implements Serializable{
    
    private int id;
    private String fName;
    private double uPrice;
    private int maxOrder;
    private Boolean available;
    private String uDateTime, updateBy;
    private Boolean state;
    
    public FoodModel() {
    }

    public FoodModel(int id, String fName, double uPrice, int maxOrder, Boolean available, String uDateTime, String updateBy, Boolean state) {
        this.id = id;
        this.fName = fName;
        this.uPrice = uPrice;
        this.maxOrder = maxOrder;
        this.available = available;
        this.uDateTime = uDateTime;
        this.updateBy = updateBy;
        this.state = state;
    }

    public FoodModel(int id, String fName, double uPrice, int maxOrder, Boolean available, String uDateTime, String updateBy) {
        this.id = id;
        this.fName = fName;
        this.uPrice = uPrice;
        this.maxOrder = maxOrder;
        this.available = available;
        this.uDateTime = uDateTime;
        this.updateBy = updateBy;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public double getuPrice() {
        return uPrice;
    }

    public void setuPrice(double uPrice) {
        this.uPrice = uPrice;
    }

    public int getMaxOrder() {
        return maxOrder;
    }

    public void setMaxOrder(int maxOrder) {
        this.maxOrder = maxOrder;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FoodModel{" + "id=" + id + ", fName=" + fName + ", uPrice=" + uPrice + ", maxOrder=" + maxOrder + ", available=" + available + ", uDateTime=" + uDateTime + ", updateBy=" + updateBy + ", state=" + state + '}';
    }

}
