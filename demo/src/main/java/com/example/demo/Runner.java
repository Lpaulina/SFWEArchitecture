package com.example.demo;

import java.util.Arrays;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  @Autowired
  private Sender sender;

  @Autowired
  private Environment env;

  @Override
  public void run(String... args) throws Exception {
    String[] profiles = env.getActiveProfiles();

    if (Arrays.asList(profiles).contains("sender")) {

      Person person = getNewPerson();
      if (person != null) {
        sender.send(person);

      }
    }
  }

  public Person getNewPerson() {
    Scanner scanner = new Scanner(System.in);
    Person person = new Person();
    String input;

    System.out.println("Enter person's name:");

    if (!scanner.hasNextLine()) {
      System.out.println("No input found. Exiting input.");
      return null;
    }

    input = scanner.nextLine();

    if (input.isBlank()) {
      System.out.println("Name cannot be empty");
      return null;
    }
    person.setName(input);

    System.out.println("Enter person's email:");
    input = scanner.nextLine();

    if (input.isBlank()) {
      System.out.println("Email cannot be empty");
      return null;
    }
    person.setEmail(input);

    System.out.println("Enter person's age:");
    Integer age;
    try {
      age = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Age must be a number.");
      return null;
    }

    if (age < 18) {
      System.out.println("Must be 18 or older.");
      return null;
    }
    person.setAge(age);

    return person;
  }
}
