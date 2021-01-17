package ro.mta.se.lab;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author MateiMunteanu
 * Clasa ce va realiza testarea clasei Init.
 */
public class InitTest {

    Init help = new Init();

    /**
     * Testam daca primim informatii de la clasa CityFromFile, cu alte cuvinte daca
     * avem orice tara in acel fisier. Dorim sa testam acest lucru pentru a avea metode
     * care sa functioneze mai departe.
     */
    @Test
    public void getAllInfoFromFile() {
        assertNotNull(help.getAllInfoFromFile());
    }

    /**
     * Testam daca reusim sa primim raspuns de la api-ul openweathermap.org
     * Am lasat si un model in care testul esueaza, orasul "bacau" scris cu litere
     * mici, nu il gaseste pe opentheweather
     */
    @Test
    public void doHttpRequest() throws ParseException {
        assertNotNull(help.doHttpRequest("London"));
        assertNotNull(help.doHttpRequest("Paris"));
        //assertNotNull(help.doHttpRequest("bacau"));
    }
}