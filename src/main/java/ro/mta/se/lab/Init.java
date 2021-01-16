package ro.mta.se.lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ro.mta.se.lab.model.CityFromFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Init {

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

    public String doHttpRequest(String cityName){
        String myApiId = "0663d566ce998bf47c7c358960e85af6";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q=" + cityName +"&appid=" + myApiId)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join();
    }
}
