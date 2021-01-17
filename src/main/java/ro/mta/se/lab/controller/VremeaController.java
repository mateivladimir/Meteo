package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import ro.mta.se.lab.Init;
import ro.mta.se.lab.Log;
import ro.mta.se.lab.model.CityFromFile;
import ro.mta.se.lab.model.CityFromJson;

import javax.xml.crypto.Data;

/**
 *
 * Clasa care face legatura intre cod si interfata grafica.
 *
 *  @author MateiMunteanu
 */
public class VremeaController implements Initializable {
    @FXML private ComboBox<String> countryComboBox;
    @FXML private Label cityLabel;
    @FXML private ComboBox<String> cityComboBox;
    @FXML private Label tempLabel;
    @FXML private Label timeLabel;
    @FXML private Label typeWeatherLabel;
    @FXML private Label humidityLabel;
    @FXML private Label windLabel;
    @FXML private ImageView imageImageView;
    @FXML private Label descLabel;
    @FXML private Label cloudLabel;
    @FXML private Label sunsetLabel;
    @FXML private Label sunriseLabel;
    @FXML private Label minMaxLabel;

    private ObservableList<CityFromFile> allCities = FXCollections.observableArrayList();

    /**
     * Constructor implicit pentru a putea salva toate orasele citite din fisier intr-o variabila
     * la care sa avem acces ori de cate ori este nevoie in cadrul acestei clase.
     * @param allCities
     */
    public VremeaController(ObservableList<CityFromFile> allCities) {
        this.allCities = allCities;
    }

    /**
     * Functie ce se va rula la inceputul aplicatiei. Aceasta va face o lista de tari
     * citite din fisier, dupa care va aplica metoda distinct() care va returna toate
     * obiectele unice din interiorul acelui ArrayList. La final, vom adauga tooate
     * stringurile in FXML-ul countryComboBox, pentru a fi disponibile utilizatorului.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ArrayList<String> allCountry = new ArrayList<>();
        for (CityFromFile city : allCities)
            allCountry.add(city.getCountryCity());
        List <String> distinctCountry = allCountry.stream().distinct().collect(Collectors.toList());
        for (String cnt : distinctCountry)
            countryComboBox.getItems().add(cnt);
    }

    /**
     * Metoda ce se va apela dupa ce utilizatorul va selecta o tara. Precum conceptului prezentat mai sus,
     * aceasta functie va salva tara selectata intr-un buffer, dupa care va lua un ArrayList in care va salva
     * toate orasele care se afla in tara respectiva. La final, vom adauga aceasta lista intr-un comboBox care va
     * fi disponibil utilizatorului.
     */

    public void whenCountryComboBoxIsSelected() throws Exception {
        cityComboBox.getItems().clear();
        cityComboBox.getSelectionModel().clearSelection();

        String x = countryComboBox.getValue();
        ArrayList <String> allCityFromOneCountry = new ArrayList<>();
        for (CityFromFile city : allCities){
            if(city.getCountryCity().equals(x))
                allCityFromOneCountry.add(city.getNameCity());
        }
        for (String cty :allCityFromOneCountry)
            cityComboBox.getItems().add(cty);

    }


    /**
     * Metoda ce se va apela dupa ce utilizatorul va selecta si orasul pe care il doreste.
     * Aceasta va trimite un httpRequest catre openweathermap.org cu numele orasului si va face
     * legatura intre interfata si elementele gasite in json.
     *
     * @throws Exception
     */

    public void whenCityComboBoxIsSelected() throws Exception {
        if (countryComboBox.getValue()==null || cityComboBox.getValue()==null)
            return;
        String jsonString;
        Init initHttpRequest = new Init();
        jsonString = initHttpRequest.doHttpRequest(cityComboBox.getValue());
        if(jsonString == null)
            return;

        Object obj = new JSONParser().parse(jsonString);
        JSONObject jo = (JSONObject) obj;

        CityFromJson cityParse = new CityFromJson(jo);
        this.tempLabel.setText(Double.toString(cityParse.getTemp())+ "°C");
        this.typeWeatherLabel.setText(cityParse.getDescription());
        this.humidityLabel.setText(cityParse.getHumidity());
        this.windLabel.setText(cityParse.getWindSpeed());
        this.imageImageView.setImage(new Image("https://openweathermap.org/img/wn/"+ cityParse.getImg()+"@2x.png"));
        this.descLabel.setText(cityParse.getDescLabel());
        this.cloudLabel.setText(cityParse.getCloudsPI());
        this.timeLabel.setText(cityParse.getDateTime());
        this.sunriseLabel.setText(cityParse.getSunriseTime());
        this.sunsetLabel.setText(cityParse.getSunsetTime());
        this.minMaxLabel.setText(cityParse.getMinMax());

        Log.getInstance().writeToFile("Accesare vreme orasul : " + cityComboBox.getValue());


    }


}
