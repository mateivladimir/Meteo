package ro.mta.se.lab.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CityFromFile {
  String idCity;
  String nameCity;
  Double latCity;
  Double longCity;
  String countryCity;


  public CityFromFile(String id, String name, Double lat, Double lon, String country){
      this.idCity = id;
      this.nameCity = name;
      this.latCity = lat;
      this.longCity = lon;
      this.countryCity = country;
  }

    public String getCountryCity() {
        return countryCity;
    }

    public String getNameCity() {
        return nameCity;
    }
}
