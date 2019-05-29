package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelRespons {
    @SerializedName("tailormade_reply_id")
    @Expose
    String   tailormade_reply_id;

    @SerializedName("tailormade_id")
    @Expose
    String   tailormade_id;

    @SerializedName("tailormade_operator_id")
    @Expose
    String   tailormade_operator_id;

    @SerializedName("tailormade_operator_name")
    @Expose
    String   tailormade_operator_name;

    @SerializedName("tailormade_customer_id")
    @Expose
    String   tailormade_customer_id;

    @SerializedName("tailormade_reply_message")
    @Expose
    String   tailormade_reply_message;
//start
    @SerializedName("Confarmation_massage")
    @Expose
    String   Confarmation_massage;

    @SerializedName("tailormade_reply_file")
    @Expose
    String  reply_file;

    public String getreply_file() {
        return reply_file;
    }


    @SerializedName("tailormade_confirm_status")
    @Expose
    String  tailormade_confirm_status;

    public String gettailormade_confirm_status() {
        return tailormade_confirm_status;
    }




    public String gettailormade_reply_message() {
        return tailormade_reply_message;
    }

    public void setConfarmation_massage(String Confarmation_massage) {

        this.Confarmation_massage = Confarmation_massage;
        return;
    }


    public void settailormade_reply_message(String tailormade_reply_id) {

        this.tailormade_reply_message = tailormade_reply_id;
        return;
    }



    public String gettailormade_reply_id() {
        return tailormade_reply_id;
    }

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

    public String gettailormade_operator_name() {
        return tailormade_operator_name;
    }
    public void settailormade_operator_name(String tailormade_operator_name) {
        this.tailormade_operator_name = tailormade_operator_name;
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
