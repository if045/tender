package com.softserveinc.tender.util;

public class Constants {
    public final static String DATE_PATTERN = "yyyy/MM/dd";
    public static final Boolean ENABLED_USER = true;
    public static final Boolean CHECKED_USER = false;
    public static final Character LEGAL_PERSON = 'L';
    public static final String CHECKED_PROFILE_UNCHECKED_STATUS = "Unchecked";

    //constants for UserServiceFacadeImpl.getHomePage();
    public static final String CUSTOMER_AND_SELLER_HOME_PAGE = "tenders";
    public static final String MODERATOR_HOME_PAGE = "moderatorHome";
    public static final String ADMINISTRATOR_HOME_PAGE = "administratorHome";
    public static final String UNKNOWN_USER = "anonymousUser";
}
