package com.moffcomm.supatem.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import org.json.JSONObject;

import static java.security.AccessController.getContext;

/**
 * Created by jacobsFactory on 2017-02-09.
 */

public class EtcUtil {
    public static String getErrorMessage(String errorBodyString) {
        try {
            JSONObject jsonObject = new JSONObject(errorBodyString);
            if (jsonObject.has("errorMsg")) {
                return jsonObject.getString("errorMsg");
            } else {
                return "anonymous error";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String getAppVersion(Context context) {
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return pInfo.versionName;
    }
}
