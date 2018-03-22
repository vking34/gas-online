package appLayer;

import com.google.gson.Gson;

public class Redirect {
    String url;
    boolean status;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String json = new Gson().toJson(this);
        return json;
    }
}
