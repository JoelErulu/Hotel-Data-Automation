import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CustomURLGenerator {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // Hotel chains and cities
        String[] hotels = {"Four Seasons", "Ritz-Carlton", "Park Hyatt", "St. Regis Hotel", "Waldorf Astoria Hotel"};
        String[] cities = {"Las Vegas", "New York City", "Miami", "Paris", "Los Angeles"};
        Map<String, String> cityCodes = new HashMap<>();
        cityCodes.put("Las Vegas", "550");
        cityCodes.put("New York City", "633");
        cityCodes.put("Miami", "430");
        cityCodes.put("Paris", "1354");
        cityCodes.put("Los Angeles", "1785");

        // Base URL
        String baseUrl = "https://us.trip.com/hotels/list";
        String checkin = "2024/12/10";
        String checkout = "2024/12/11";

        // Generate URLs
        for (String city : cities) {
            for (String hotel : hotels) {
                String cityCode = cityCodes.get(city);
                String searchWord = URLEncoder.encode(hotel + " " + city, "UTF-8");
                String customUrl = String.format(
                        "%s?city=%s&provinceId=0&countryId=66&districtId=0&checkin=%s&checkout=%s" +
                                "&lowPrice=0&highPrice=-1&barCurr=USD&searchType=N&searchWord=%s" +
                                "&crn=1&adult=2&children=0&locale=en-US&curr=USD",
                        baseUrl, cityCode, checkin, checkout, searchWord
                );

                System.out.println("Custom URL for " + hotel + " in " + city + ":");
                System.out.println(customUrl);
                System.out.println();
            }
        }
    }
}
