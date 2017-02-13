package com.moffcomm.supatem.ui.splash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.moffcomm.supatem.Constant;
import com.moffcomm.supatem.R;
import com.moffcomm.supatem.data.callbacks.CallbackVersion;
import com.moffcomm.supatem.data.local.model.DBManager;
import com.moffcomm.supatem.data.local.model.UserEntity;
import com.moffcomm.supatem.data.remote.WebServiceManager;
import com.moffcomm.supatem.data.remote.model.Version;
import com.moffcomm.supatem.gcm.RegistrationIntentService;
import com.moffcomm.supatem.ui.SimpleDialogFragment;
import com.moffcomm.supatem.ui.main.MainActivity;
import com.moffcomm.supatem.ui.tutorial.TutorialActivity;
import com.moffcomm.supatem.util.EtcUtil;

public class SplashActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private static final String TAG = "SplashActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isGcmDone = false;
    private boolean isUpdateCheckDone = false;
    private boolean isReceiverRegistered;
    private UserEntity user;
    private long startTime;
    private WebServiceManager webServiceManager;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startTime = System.currentTimeMillis();
        webServiceManager = WebServiceManager.getInstance(getApplication());
        dbManager = DBManager.getInstance(this);
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constant.PREF_INIT, false) == false) {
            initialize();
        }
        webServiceManager.getVersion(new CallbackVersion() {
            @Override
            public void onVersionLoaded(Version version) {
                if (EtcUtil.getAppVersion(SplashActivity.this).compareTo(version.getAppVersion()) < 0) {
                    showUpdateDialog();
                } else {
                    if (isGcmDone) {
                        letsGetOutOfHere();
                    } else
                        isUpdateCheckDone = true;
                }
            }

            @Override
            public void onDataNotAvailable(String errorMsg) {
                if (isGcmDone) {
                    letsGetOutOfHere();
                } else
                    isUpdateCheckDone = true;
            }

            @Override
            public void onFailure() {

            }
        });
        user = dbManager.getUser();
        if (user != null)
            initGcm();
        else {
            if (isUpdateCheckDone) {
                letsGetOutOfHere();
            } else
                isGcmDone = true;
        }
    }

    private void letsGetOutOfHere() {
        long waitTime = Constant.SPLASH_TIME_OUT - (System.currentTimeMillis() - startTime);
        final boolean tutorialDone = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(Constant.PREF_TUTORIAL_DONE, false);
        if (waitTime > 0)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent;
                    if (tutorialDone) {
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    } else {
                        intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    }
                    startActivity(intent);
                }
            }, waitTime);
        else {
            Intent intent;
            if (tutorialDone) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, TutorialActivity.class);
            }
            startActivity(intent);
        }
    }

    private void initialize() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(Constant.PREF_INIT, true).commit();
    }

    private void initGcm() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (isUpdateCheckDone) {
                    LocalBroadcastManager.getInstance(SplashActivity.this).unregisterReceiver(mRegistrationBroadcastReceiver);
                    letsGetOutOfHere();
                } else
                    isGcmDone = true;
            }
        };
        registerReceiver();
        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);

            intent.putExtra("memberNumber", user.getMemberNumber());
            startService(intent);
        }
    }

    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Constant.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private void showUpdateDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        SimpleDialogFragment updateDialogFragment = SimpleDialogFragment.newInstance(
                R.string.update_title, R.string.update_msg, R.string.update, R.string.next);

        updateDialogFragment.setCallback(new SimpleDialogFragment.CallbackSimpleDialog() {
            @Override
            public void onPositiveClick() {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                finish();
            }

            @Override
            public void onNegativeClick() {
                finish();
            }

            @Override
            public void onNeutralClick() {

            }
        });
        updateDialogFragment.setCancelable(false);
        updateDialogFragment.show(ft, "dialog");
    }


}
