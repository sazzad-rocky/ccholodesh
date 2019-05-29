/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package userDefinder;

import io.realm.RealmList;
import io.realm.RealmObject;

import model.PackageDetails;

/**
 * Created by Olivine on 6/15/2017.
 */

public class PackageReservation extends RealmObject {
    public String token;
    public PackageDetails packages;
}
