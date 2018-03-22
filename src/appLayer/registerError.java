package appLayer;

public class registerError {
    private String usernameError;
    private String phoneNumberError;
    private String emailError;

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    @Override
    public String toString()
    {

        String json = "{\"usernameError\":\"";
        if(usernameError == null)
        {
            json += "\",\"phoneNumberError\":\"";
        }
        else
        {
            json += usernameError + "\",\"phoneNumberError\":\"";
        }

        if(phoneNumberError == null)
        {
            json += "\",\"emailError\":\"";
        }
        else
        {
            json += phoneNumberError + "\",\"emailError\":\"";
        }

        if (emailError == null)
        {
            json += "\"}";
        }
        else
        {
            json += emailError + "\"}";
        }

        return json;
    }
}
