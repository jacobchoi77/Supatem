package com.moffcomm.supatem.util;

import android.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jacobsFactory on 2016-08-26.
 */
public class AuthUtil {

    public static String getHash(long time) {
        try {
            String message = String.valueOf(time / 1000);

            String secret = "L3vD3YyzRLh3P7VF4blDLMZWK1IT1M249eXuvxj6XDw=";

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            String hash = Base64.encodeToString(sha256_HMAC.doFinal(message.getBytes()), Base64.NO_WRAP);
            return hash;
        } catch (Exception e) {
            System.out.println("Error");
        }
        return null;
    }

}
