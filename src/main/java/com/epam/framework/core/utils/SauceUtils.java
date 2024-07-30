package com.epam.framework.core.utils;

import com.saucelabs.saucerest.SauceREST;
import com.epam.framework.core.exceptions.CollabFrameworkRuntimeException;
import com.epam.framework.ui.UiConstants;

import java.util.HashMap;
import java.util.Map;

public class SauceUtils {
    /**
     * Login singleton instance of the Sauce REST client
     */
    private static SauceREST sauceClient;

    private SauceUtils() {
    }

    private static SauceREST getSauceRestClient() {
        if (sauceClient == null) {
            sauceClient = new SauceREST(UiConstants.USER_NAME, UiConstants.ACCESS_KEY, UiConstants.SAUCE_DATA_CENTER);
        }
        return sauceClient;
    }

    public static synchronized void updateResults(boolean testResults, String sessionId) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", testResults);
        try {
            /*getSauceRestClient().
            getSauceRestClient().updateJobInfo(sessionId, updates);*/
        } catch (Exception ex) {
            throw new CollabFrameworkRuntimeException("Getting exception while updating the test result status...!!!", ex);
        }
    }
}
