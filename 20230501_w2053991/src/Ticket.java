// The import statement imports the classes required for file management.
import java.io.FileWriter;
import java.io.IOException;

class Ticket {
    public char row;
    public int seat;
    public double price;
    public Person person;

    // With the row, seat, price, and person provided, this constructor starts a Ticket object.
    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // The ticket details, including the row, seat, price, and person information, are displayed using this method.
    public void print() {
        System.out.println("\n=== Ticket Information ===");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price);
        System.out.println("Person Information:");
        person.print();
    }

    // The ticket data is saved to a text file using this method.
    public void save() {
        // Using the row and seat numbers as input, generate the filename.
        String filename = String.format("%c%d.txt", row, seat);

        try (FileWriter writer = new FileWriter(filename)) {
            // Add ticket details to the file.
            writer.write("Ticket Information:\n");
            writer.write(String.format("Row: %c\n", row));
            writer.write(String.format("Seat: %d\n", seat));
            writer.write(String.format("Price: £%.2f\n", price));
            writer.write("Person Information:\n");
            writer.write(String.format("Name: %s\n", person.getName()));
            writer.write(String.format("Surname: %s\n", person.getSurname()));
            writer.write(String.format("Email: %s\n", person.getEmail()));

            System.out.println("Ticket information saved to " + filename);

        } catch (IOException e) {
            // If a problem happens when saving the ticket data, print an error message.
            System.out.println("An error occurred while saving the ticket information.");
            e.printStackTrace();
        }
    }

}

