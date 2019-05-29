package model;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;

public class AccommodationRoom extends RealmObject {
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        return;
    }

    @SerializedName("quantity")
    @Expose
    private int quantity = 1;
    @SerializedName("accommodation_service_id")
    @Expose
    private Integer accommodationServiceId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;

    //
    @SerializedName("district_name_bn")
    @Expose
    public String districtnameBn;
    @SerializedName("provider_name_bn")
    @Expose
    public String providerNameBn;
    @SerializedName("accommodation_category_name_bn")
    @Expose
    public String categoryNameBn;
    @SerializedName("bed_type_name_bn")
    @Expose
    public String bedTypeNameBn;


    public AccommodationRoom() {
//        this.isSelected = false;
//        this.quantity = 0;
//        this.accommodationServiceId = 0;
//        this.districtName = "";
//        this.districtId = 0;
//        this.providerId = 0;
//        this.providerName = "";
//        this.accommodationRoomId = 0;
//        this.accommodationRoomType = "";
//        this.accommodationRoomMaxOccupancy = "";
//        this.accommodationRoomPrice = "";
//        this.accommodationCategoryId = 0;
//        this.accommodationCategoryName = "";
//        this.accommodationRoomGalleryImage = "";
//        this.bedTypeName = "";
//        String uniqueID = UUID.randomUUID().toString();
//        UniqueId = uniqueID;

        //UniqueId = uniqueId;
    }

    @SerializedName("provider_name")

    @Expose
    private String providerName;
    @SerializedName("accommodation_room_id")
    @Expose
    private Integer accommodationRoomId;
    @SerializedName("accommodation_room_type")
    @Expose
    private String accommodationRoomType;
    @SerializedName("accommodation_room_max_occupancy")
    @Expose
    private String accommodationRoomMaxOccupancy;
    @SerializedName("accommodation_room_price")
    @Expose
    private String accommodationRoomPrice;
    @SerializedName("accommodation_category_id")
    @Expose
    private Integer accommodationCategoryId;
    @SerializedName("accommodation_category_name")
    @Expose
    private String accommodationCategoryName;

    @SerializedName("accommodation_room_gallery_image")
    @Expose
    public String accommodationRoomGalleryImage;

    @SerializedName("bed_type_name")
    @Expose
    public String bedTypeName;
    //start sazzad

    @SerializedName("check_in_date")
    @Expose
    private String check_in_date;

    @SerializedName("check_out_date")
    @Expose
    private String check_out_date;

    public String getCheck_in_date() {
        return check_in_date;
    }

    public AccommodationRoom setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
        return this;
    }
    public String getCheck_out_date() {
        return check_out_date;
    }

    public AccommodationRoom setCheck_out_date(String check_out_date) {
        this.check_out_date = check_out_date;
        return this;
    }
    // end

    public String UniqueId;

    public String getAccommodationRoomGalleryImage() {
        return accommodationRoomGalleryImage;
    }


//    @SerializedName("check_in_date")
//    @Expose
//    private String check_in_date;
//
//        public String getCheck_in_date(String check_in_date) {
//        return check_in_date;
//    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        return;
    }

    public String getDistrictName() {
        return districtName;
    }

    public AccommodationRoom setDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public AccommodationRoom setDistrictId(Integer districtId) {
        this.districtId = districtId;
        return this;
    }

    public Integer getAccommodationServiceId() {
        return accommodationServiceId;
    }

    public AccommodationRoom setAccommodationServiceId(Integer accommodationServiceId) {
        this.accommodationServiceId = accommodationServiceId;
        return this;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public AccommodationRoom setProviderId(Integer providerId) {
        this.providerId = providerId;
        return this;
    }

    //public List<BedType> getBedTypes () {return this.bedTypes;}

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getAccommodationRoomId() {
        return accommodationRoomId;
    }

    public void setAccommodationRoomId(Integer accommodationRoomId) {
        this.accommodationRoomId = accommodationRoomId;
    }

    public String getAccommodationRoomType() {
        return accommodationRoomType;
    }

    public void setAccommodationRoomType(String accommodationRoomType) {
        this.accommodationRoomType = accommodationRoomType;
    }

    public String getAccommodationRoomMaxOccupancy() {
        return accommodationRoomMaxOccupancy;
    }

    public void setAccommodationRoomMaxOccupancy(String accommodationRoomMaxOccupancy) {
        this.accommodationRoomMaxOccupancy = accommodationRoomMaxOccupancy;
    }

    public String getAccommodationRoomPrice() {
        return accommodationRoomPrice;
    }

    public void setAccommodationRoomPrice(String accommodationRoomPrice) {
        this.accommodationRoomPrice = accommodationRoomPrice;
    }

    public Integer getAccommodationCategoryId() {
        return accommodationCategoryId;
    }

    public void setAccommodationCategoryId(Integer accommodationCategoryId) {
        this.accommodationCategoryId = accommodationCategoryId;
    }

    public String getAccommodationCategoryName() {
        return accommodationCategoryName;
    }

    public void setAccommodationCategoryName(String accommodationCategoryName) {
        this.accommodationCategoryName = accommodationCategoryName;
    }


//    @Override
//    public boolean equals(Object obj) {
//
//        if (!(obj instanceof AccommodationRoom))
//        {
//            return false;
//        }
//        else
//        {
//            Gson gson = new Gson();
//            String json = gson.toJson(this);
//            Log.e("lft","LEft");
//            Log.e("JSON",json );
//
//            gson = new Gson();
//            json = gson.toJson(obj);
//            Log.e("rgt","Right");
//            Log.e("JSON",json );
//
//
//            boolean result = false;
//            try {
//                AccommodationRoom accommodationRoom  = (AccommodationRoom) obj;
//                if (this.accommodationRoomId.equals(accommodationRoom.accommodationRoomId) && this.accommodationServiceId==accommodationRoom.accommodationServiceId
//                        && this.districtId== accommodationRoom.districtId && this.accommodationRoomMaxOccupancy.equals(accommodationRoom.accommodationRoomMaxOccupancy)
//                        && this.providerId.equals(accommodationRoom.providerId) && this.accommodationRoomPrice.equals(accommodationRoom.accommodationRoomPrice))
//                {
//                    result =  true;
//                }
//
//            }catch (Exception e){
//                //result =  true;
//            }
//
//
//             return result;
//
//        }
//
//    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof AccommodationRoom)) {
            return false;
        } else {
            Log.e("EQUAL", "else");
            boolean result = false;
            try {
                Log.e("EQUAL", "try");
                AccommodationRoom accommodationRoom = (AccommodationRoom) obj;
                if (this.accommodationRoomId.equals(accommodationRoom.accommodationRoomId) &&
                        this.accommodationServiceId == accommodationRoom.accommodationServiceId
                        && this.districtId == accommodationRoom.districtId) {
                    Log.e("EQUAL", "if");
                    result = true;
                }

            } catch (Exception e) {
                //result =  true;
                Log.e("EQUAL", "catch");
            }


            return result;

        }

    }
}