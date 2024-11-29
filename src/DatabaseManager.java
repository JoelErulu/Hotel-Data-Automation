
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:hotels.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS hotels (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    city TEXT NOT NULL,
                    date TEXT NOT NULL,
                    price REAL NOT NULL
                );
            """;
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveHotelData(Hotel hotel) {
        String insertSQL = "INSERT INTO hotels (name, city, date, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, hotel.getName());
            pstmt.setString(2, hotel.getCity());
            pstmt.setString(3, hotel.getDate());
            pstmt.setDouble(4, hotel.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Hotel> findLowestPricedHotels(String city, int limit) {
        String querySQL = "SELECT * FROM hotels WHERE city = ? ORDER BY price ASC LIMIT ?";
        List<Hotel> hotels = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {
            pstmt.setString(1, city);
            pstmt.setInt(2, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("date"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }
}
