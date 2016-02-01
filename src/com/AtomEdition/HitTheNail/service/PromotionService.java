package com.AtomEdition.HitTheNail.service;

import android.content.SharedPreferences;

/**
 * Created by � on 14.01.2016.
 */
public class PromotionService {

    public static final String OTHER_APPS_SCREEN = "otherAppsScreen";
    public static final String CHECK_BOX_STATE = "checkBoxState";

    public static PromotionService instance;
    public static PromotionService getInstance() {

        if (instance == null) {
            instance = new PromotionService();
        }
        return instance;
    }

    public void setPromotionState (SharedPreferences.Editor editor, boolean state) {
        editor.putBoolean(CHECK_BOX_STATE, state);
        editor.commit();
    }

    public boolean getPromotionState (SharedPreferences preferences) {

        return preferences.getBoolean(CHECK_BOX_STATE, false);
    }

}
