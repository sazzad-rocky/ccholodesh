package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmOperator {

    @SerializedName("tailormade_id")
    @Expose
    String   tailormade_id;
    @SerializedName("tailormade_operator_id")
    @Expose
    String   tailormade_operator_id;

    @SerializedName("tailormade_customer_id")
    @Expose
    String   tailormade_customer_id;

    @SerializedName("tailormade_reply_id")
    @Expose
    String   tailormade_reply_id;

    public void settailormade_reply_id(String tailormade_reply_id) {
        this.tailormade_reply_id = tailormade_reply_id;
        return;
    }


    public String gettailormade_id() {
        return tailormade_id;
    }
    public void settailormade_id(String tailormade_id) {
        this.tailormade_id = tailormade_id;
        return;
    }

    public String gettailormade_operator_id() {
        return tailormade_operator_id;
    }
    public void settailormade_operator_id(String tailormade_operator_id) {
        this.tailormade_operator_id = tailormade_operator_id;
        return;
    }

    public String gettailormade_customer_id() {
        return tailormade_customer_id;
    }
    public void settailormade_customer_id(String tailormade_customer_id) {
        this.tailormade_customer_id = tailormade_customer_id;
        return;
    }

}
