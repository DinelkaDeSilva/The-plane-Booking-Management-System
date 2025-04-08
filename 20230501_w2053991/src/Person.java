import java.util.Scanner;

public class Person {
    private String name;
    private String surname;
    private String email;

    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getting methods
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    // Setting methods
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Print method to display person Data
    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }

    // Static method for using user input to construct a Person
    public static Person createPersonFromInput(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter email: ");
        String email = scanner.next();
        return new Person(name, surname, email);
    }
}