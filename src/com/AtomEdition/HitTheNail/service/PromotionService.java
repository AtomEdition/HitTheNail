package com.AtomEdition.HitTheNail.service;

import android.content.SharedPreferences;

/**
 * Created by Ì on 14.01.2016.
 */
public abstract class PromotionService {

    public static final String OTHER_APPS_SCREEN = "otherAppsScreen";
    public static final String CHECK_BOX_STATE = "checkBoxState";

    public static void setPromotionState (SharedPreferences.Editor editor, boolean state) {
        editor.putBoolean(CHECK_BOX_STATE, state);
        editor.commit();
    }

    public static boolean getPromotionState (SharedPreferences preferences) {

        return preferences.getBoolean(CHECK_BOX_STATE, false);
    }

}
