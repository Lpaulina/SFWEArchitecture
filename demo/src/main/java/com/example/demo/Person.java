package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Name cannot be empty.")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Min(value = 18, message = "Age must be greater than 17")
    private int age;

    public Person(){
        super();
    }

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Email: " + this.email + ", Age: " + this.age; 
    }
}
