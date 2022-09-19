package com.example.x195;

import com.mysql.cj.conf.IntegerProperty;
import javafx.beans.value.ObservableValue;

public class customer {
    int id;String name, address, postalcode, phone;int divid;
//    ObservableValue<Integer> id;
//    ObservableValue<String> name;
//    ObservableValue<String> address;
//    O

    public customer(int id, String name, String address, String postalcode, String phone, int divid){
        this.id = id; this.name = name; this.address=address; this.postalcode=postalcode; this.divid=divid;
    }

    public void setid(int id) {this.id=id;}
    public void setname(String name) {this.name=name;}
    public void setaddress(String address) {this.address=address;}
    public void postalcode(String postalcode) {this.postalcode=postalcode;}
    public void phone(String phone) {this.phone=phone;}
    public void divid(int divid) {this.divid=divid;}

    public int getid() {return id;}
    public String getname() {return name;}
    public String getaddress() {return address;}
    public String getpostalcode() {return postalcode;}
    public String getphone() {return phone;}
    public int getdivid() {return divid;}

    //mapping between the db
    // get instance of customer with jdbc
    // missing types
    // i can't find the id. perhaps
    // java need a model. model maps to a pojo
}
