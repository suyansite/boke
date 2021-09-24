package com.databaker.hyzx.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Formatter;

/**
 * @author yzhu
 */
public class SignatureService {
    public static String getSignature(int devId, String devKey, Long timestamp) throws java.security.NoSuchAlgorithmException, java.security.InvalidKeyException {
        String signKey = devId + Long.toString(timestamp);

        SecretKeySpec signingKey = new SecretKeySpec(devKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] bytes = mac.doFinal(signKey.getBytes());
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }
}
