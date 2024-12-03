import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CustomURLGenerator {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // Hotel chains and cities
        String[] hotels = {"Comfort Suites", "Marriott", "Park Hyatt", "Holiday Inn", "DoubleTree"};
        String[] cities = {"Dallas", "New York City", "Miami", "Chicago", "Los Angeles"};
        Map<String, String> cityCodes = new HashMap<>();
        cityCodes.put("Dallas", "705");
        cityCodes.put("New York City", "633");
        cityCodes.put("Miami", "25773");
        cityCodes.put("Chicago", "549");
        cityCodes.put("Los Angeles", "347");

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
     /**
     Hotel Brands
     Hilton
     Marriott
     Hyatt
     Holiday Inn (IHG)
     Sheraton (part of Marriott)

     New York City, NY
     Chicago, IL
     Los Angeles, CA
     Atlanta, GA
     Dallas, TX
     **/
}
