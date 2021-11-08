
package model;

import java.io.Serializable;

public class OrderModel implements Serializable{
    private int id;
    private String userName;
    private int fId, quantity;
    private String cDateTime, uDateTime, updateBy;
    private Boolean delivery;
    private Boolean state;

    public OrderModel() {
    }

    public OrderModel(int id, String userName, int fId, int quantity, String cDateTime, String uDateTime, String updateBy, Boolean delivery, Boolean state) {
        this.id = id;
        this.userName = userName;
        this.fId = fId;
        this.quantity = quantity;
        this.cDateTime = cDateTime;
        this.uDateTime = uDateTime;
        this.updateBy = updateBy;
        this.delivery = delivery;
        this.state = state;
    }

    public OrderModel(int id, int fId, int quantity, String uDateTime, Boolean delivery) {
        this.id = id;
        this.fId = fId;
        this.quantity = quantity;
        this.uDateTime = uDateTime;
        this.delivery = delivery;
    }

    public OrderModel(String userName, int fId, int quantity, String uDateTime) {
        this.userName = userName;
        this.fId = fId;
        this.quantity = quantity;
        this.uDateTime = uDateTime;
    }

    public OrderModel(int id, int fId, int quantity, String uDateTime, Boolean delivery, Boolean state) {
        this.id = id;
        this.fId = fId;
        this.quantity = quantity;
        this.uDateTime = uDateTime;
        this.delivery = delivery;
        this.state = state;
    }

    public OrderModel(int id, String userName, int quantity, String uDateTime) {
        this.id = id;
        this.userName = userName;
        this.quantity = quantity;
        this.uDateTime = uDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderModel{" + "id=" + id + ", userName=" + userName + ", fId=" + fId + ", quantity=" + quantity + ", cDateTime=" + cDateTime + ", uDateTime=" + uDateTime + ", updateBy=" + updateBy + ", delivery=" + delivery + ", state=" + state + '}';
    }


}
