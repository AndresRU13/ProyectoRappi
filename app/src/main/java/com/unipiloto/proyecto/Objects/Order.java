package com.unipiloto.proyecto.Objects;

public class Order {

    Integer id;
    String nameU;
    String value;
    String pay;
    String nameV;
    String location;
    String date;

    public Order(Integer id, String nameU, String value, String pay, String nameV, String location, String date) {
        this.id = id;
        this.nameU = nameU;
        this.value = value;
        this.pay = pay;
        this.nameV = nameV;
        this.location = location;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameU() {
        return nameU;
    }

    public void setNameU(String nameU) {
        this.nameU = nameU;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getNameV() {
        return nameV;
    }

    public void setNameV(String nameV) {
        this.nameV = nameV;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
