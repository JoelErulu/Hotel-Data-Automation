
public class Hotel {
    private String name;
    private String city;
    private String date;
    private double price;

    public Hotel(String name, String city, String date, double price) {
        this.name = name;
        this.city = city;
        this.date = date;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}
