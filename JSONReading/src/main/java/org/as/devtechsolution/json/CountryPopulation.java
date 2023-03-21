package org.as.devtechsolution.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CountryPopulation {
    public static void main(String[] args) throws Exception {
        int p = 100000000; // Population threshold

        String url = "https://jsonmock.hackerrank.com/api/countries/search?name=&page=1";
        StringBuilder response = new StringBuilder();

        // Send GET request to the API endpoint
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        // Read the response into a StringBuilder object
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        int totalPages = jsonObject.getInt("total_pages");
        System.out.println(totalPages);
        JSONArray countries = jsonObject.getJSONArray("data");

        // Fetch all remaining pages and add them to the countries array
        for (int i = 2; i <= totalPages; i++) {
            String nextPageUrl = "https://jsonmock.hackerrank.com/api/countries/search?name=&page=" + i;
            obj = new URL(nextPageUrl);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            jsonObject = new JSONObject(response.toString());
            countries = concatJSONArray(countries, jsonObject.getJSONArray("data"));
        }

        // Filter countries by population
        JSONArray countriesWithPopulationGreaterThanP = new JSONArray();
        for (int i = 0; i < countries.length(); i++) {
            JSONObject country = countries.getJSONObject(i);
            if (country.getInt("population") > p) {
                countriesWithPopulationGreaterThanP.put(country);
            }
        }

        System.out.println(countriesWithPopulationGreaterThanP.toString());
    }

    // Helper function to concatenate two JSON arrays
    public static JSONArray concatJSONArray(JSONArray arr1, JSONArray arr2) {
        JSONArray result = new JSONArray();

        for (int i = 0; i < arr1.length(); i++) {
            result.put(arr1.get(i));
        }

        for (int i = 0; i < arr2.length(); i++) {
            result.put(arr2.get(i));
        }

        return result;
    }
}