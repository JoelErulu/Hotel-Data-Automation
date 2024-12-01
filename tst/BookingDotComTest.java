import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.fail;

public class BookingDotComTest {

    private static WebDriver driver;
    private static final String BASE_URL = "https://www.booking.com/";
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    @BeforeClass
    public static void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
    }

    @Test
    public void testLocation() {
        // Navigate to the base URL
        driver.get(BASE_URL);

        // Set up explicit wait
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        try {
            // Wait for and dismiss the pop-up if it's present
            WebElement closePopupButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Dismiss sign-in info.']")));
            closePopupButton.click(); // Click the close button

            // Locate and interact with the input field for location
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id(":rh:")));
            inputField.sendKeys("Las Vegas");

            // Locate and click the search button
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']//span[@class='e4adce92df']")));
            searchButton.click();

            // Verify if results container is present after clicking search
            WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("results")));
            assertNotNull(resultsContainer, "Results container should be present after search.");

        } catch (TimeoutException e) {
            // Fail the test if timeout occurs or element is not found
            fail("Element not found or search results did not load in the expected time.");
        }
    }

}
