package ro.mta.se.lab.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author MateiMunteanu
 * Aceasta clasa va salva toate informatiile citite din fisier.
 */
public class CityFromFile {
  String idCity;
  String nameCity;
  Double latCity;
  Double longCity;
  String countryCity;

    /**
     *
     * @param id - id-ul orasului, aceasta valoare NU va influenta rularea programului
     * @param name - numele pe care il are orasul, valoarea fiind foarte importanta deoarece cu ajutorul
     *             acesteia vom realiza creearea HTTP requestului.
     * @param lat - latitudinea la care se afla orasul, NU va influenta programul
     * @param lon - longitudinea la care se afla orasul, NU va influenta programul
     * @param country - tara in care se afla orasul, un parametru important deoarece cand vom afisa
     *                in comboox toate tarile, daca tara nu este adnotata corespunzator, va afisa doar
     *                orasul curent in cityComboBox.
     */
  public CityFromFile(String id, String name, Double lat, Double lon, String country){
      this.idCity = id;
      this.nameCity = name;
      this.latCity = lat;
      this.longCity = lon;
      this.countryCity = country;
  }

    /**
     * Functie care va returna fiecare tara citita din fisier, fie ca este duplicata sau nu.
     */

    public String getCountryCity() {
        return countryCity;
    }

    /**
     * Metoda care va returna toate orasele din fisier, fie ca apartin unei anumite tari sau nu.
     */
    public String getNameCity() {
        return nameCity;
    }
}
