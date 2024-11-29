import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.List;

public class DatabaseManagerTest {

    // Set up the test database before each test
    @BeforeEach
    public void setUp() {
        // Initialize the database (create table if not exists)
        DatabaseManager.initializeDatabase();
    }

    // Test database table creation
    @Test
    public void testInitializeDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:hotels.db");
             Statement stmt = conn.createStatement()) {
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='hotels';";
            ResultSet rs = stmt.executeQuery(query);
            assertTrue(rs.next(), "Hotels table should be created.");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database initialization failed.");
        }
    }

    // Test saving a hotel
    @Test
    public void testSaveHotelData() {
        Hotel hotel = new Hotel("Luxury Inn", "New York", "2024-11-25", 250.75);

        // Save hotel data
        DatabaseManager.saveHotelData(hotel);

        // Verify if the hotel is inserted into the database
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:hotels.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM hotels WHERE name = ?")) {
            pstmt.setString(1, "Luxury Inn");
            ResultSet rs = pstmt.executeQuery();
            assertTrue(rs.next(), "Hotel should be saved in the database.");
            assertEquals("New York", rs.getString("city"));
            assertEquals("2024-11-25", rs.getString("date"));
            assertEquals(250.75, rs.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Saving hotel data failed.");
        }
    }

    // Test finding the lowest-priced hotels in a city
    @Test
    public void testFindLowestPricedHotels() {
        Hotel hotel1 = new Hotel("Budget Inn", "Atlanta", "2024-11-20", 99.99);
        Hotel hotel2 = new Hotel("Economy Stay", "Atlanta", "2024-11-21", 120.50);
        Hotel hotel3 = new Hotel("Luxury Inn", "Atlanta", "2024-11-22", 250.75);

        // Save hotels
        DatabaseManager.saveHotelData(hotel1);
        DatabaseManager.saveHotelData(hotel2);
        DatabaseManager.saveHotelData(hotel3);

        // Retrieve the lowest priced hotels in Atlanta
        List<Hotel> hotels = DatabaseManager.findLowestPricedHotels("Atlanta", 2);

        // Verify if the list contains the correct hotels in order of price
        assertEquals(2, hotels.size(), "There should be 2 hotels returned.");
        assertEquals("Budget Inn", hotels.get(0).getName(), "First hotel should be Budget Inn");
        assertEquals("Economy Stay", hotels.get(1).getName(), "Second hotel should be Economy Stay");
        assertTrue(hotels.get(0).getPrice() < hotels.get(1).getPrice(), "Hotels should be ordered by price.");
    }

    // Clean up the database after each test
    @AfterEach
    public void cleanUp() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:hotels.db");
             Statement stmt = conn.createStatement()) {
            // Drop the hotels table to clean up after tests
            stmt.execute("DROP TABLE IF EXISTS hotels;");
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Database cleanup failed.");
        }
    }
}
