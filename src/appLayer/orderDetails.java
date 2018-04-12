package appLayer;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
public class orderDetails {
    private int gasCode;
    private String phoneNumber;
    private String address;
    private String ward;

    orderDetails()
    {

    }

    public int getGasCode() {
        return gasCode;
    }

    public void setGasCode(int gasCode) {
        this.gasCode = gasCode;
    }

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
}
