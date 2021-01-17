package ro.mta.se.lab.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author MateiMunteanu
 * Aceasta clasa va implementa testele unitare din clasa CityFromFile.java
 * Vrem prin aceasta sa vedem daca functioneaza corespunzator get-urile din
 * aceasta clasa
 */

public class CityFromFileTest {

    /**
     * Am luat 3 referinte pentru a testa cele 2 teste. Este de mentionat faptul ca id-ul, latitudinea
     * si longitudinea nu reflecta realitatea, acest lucru neinfluentand aceste teste.
     */

    CityFromFile c1 = new CityFromFile("200000", "Bacau", 150.20, 200.03, "RO");
    CityFromFile c2 = new CityFromFile("20154", "London", 32.1, 20.6, "GB");
    CityFromFile c3 = new CityFromFile("45212", "Paris", 32.0, 27.4, "FR");

    /**
     * Clasa ce va implementa 3 teste asupra functiei getCountryCity. Ar trebui sa primim raspuns
     * ok din partea tuturor pentru a le putea da mai departe comboBoxului
     */
    @Test
    public void getCountryCity() {
        assertEquals("RO", c1.getCountryCity());
        assertEquals("GB", c2.getCountryCity());
        assertEquals("FR", c3.getCountryCity());
    }

    /**
     * Clasa de teste ce va intoarce numele orasului.
     */

    @Test
    public void getNameCity() {
        assertEquals("Bacau", c1.getNameCity());
        assertEquals("London", c2.getNameCity());
        assertEquals("Paris", c3.getNameCity());
    }
}