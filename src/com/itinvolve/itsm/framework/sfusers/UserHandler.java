/*
 * Copyright (c) ITinvolve, Inc. All Rights Reserved.
 */
package com.itinvolve.itsm.framework.sfusers;

import static com.itinvolve.itsm.framework.env.Setup.PATH_SALESFORCE_CREDENTIALS;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.itinvolve.itsm.framework.logs.Log;

/**
 * User Handler class. Provide methods to interact with salesforce users.
 * @author Adrian Espinoza
 *
 */
public class UserHandler {
    private static User CURRENT_USER;
    private static Map<String, User> AVAILABLE_USERS_MAP;//only one type
    private static String SFDC_TYPE_VALUE = null;// [expected values "login"(production)/"test"(sandbox)]
    private static String ID_VALUE = null;
    private static final String SFDC_TYPE_ATTRIBUTE = "sfdc-type";
    private static final String ID_ATRIBUTE = "id";

    /**
     * This method return the user in the current session.
     * @return Current user.
     */
    public static User getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(User newUser) {
        CURRENT_USER = newUser;
    }

    public static User getUser(Profile userProfile) {
        if (AVAILABLE_USERS_MAP == null || AVAILABLE_USERS_MAP.isEmpty() || !AVAILABLE_USERS_MAP.containsKey(userProfile.name())) {
            AVAILABLE_USERS_MAP = CredentialXmlParser.getUsersFromXmlFile(PATH_SALESFORCE_CREDENTIALS);
        }
        User resultUser = AVAILABLE_USERS_MAP.get(userProfile.name());
        if (null == resultUser) {
            System.out.println("Profile " + userProfile + " not defined, please check your credentials setting.");
            Log.LOGGER.error("-> Profile " + userProfile + " not defined, please check your credentials setting.");
        }
        return resultUser;
    }

    public static boolean setup() {
        AVAILABLE_USERS_MAP = CredentialXmlParser.getUsersFromXmlFile(PATH_SALESFORCE_CREDENTIALS);

        boolean wasSetup = false;
        if (AVAILABLE_USERS_MAP.size() > 0) {
            String env = CredentialXmlParser.getAttributeValue(SFDC_TYPE_ATTRIBUTE);
            SFDC_TYPE_VALUE = StringUtils.isNotBlank(env) ? env.toLowerCase() : SFDC_TYPE_VALUE;

            String id = CredentialXmlParser.getAttributeValue(ID_ATRIBUTE);
            ID_VALUE = StringUtils.isNotBlank(id) ? id : ID_VALUE;
            wasSetup = true;
        }
        return wasSetup;
    }

    public static User setupCurrentUser(Profile userProfile) {
        User tmpUser = getUser(userProfile);
        if (CURRENT_USER == null) {
            CURRENT_USER = tmpUser;
        } else if (!(currentIsTheSameProfile(userProfile))) {
            CURRENT_USER = tmpUser;//review conditional
        }
        return CURRENT_USER;
    }

    public static Boolean currentIsTheSameProfile(Profile userProfile) {
        User tmpUser = getUser(userProfile);
        return (tmpUser.profile.equals(CURRENT_USER.profile));
    }

    public static String getSfdcTypeAttribute() {
        return SFDC_TYPE_ATTRIBUTE;
    }

    public static String getSfdcTypeAttributeValue() {
        return SFDC_TYPE_VALUE;
    }

    public static String getIdAttributeValue() {
        return ID_VALUE;
    }
}
