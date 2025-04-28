package com.example.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;


@RabbitListener(queues = "hello")
public class Receiver {
    private final String addPersonURL = "http://localhost:8080/people";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Autowired
    private PersonService personService;

    @RabbitHandler
    public void receive(Person person) {
        System.out.println(" [x] Received '" + person + "'");

        try {
        personService.addPerson(person);

        } catch (Exception e) {
        System.out.println("ERROR while adding person: " + e.getMessage());
        e.printStackTrace();
        }

        // try {

        //     String data = "name=" + person.getName() + "&email=" + person.getEmail() + "&age=" + person.getAge();

        //     HttpRequest request = HttpRequest.newBuilder()
        //             .uri(URI.create(addPersonURL))
        //             .header("Content-Type", "application/x-www-form-urlencoded")
        //             .POST(HttpRequest.BodyPublishers.ofString(data, StandardCharsets.UTF_8))
        //             .build();

        //     HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        //     int statusCode = response.statusCode();
        //     System.out.println("HTTP Status Code: " + statusCode);


        // } catch (Exception e) {
        //     System.err.println("Error sending request " + e.getMessage());

        // }
    }

}