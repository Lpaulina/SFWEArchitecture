package com.sfwe302.part3;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class CountriesProtocol {
    private final static Integer WAITING = 1;
    private final static Integer SENT_INSTRUCTION = 2;

    private static Integer state = WAITING;

    private static Integer numUniversities;
    private static ArrayList<String> universities = new ArrayList<>();

    public String processInput(String userInput) throws IOException, InterruptedException {
        String output = null;
        if (state == WAITING) {
            output = "specify the country";
            state = SENT_INSTRUCTION;
        } else if (state == SENT_INSTRUCTION) {
            getCountryInfo(userInput);
            output = "Number of universities: " + numUniversities + "\n Universities: " + universities + "\n specify the country";

            universities.clear();
            state = SENT_INSTRUCTION;
        }

        return output;
    }

    public static void getCountryInfo(String userCountry) throws IOException, InterruptedException {

        userCountry = URLEncoder.encode(userCountry, StandardCharsets.UTF_8);
        String countryUri = "http://universities.hipolabs.com/search?country=" + userCountry;
        // String countryUri = "http://universities.hipolabs.com/search?country=United%20States";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(countryUri))
                .header("content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                HttpResponse.BodyHandlers.ofString());

        String result = response.body();

        JSONArray data = new JSONArray(result);
        numUniversities = data.length();

        for (int i = 0; i < numUniversities; i++) {
            JSONObject univ = data.getJSONObject(i);
            String name = (String) univ.get("name");

            universities.add(name);
        }

    }
}
