import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.time.Duration;

public class HotelNameScraper {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the URL
            driver.get("https://us.trip.com/hotels/list?city=633&provinceId=0&countryId=66&checkin=2024%2F12%2F10&checkout=2024%2F12%2F11&listFilters=2~2~163*2*2~163*2%2C17~1*17*1*2%2C80~0~1*80*0*2:");

            // Wait for elements to load using WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@class, 'name')]")));

            // Scrape hotel names and URLs using XPath
            List<WebElement> hotelLinks = driver.findElements(By.xpath("//a[contains(@class, 'name')]"));

            if (hotelLinks.isEmpty()) {
                System.out.println("No hotel links found.");
                return;
            }

            for (WebElement link : hotelLinks) {
                String hotelName = link.getText(); // Extract the hotel name
                String hotelUrl = link.getAttribute("href"); // Extract the hotel URL

                // Print hotel details
                System.out.println("Hotel: " + hotelName);
                System.out.println("URL: " + hotelUrl);
                System.out.println("-------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
