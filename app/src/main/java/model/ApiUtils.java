package model;

import listeners.AuthenticationListener;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://cholodesh.com/api/portal/";
    public static AuthenticationListener getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(AuthenticationListener.class);
    }
}
