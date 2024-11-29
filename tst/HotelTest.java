import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    @Test
    public void testHotelConstructorAndGetters() {
        String name = "Luxury Inn";
        String city = "Atlanta";
        String date = "2024-11-15";
        double price = 150.75;

        Hotel hotel = new Hotel(name, city, date, price);

        assertEquals(name, hotel.getName(), "Hotel name should match");
        assertEquals(city, hotel.getCity(), "Hotel city should match");
        assertEquals(date, hotel.getDate(), "Hotel date should match");
        assertEquals(price, hotel.getPrice(), "Hotel price should match");
    }

    @Test
    public void testHotelWithSpecialCharactersInName() {
        String name = "The Ritz-Carlton";
        String city = "Paris";
        String date = "2024-12-25";
        double price = 500.0;

        Hotel hotel = new Hotel(name, city, date, price);

        System.out.println("Hotel name: " + hotel.getName());
        assertEquals(name, hotel.getName(), "Hotel name with special characters should match");
        assertEquals(city, hotel.getCity(), "Hotel city should match");
        assertEquals(date, hotel.getDate(), "Hotel date should match");
        assertEquals(price, hotel.getPrice(), "Hotel price should match");
    }
}
