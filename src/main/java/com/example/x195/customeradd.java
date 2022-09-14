package com.example.x195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.HashMap;

public class customeradd {
    public ComboBox countryComboBox;
    public ComboBox divisionComboBox;

    HashMap<String, Integer> countryhm1 = new HashMap<>();
    HashMap<Integer, String> countryhm2 = new HashMap<>();
    HashMap<String, Integer> divisionhm1 = new HashMap<>();
    HashMap<Integer, String> divisionhm2 = new HashMap<>();
    ObservableList<String> countryar = FXCollections.observableArrayList();


    public void initialize() {
        countryhm1.put("U.S", 1);
        countryhm1.put("UK", 2);
        countryhm1.put("Canada", 3);
        countryhm2.put(1, "U.S");
        countryhm2.put(2, "UK");
        countryhm2.put(3, "Canada");
        countryar.add("U.S"); countryar.add("UK"); countryar.add("Canada");
        countryComboBox.setItems(countryar);

        
    }

}
