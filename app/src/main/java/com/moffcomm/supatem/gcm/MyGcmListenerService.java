/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moffcomm.supatem.gcm;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.gms.gcm.GcmListenerService;
import com.moffcomm.supatem.data.remote.model.FIELD;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        final String message = data.getString(FIELD.MESSAGE);
        final String type = data.getString(FIELD.TYPE_CODE);
        int boardNumber = -1;
        if (TextUtils.isEmpty(data.getString(FIELD.BOARD_NO)) == false) {
            boardNumber = Integer.valueOf(data.getString(FIELD.BOARD_NO));
        }
//        if (type.equals("01")
//                && PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constant.PREF_EVENT, false)) {
//            sendNotification(message, true, boardNumber);
//        } else if (type.equals("02")
//                && PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constant.PREF_MONEY, false)) {
//            sendNotification(message, false, boardNumber);
//        }


    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message     GCM message received.
     * @param boardNumber
     */
    private void sendNotification(String message, boolean isEvent, int boardNumber) {
//        Intent intent;
//        if (isEvent) {
//            intent = new Intent(this, NoticeActivity.class);
//            intent.putExtra("from", "gcm");
//            intent.putExtra("boardNumber", boardNumber);
//        } else {
//            intent = new Intent();
//        }
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.launcher_small)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.launcher_large))
//                .setContentTitle(getString(R.string.app_name))
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
//                .setVibrate(new long[]{0l})
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
