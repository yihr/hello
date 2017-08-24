package com.yihr.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yihr-pc on 2017/8/23.
 */
public class HexUtil {

    public static final String HexUpper = "0123456789ABCDEF";

    public static String stringFromByteArray(byte[] bytes) {
        if (null == bytes || 0 == bytes.length) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            builder.append(new String(bytes, "UTF-8"));
            builder.append("\n");
        }
        return builder.toString();
    }

    public static byte[] bytesFromHexBytes(byte[] hexByteArray) {
        if (null == hexByteArray || 0 == hexByteArray.length) {
            return null;
        }

        List<Byte> byteList = new ArrayList<Byte>();

        int arrayLength = hexByteArray.length;
        for (int i = 0; i < arrayLength-1; i++) {
            int ixValue = HexUpper.indexOf(hexByteArray[i]);
            int ixNext = HexUpper.indexOf(hexByteArray[i+1]);

            if (ixValue >= 0 && ixValue <= 0xf && ixNext >= 0 && ixNext <= 0xf) {
                int value = (ixValue << 4) | ixNext;
                byteList.add(Byte.valueOf((byte) value));
                i++;
            }
        }

        return byteArrayFromList(byteList);
    }


    public static byte[] byteArrayFromList(List<Byte> byteList){
        if (null == byteList || byteList.isEmpty()) {
            return null;
        }

        //将Byte类型List转成Byte类型Array
        int size = byteList.size();
        byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            byteArray[i] = byteList.get(i).byteValue();
        }
        return byteArray;
    }


    public static void main(String[] args) {
        byte[] bytes = new byte[0];
        try {
            bytes = bytesFromHexBytes("7f80f 中f eff0a1".toUpperCase().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(bytes));
    }
}
