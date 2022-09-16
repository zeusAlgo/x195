package com.example.x195;

public class customer {
    int divid;String postalcode; String address; int id; String name; String phone;

    public customer(int id, String name, String address, String postalcode, String phone, int divid){
        this.id = id; this.name = name; this.address=address; this.postalcode=postalcode; this.divid=divid;
    }

    public void setid(int id) {this.id=id;}
    public void setname(String name) {this.name=name;}
    public void setaddress(String address) {this.address=address;}
    public void postalcode(String postalcode) {this.postalcode=postalcode;}
    public void phone(String phone) {this.phone=phone;}
    public void divid(int divid) {this.divid=divid;}

    
}
