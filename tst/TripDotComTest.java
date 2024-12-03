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

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TripDotComTest {
    private static WebDriver driver;
    private static final String BASE_URL = "https://us.trip.com/";
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
            // Locate and interact with the input field for location
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(By.id("destinationInput")));
            inputField.sendKeys("Las Vegas");

            //TODO: Eventually do statement to select specific days.
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchBoxCon\"]/div/div/div/div/div[3]/i")));
            searchButton.click();

            // Locate and click the search button
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='searchBoxCon']//button")));
            searchButton.click();

            // Verify if results container is present after clicking search
            // TODO: IDK how to do this.
            WebElement resultsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("results")));
            assertNotNull(resultsContainer, "Results container should be present after search.");

        } catch (TimeoutException e) {
            // Fail the test if timeout occurs or element is not found
            fail("Element not found or search results did not load in the expected time.");
        }
    }
}
