import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class HotelNameScraperTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testHotelNameScraper() {
        try {
            // Navigate to the URL
            driver.get("https://us.trip.com/hotels/list?city=633&provinceId=0&countryId=66&checkin=2024%2F12%2F10&checkout=2024%2F12%2F11&listFilters=2~2~163*2*2~163*2%2C17~1*17*1*2%2C80~0~1*80*0*2:");

            // Scrape hotel names using CSS selectors
            List<WebElement> hotelLinks = driver.findElements(By.cssSelector("a.name"));

            boolean foundHotel = false;
            // Check if the specific hotel is in the list
            for (WebElement link : hotelLinks) {
                String hotelName = link.getText();

                // If the specific hotel is found, break the loop and mark as found
                if (hotelName.equals("Four Seasons Hotel New York Downtown")) {
                    foundHotel = true;
                    break;
                }
            }

            // Assert that the hotel was found
            assertTrue(foundHotel, "Four Seasons Hotel New York Downtown not found.");

        } catch (Exception e) {
            fail("Exception occurred during scraping: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
