package com.databaker.hyzx.utils;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @description BASE64编解码工具
 */
public class BASE64Utils {

    static Base64.Decoder decoder = Base64.getDecoder();
    /**
     * 编码
     * @param bytes
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(byte[] bytes) throws UnsupportedEncodingException {
        return new String(Base64.getEncoder().encode(bytes), "UTF-8");
    }

    /**
     * 编码
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String s) throws UnsupportedEncodingException {
        return encode(s.getBytes("UTF-8"));
    }

    /**
     * 解码
     *
     * @param bytes
     * @return
     */
    public static byte[] decode(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }

    /**
     * 解码
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] decode(String s) throws UnsupportedEncodingException {
        return decode(s.getBytes("UTF-8"));
    }


    /**
     * 解码
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] decode1(String s) throws UnsupportedEncodingException {
        return  Base64.getDecoder().decode(s);
    }

    /**
     * 解码成字符串
     *
     * @param bytes
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeToString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(decode(bytes), "UTF-8");
    }

    /**
     * 解码成字符串
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeToString(String s) throws UnsupportedEncodingException {
        return decodeToString(s.getBytes("UTF-8"));
    }


    /**
     * 解码成字符串
     *
     * @param s
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decodeToString1(String s) throws UnsupportedEncodingException {

        return new String(s.getBytes("gbk"), "gbk");
    }


    //MD5加密
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }
}