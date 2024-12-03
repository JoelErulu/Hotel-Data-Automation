import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Scraper {
    public static void main(String[] args) {
        // List of URLs generated dynamically
        List<String> urls = new ArrayList<>();
        urls.add("https://us.trip.com/hotels/list?city=633&provinceId=0&countryId=66&checkin=2024/12/10&checkout=2024/12/11&searchWord=Four%20Seasons%20New%20York"); // New York Example
        urls.add("https://us.trip.com/hotels/list?city=1354&provinceId=0&countryId=66&checkin=2024/12/10&checkout=2024/12/11&searchWord=Waldorf%20Astoria%20Paris"); // Paris Example
        // Add more URLs here...

        for (String url : urls) {
            try {
                System.out.println("Scraping URL: " + url);

                // Connect to the URL and fetch the document
                Document doc = Jsoup.connect(url).get();

                // Select elements containing hotel data
                // Update these selectors based on the website's actual structure
                Elements hotels = doc.select(".hotel-class"); // Example selector
                for (Element hotel : hotels) {
                    String name = hotel.select(".hotel-name").text(); // Update selector
                    String price = hotel.select(".price-class").text(); // Update selector
                    System.out.println("Hotel: " + name + ", Price: " + price);
                }
            } catch (Exception e) {
                System.err.println("Error scraping URL: " + url);
                e.printStackTrace();
            }
        }
    }
}
