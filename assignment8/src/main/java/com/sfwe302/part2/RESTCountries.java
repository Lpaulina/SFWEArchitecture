package com.sfwe302.part2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RESTCountries {
    public static void main(String[] args) throws Exception {

        // HttpRequest request = HttpRequest.newBuilder()
        // .uri(URI.create("https://restcountries.com/v3.1/name/Czechia?fullText=true"))
        // .uri(URI.create("https://restcountries.com/v3.1/name/Germany?fullText=true"))
        // .uri(URI.create("https://restcountries.com/v3.1/name/United%20States%20of%20America?fullText=true"))
        // .header("content-type", "application/json")
        // .method("GET", HttpRequest.BodyPublishers.noBody())
        // .build();

        String[] countries = {
                "https://restcountries.com/v3.1/name/Czechia?fullText=true",
                "https://restcountries.com/v3.1/name/Germany?fullText=true",
                "https://restcountries.com/v3.1/name/United%20States%20of%20America?fullText=true"
        };

        for (String countryUri : countries) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(countryUri))
                    .header("content-type", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            String result = response.body();

            JSONArray ja = new JSONArray(result);
            JSONObject jo = ja.getJSONObject(0);

            System.out.println(jo);
            System.out.println(jo.get("cca2"));
            System.out.println(jo.getJSONArray("capital").get(0));
            System.out.println(jo.getJSONObject("languages"));

            for (String key : jo.getJSONObject("languages").keySet()) {
                System.out.println(key);
            }

            try {
                System.out
                .println(jo.getJSONObject("name").getJSONObject("nativeName").getJSONObject("ces").get("official"));
            } catch (Exception e) {
                System.out.println("ces is not this countries' economic organization");
            }


        }
    }
}
