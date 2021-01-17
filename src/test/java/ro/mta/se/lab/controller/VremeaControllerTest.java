package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.model.CityFromFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * Aceasta este o clasa de testare a functionalitatii din punct de vedere mock pentru VremeaController
 */
public class VremeaControllerTest {

    /**
     * obiectul pe care vom face mock
     */
    private CityFromFile cityFromFile ;

    /**
     * initializarea obiectului
     * dupa aceea, observam ca am setat ca de fiecare data cand apelam metoda getCountryCity sa ne afiseze
     * RO.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        cityFromFile = new CityFromFile("246214", "London", 51.5085, 0.1257, "GB");
        cityFromFile = mock(CityFromFile.class);
        when(cityFromFile.getCountryCity()).thenReturn("RO");
    }

    /**
     * Testarea aplicatiei, salvand inforamtia intr-o variabila locala
     *
     */

    @Test
    public void initialize() {

        String cty = cityFromFile.getCountryCity();
        System.out.println(cty + " " +cityFromFile.getCountryCity());
        assertEquals(cty, cityFromFile.getCountryCity());
        assertNotNull(cty);

    }
}