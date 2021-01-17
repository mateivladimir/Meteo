package ro.mta.se.lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.mta.se.lab.model.CityFromFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * @author MateiMunteanu
 *
 * Aceasta clasa va face initializarile in ceea ce avem nevoie pentru a avea o aplicatie functionala.
 */
public class Init {

    /**
     * Clasa care va citi informatii din fisierul "input.txt", dupa care le va face split in functie de oricate spatii
     * (" +"), salvandu-le in clasa CityFromFile.
     * @return - clasa va returna o lista de orase citite din fisier.
     */
    public ObservableList <CityFromFile> getAllInfoFromFile() {
        ObservableList <CityFromFile> citiesFromFile = FXCollections.observableArrayList();
        try {
            Scanner scan = new Scanner(new File("src/main/resources/input.txt"));
            String data = scan.nextLine();
            while (scan.hasNextLine()) {
                data = scan.nextLine();
                String[] array = data.split(" +");
                citiesFromFile.add(new CityFromFile(array[0], array[1], Double.parseDouble(array[2]), Double.parseDouble(array[3]), array[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return citiesFromFile;

    }

    /**
     * Aceasta metoda va trimite un httpRequest catre serverul api.openweather.org, dupa care
     * va salva informatiile intr-un string. De asemenea, am testat si daca acest string este valid,
     * cu alte cuvinte daca campul "name" este egal cu ceea ce am trimis. Daca nu, inseamna ca serverul
     * a trimis un mesaj de eroare si vom returna null.
     * @param cityName - numele orasului cu care va face HttpReqest
     * @throws ParseException
     */
    public String doHttpRequest(String cityName) throws ParseException {
        String myApiId = "0663d566ce998bf47c7c358960e85af6";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&appid=" + myApiId)).build();
        String join = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join();

        Object obj = new JSONParser().parse(join);
        JSONObject jo = (JSONObject) obj;
        if (jo.get("name").equals(cityName))
            return join;
        return null;
    }
}
