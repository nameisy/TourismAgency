# Patika Travel Agency System Project

This software solution is designed to digitize the daily operations of Patika Travel Agency, offering a comprehensive suite of features including hotel management, room management, period management, price management, room search, and reservation processes.

## Installation Steps

1. **Clone the Project:**
   - Clone the project repository to your local machine.

2. **Create the Database:**
   - Create a database named `turizmacentesistemi` in PostgreSQL.

3. **Set Up Tables:**
   - Use the `turizmacentesistemi.sql` file to create the necessary tables in the database.

4. **Configure Database Connection:**
   - Edit the `DatabaseConnection.java` file to configure the database connection.

## Technologies Used

- **Java:** Application development
- **Java Swing (GUI):** User interface design
- **PostgreSQL:** Database management

## Project Structure

1. **Business:** Contains service classes that implement business logic.
2. **Core:** Directory containing database creation files.
3. **Dao:** Data Access Object classes for database operations.
4. **Entity:** Model classes representing database tables.
5. **Views:** Swing GUI classes that create the user interface.

This structure ensures that the project is modular and maintainable, allowing for easy management and expansion of different components. This software accelerates the digitization process of Patika Travel Agency with a user-friendly interface and robust business logic.

---

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        // Initialize database connection
        DatabaseConnection.connect();

        // Example of retrieving a list of hotels
        HotelService hotelService = new HotelService();
        List<Hotel> hotels = hotelService.getAllHotels();
        
        // Display hotels in the GUI
        HotelView hotelView = new HotelView(hotels);
        hotelView.display();
    }
}
```

---

### Screenshots

![Hotel Management](path/to/screenshot1.png)
*Hotel Management Interface*

![Room Booking](path/to/screenshot2.png)
*Room Booking Interface*

---

### Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

---

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Contact

For any inquiries or issues, please contact ismailsafayilmaz@gmail.com

---

Enjoy using Patika Travel Agency System!
