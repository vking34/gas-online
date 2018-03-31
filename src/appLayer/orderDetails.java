package appLayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class orderDetails {
    private String phoneNumber;
    private String address;
    private String ward;
    private String city;
    private double lat;
    private double lng;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString()
    {
        return "\"orderDetails\": {phoneNumber:\""+ phoneNumber+
                "\", address:\""+ address +
                "\", ward:\""+ ward +
                "\", city:\""+ city +
                "\", lat:\""+ lat +
                "\", lng:\""+ lng + "\"}";
    }
}
