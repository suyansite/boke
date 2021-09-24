package com.databaker.hyzx;

// api 2.0 校验签名 sample code
import com.databaker.hyzx.constant.Constants;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Signature{

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();

        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public static String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        return toHexString(mac.doFinal(data.getBytes()));
    }


    public static void main(String []args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String accessId = Constants.DevId;
        String accessKey = Constants.DevKey;
        long timestamp = System.currentTimeMillis() / 1000L;
        String signKey = accessId + timestamp;

        String signature = calculateRFC2104HMAC(signKey, accessKey);
        System.out.println("Send Request With Following Headers");
        System.out.println("    x-dev-id: " + accessId);
        System.out.println("    x-signature: " + signature);
        System.out.println("    x-request-send-timestamp: " + timestamp);

        /**
         *     x-dev-id: 22009
         *     x-signature: c4b124b9b9e9aea43b31f0a70955307027ed225147816ffb8d211a18d61d94d9
         *     x-request-send-timestamp: 1629100282
         */
    }
}
