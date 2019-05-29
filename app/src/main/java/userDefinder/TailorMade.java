
package userDefinder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import model.AccommodationRoom;
import model.Itenerary;
import model.Route;
import model.TransportProvider;

/**
 * Created by Olivine on 6/1/2017.
 */

public class TailorMade extends RealmObject {
    @PrimaryKey
    public int tailormade_id;
    public int deapartDistrictId;
    public int destinationDistrictId;
    public String departDistrictName;
    public String destinationDistrictName;
    public String departDate;
    public String returnDate;
    public int numberOFDays;
    public int numberOFTourists;

    public int transportCost;
    public int accommodationCost;
    public int itineraryCost;

    public RealmList<Route> routes;
    public RealmList<AccommodationRoom> accommodationRooms;
    public RealmList<Itenerary> iteneraries;



    public String getDepartDistrictName() {
        return departDistrictName;
    }

    public TailorMade setDepartDistrictName(String departDistrictName) {
        this.departDistrictName = departDistrictName;
        return this;
    }

    public String getDestinationDistrictName() {
        return destinationDistrictName;
    }

    public TailorMade setDestinationDistrictName(String destinationDistrictName) {
        this.destinationDistrictName = destinationDistrictName;
        return this;
    }

    public int getDeapartDistrictId() {
        return deapartDistrictId;
    }

    public TailorMade setDeapartDistrictId(int deapartDistrictId) {
        this.deapartDistrictId = deapartDistrictId;
        return this;
    }

    public int getDestinationDistrictId() {
        return destinationDistrictId;
    }

    public TailorMade setDestinationDistrictId(int destinationDistrictId) {
        this.destinationDistrictId = destinationDistrictId;
        return this;
    }

    public String getDepartDate() {
        return departDate;
    }

    public TailorMade setDepartDate(String departDate) {
        this.departDate = departDate;
        return this;
    }

    public String getReturnDate() {
        return returnDate;
    }


    public TailorMade setReturnDate(String returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public int getNumberOFDays() {
        return numberOFDays;
    }

    public TailorMade setNumberOFDays(int numberOFDays) {
        this.numberOFDays = numberOFDays;
        return this;
    }

    public int getNumberOFTourists() {
        return numberOFTourists;
    }

    public TailorMade setNumberOFTourists(int numberOFTourists) {
        this.numberOFTourists = numberOFTourists;
        return this;
    }




//    public List<TailorMade> getallTailormade(){
//        return new Select().from(TailorMade.class).execute();
//    }
}
