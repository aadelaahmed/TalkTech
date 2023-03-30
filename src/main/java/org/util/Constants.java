package org.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String PERSISTENCE_UNIT_NAME = "talktech_unit";

    public static final String CATEGORY_MOBILE_PHONES = "mobile phones";
    public static final String CATEGORY_SMART_WATCHES = "smart watches";
    public static final String CATEGORY_AIRBUDS = "airbuds";
    public static final String CATEGORY_MOBILE_PHONES_ID = "1";
    public static final String CATEGORY_SMART_WATCHES_ID = "2";
    public static final String CATEGORY_AIRBUDS_ID = "3";
    public static final String PRODUCT_ATTRIBUTE = "product";
    public static final String PRODUCT_ATTRIBUTE_JSON = "productJson";
    public static final List<String> categories = Arrays.asList("mobile phones","headphones","smart watches");
    public static final List<String> brands = Arrays.asList("apple","samsung","oppo", "realme","soundcore");

    public static final String USER_EMAIL = "aa@gmail.com";
    public static final String SHOW_CART_URL_MAPPING= "showCart";
    public static final String UPDATE_CART_URL_MAPPING= "updateInCart";
    public static final String DELETE_CART_URL_MAPPING= "removeFromCart";
    public static final String ADD_CART_URL_MAPPING= "addToCart";
    public static final String CHECKOUT_URL_MAPPING = "checkout";
    public static final String CART_ID_SESSION_ATTR= "CART_ID";
    public static final String EMAIL_SESSION_ATTR= "email";
    public static final String USER_NAME_SESSION_ATTR= "userName";
    public static final String LOGGED_IN_SESSION_ATTR= "LoggedIn";
    public static final String USERT_TYPE_SESSION_ATTR= "userType";
}
