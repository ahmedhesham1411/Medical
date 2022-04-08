package com.gharbia.medical.Utilities;

import com.gharbia.medical.fragment.AccountFragment;
import com.gharbia.medical.fragment.AppointmentsFragment;
import com.gharbia.medical.fragment.CurrentAppointmentsFragment;
import com.gharbia.medical.fragment.DewanFragment;
import com.gharbia.medical.fragment.HomeFragment;
import com.gharbia.medical.fragment.PreviousAppointmentsFragment;
import com.gharbia.medical.fragment.QuestionsFragment;

import java.util.List;


public abstract class GlobalVariables {

    public static final String GET_ADDRESSES_FROM_LATLNG_URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng=";
    public static List<String> images;
    public static List<String> images2;

    public static double LOCATION_LAT = 29.9434477 ;
    public static double LOCATION_LNG = 31.2731497;
    public static String ADDRESS  = "No Address";

    public static String qrCode = "";

    public static boolean FINISH_ACTIVITY = false;
    public static boolean IS_CONNECTED = false;

    public static boolean FINISH_ACTIVITY_2 = false;

    public static boolean FINISH_ACTIVITY_3 = false;

    public static boolean FINISH_ACTIVITY_REGISTER = false;
    public static int HOME_RESUME = 0;

    public static final int MAIN_FRAGMENT_ID = 1;
    public static final String MAIN_FRAGMENT_TAG = HomeFragment.class.getSimpleName();
    public static final int QUESTIONS_FRAGMENT_ID = 2;
    public static final String QUESTIONS_FRAGMENT_TAG = QuestionsFragment.class.getSimpleName();
    public static final int ACCOUNT_FRAGMENT_ID = 3;
    public static final String ACCOUNT_FRAGMENT_TAG = AccountFragment.class.getSimpleName();
    public static final int CURRENT_APPOINTMENTS_FRAGMENT_ID = 4;
    public static final String CURRENT_APPOINTMENTS_FRAGMENT_TAG = CurrentAppointmentsFragment.class.getSimpleName();
    public static final int PREVIOUS_APPOINTMENTS_FRAGMENT_ID = 5;
    public static final String PREVIOUS_APPOINTMENTS_FRAGMENT_TAG = PreviousAppointmentsFragment.class.getSimpleName();
    public static final int APPOINTMENTS_FRAGMENT_ID = 6;
    public static final String APPOINTMENTS_FRAGMENT_TAG = AppointmentsFragment.class.getSimpleName();
    public static final int DIWAN_FRAGMENT_ID = 7;
    public static final String DIWAN_FRAGMENT_TAG = DewanFragment.class.getSimpleName();
}