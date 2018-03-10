package appLayer;

public class User {

    public boolean isValidUserCredentials(String username, String password)
    {
        if(username.equals("vking34") && password.equals("test123"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
