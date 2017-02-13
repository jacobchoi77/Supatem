/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moffcomm.supatem.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.moffcomm.supatem.Constant;
import com.moffcomm.supatem.R;

import java.io.IOException;

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private String memberNumber;
    private SharedPreferences sharedPreferences;

    public RegistrationIntentService() {
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        if (intent.hasExtra("delete")) {
//            InstanceID instanceID = InstanceID.getInstance(this);
//            try {
//                instanceID.deleteToken(getString(R.string.gcm_defaultSenderId),
//                        GoogleCloudMessaging.INSTANCE_ID_SCOPE);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            memberNumber = intent.getStringExtra("memberNumber");
//            try {
//                InstanceID instanceID = InstanceID.getInstance(this);
//                String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
//                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
//
////                final String previousToken = sharedPreferences.getString(Constant.TOKEN_TO_SERVER, null);
////                if (previousToken == null || previousToken.equals(token) == false) {
//                sendRegistrationToServer(token);
////                }
//                Intent registrationComplete = new Intent(Constant.REGISTRATION_COMPLETE);
//                LocalBroadcastManager.getInstance(RegistrationIntentService.this).sendBroadcast(registrationComplete);
//            } catch (Exception e) {
//                e.printStackTrace();
//                sharedPreferences.edit().remove(Constant.TOKEN_TO_SERVER).apply();
//                sharedPreferences.edit().putBoolean(Constant.IS_SENT_TOKEN_TO_SERVER, false).apply();
//            }
//        }


    }

    private void sendRegistrationToServer(final String token) {
//        DeviceDTO deviceDTO = new DeviceDTO(memberNumber, Constant.DEVICE_CODE, token, "");
//        Supa7Repository mSupa7Repository = Injection.provideSupa7Repository(getApplicationContext());
//        mSupa7Repository.uploadDeviceInfo(deviceDTO, new Supa7DataSource.SuccessFailCallback() {
//            @Override
//            public void onSuccess() {
//                sharedPreferences.edit().putString(Constant.TOKEN_TO_SERVER, token).apply();
//                sharedPreferences.edit().putBoolean(Constant.IS_SENT_TOKEN_TO_SERVER, true).apply();
//            }
//
//            @Override
//            public void onFailure(String error) {
//                sharedPreferences.edit().remove(Constant.TOKEN_TO_SERVER).apply();
//                sharedPreferences.edit().putBoolean(Constant.IS_SENT_TOKEN_TO_SERVER, false).apply();
//            }
//        });

    }


}
