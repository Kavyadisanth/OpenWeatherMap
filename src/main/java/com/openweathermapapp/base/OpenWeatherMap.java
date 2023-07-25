package com.openweathermapapp.base;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


import org.json.JSONObject;


public class OpenWeatherMap {


    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=";

    public static void main(String[] args) {
        OpenWeatherMap app = new OpenWeatherMap();
        app.run();
    }

    private void run() {
        try {
            while (true) {
                displayMenu();
                int option = readOptionFromUser();

                switch (option) {
                    case 1:
                        getWeatherData();
                        break;
                    case 2:
                        //getWindSpeed();
                        break;
                    case 3:
                        //getPressure();
                        break;
                    case 0:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        System.out.println("1. Get weather");
        System.out.println("2. Get Wind Speed");
        System.out.println("3. Get Pressure");
        System.out.println("0. Exit");
    }

    private int readOptionFromUser() {
        System.out.print("Enter your option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private JSONObject getWeatherData() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return new JSONObject(response.toString());
    }

}

