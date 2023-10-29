package org.rena;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConversion {
    public static void main(String[] args) {

        try{
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your base currency (e.g. EUR)");
            String baseCurrency = scanner.next();

            System.out.println("Enter your target currency, (e.g. USD) ");
            String targetCurrency = scanner.next();

            System.out.println("Enter amount");
            double amount = scanner.nextDouble();

            final String API_KEY = "a672ccf3f3msh6357d4210a68592p1b15f1jsn93a88dc44c18";
            final String baseURL = "https://currencyconverter.p.rapidapi.com/";

            String requestUrl = baseURL + "?from_amount=" + amount + "&from=" + baseCurrency + "&to=" + targetCurrency;

            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", "currencyconverter.p.rapidapi.com");

            int responseCode = connection.getResponseCode();


            if (responseCode == 200){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                StringBuilder response = new StringBuilder();
                while ((inputLine = bufferedReader.readLine()) != null){
                    response.append(inputLine);
                }
                bufferedReader.close();
                System.out.println("Conversion result: " + response.toString());
            } else {
                System.out.println("Error fetching exchange rate. Status code: " + responseCode);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
