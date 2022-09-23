package com.example.x195;

public class Customer {
    public int id; int divid;
    public String name, address, postalcode, phone;

    public Customer(int id, String name, String address, String postalcode, String phone, int divid){
        this.id = id; this.name = name; this.address=address; this.postalcode=postalcode; this.phone=phone; this.divid=divid;
    }

    public void setId(int id) {this.id=id;}
    public void setName(String name) {this.name=name;}
    public void setAddress(String address) {this.address=address;}
    public void setPostalcode(String postalcode) {this.postalcode=postalcode;}
    public void setPhone(String phone) {this.phone=phone;}
    public void setDivid(int divid) {this.divid=divid;}

    public int getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getPostalcode() {return postalcode;}
    public String getPhone() {return phone;}
    public int getDivid() {return divid;}
}
