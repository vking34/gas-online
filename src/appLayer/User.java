package appLayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dataLayer.user_db;

import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String password1;
    private String phoneNumber;
    private String email;

    public User(){}

    public User(String name, String pass){
        this.username = name;
        this.password = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "User {firstName=\"" + firstName +
                "\", lastName=\"" + lastName +
                "\", username=\"" + username +
                "\", password=\"" + password +
                "\", password1=\"" + password1 +
                "\", phoneNumber=\"" + phoneNumber +
                "\", email=\"" + email + "\"}";
    }

    public boolean isValidUserCredentials()
    {
        user_db user_object = new user_db();

        return user_object.isValidUserLogin(this.username, this.password);
    }

    public int isExistingUser(String username, String phoneNumber, String email)
    {
        user_db u = new user_db();
        return u.isExistingUser(username, phoneNumber, email);
    }

    public boolean insertDB()
    {
        user_db u = new user_db();
        u.insert(username, password, phoneNumber, email, firstName, lastName);
        return true;
    }

    public boolean changePassword(String newPass)
    {
        user_db u = new user_db();
        return u.changePass(this.username, newPass);
    }

    public boolean changePhone(String newPhoneNumber){
        user_db u = new user_db();
        return u.changePhone(username, newPhoneNumber);
    }
}
