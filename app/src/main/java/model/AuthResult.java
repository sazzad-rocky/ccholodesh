package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResult {

    @SerializedName("output")
    @Expose
    private String output;
    @SerializedName("customer_phone")
    @Expose
    private String CustomerPhone;
    @SerializedName("authentication")
    @Expose
    private Boolean authentication;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;

    public String getcustomerEmail() {
        return customerEmail;
    }
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public AuthResult setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
        return this;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

}