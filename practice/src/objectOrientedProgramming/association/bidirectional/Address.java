package objectOrientedProgramming.association.bidirectional;

public class Address {
    private String state;
    private String city;
    private String zip;

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
