package model;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreparences {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    private static final String SHARED_PREFARENCE_NAME = "mysharedpreparence";
    private static final String LOG_IN_STATUS = "loginstatus";
    private static final String REGISTER_IN_STATUS = "registerstatus";
    private static final String EMAIL = "email";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";
    private static final String Ruser = "generalUser";
    private static final String SUPERVISOR_ID = "sid";
    public MySharedPreparences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences
                (SHARED_PREFARENCE_NAME, Context.MODE_PRIVATE);
        preferences.getString("mail", "no mail found");
    }

    public boolean isLogedIn() {
        return preferences.getBoolean(LOG_IN_STATUS, false);


    } public boolean isLogedInrEGISTER() {
        return preferences.getBoolean(REGISTER_IN_STATUS, false);
    }


    public void setLogedInDataRegister(boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(REGISTER_IN_STATUS, value);
        editor.commit();
    }
    public void setLogedInData(boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOG_IN_STATUS, value);
        editor.commit();
    }

    public void setEmail(String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL, value);
        editor.commit();
    }

    public void setUSER_NAME(String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_NAME, value);
        editor.commit();
    }
    public String getUSER_NAME() {
        return preferences.getString(USER_NAME, "");
    }

    public void setUid(int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PASSWORD, value);
        editor.commit();
    }

    public void setRegristerUid(int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Ruser, value);
        editor.commit();
    }
    public int getRegristerUid() {
        return preferences.getInt(Ruser,0);
    }

    public void setSuperVisorid(String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SUPERVISOR_ID, value);
        editor.commit();
    }

    public String getEmail() {
        return preferences.getString(EMAIL, "");
    }

    public int getUid() {
        return preferences.getInt(PASSWORD,0);
    }
    public String getSuperVisorid() {
        return preferences.getString(SUPERVISOR_ID, "");
    }

}
