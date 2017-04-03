/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import com.google.gson.Gson;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.*;


/**
 * @author Bekzhan
 */
public class Util {

    public static String createGuid() {
        return UUID.randomUUID().toString();
    }

    public static Object getSingleResultOrNull(Query query) {
        List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        }
        throw new NonUniqueResultException();
    }

    public Object getSingleResultOrNull2(Query query) {
        List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        }
        throw new NonUniqueResultException();
    }


    public static Integer int_nvl(Integer val1, Integer val2) {
        if (val1 == null) {
            return val2;
        }

        return val1;
    }

    public static Boolean isNullOrEmptyCollection(List list) {
        return (list == null || list.size() == 0);
    }

    public static Boolean isNullOrEmpty(String value) {
        return (value == null || value.trim().isEmpty());
    }

    public static Integer getZeroOrIntegerValueByString(String value) {
        try {
            if (isNullOrEmpty(value))
                return 0;
            else
                return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String getFirstUpperString(String value) {
        if (!isNullOrEmpty(value)) {
            return value.substring(0, 1).toUpperCase() + value.substring(1, value.length()).toLowerCase();
        }
        return null;
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    private static String getFileSizeString(Integer fileSize) {
        if (fileSize <= 0) {
            return "0";
        }
        final String[] units = new String[]{"байт", "килобайт", "мегабайт", "гигабайт", "терабайт"};
        int digitGroups = (int) (Math.log10(fileSize) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(fileSize / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public static Long integerToLong(Integer val) {
        if (val == null) {
            return null;
        }

        return val.longValue();
    }

    public static Long bigIntegerToLong(BigInteger val) {
        if (val == null)
            return null;

        return val.longValue();
    }

    public static Long bigDecimalToLong(BigDecimal val) {
        if (val == null)
            return null;

        return val.longValue();
    }

    public static String bigDecimalToString(BigDecimal val) {
        if (val == null)
            return null;

        return val.toString();
    }

    public static BigDecimal stringToBigdecimal(String val) {
        if (isNullOrEmpty(val))
            return null;

        try {
            return new BigDecimal(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Short integerToShort(Integer val) {
        return val.shortValue();
    }

    public static Integer stringToInteger(String val) {
        if (isNullOrEmpty(val))
            return null;
        return Integer.parseInt(val);
    }

    public static BigDecimal stringToBigDecimal(String val) {
        if (isNullOrEmpty(val)) {
            return null;
        }

        return new BigDecimal(val.replaceAll(",", "."));
    }

    public static Long stringToLong(String val) {
        if (val == null || val.isEmpty()) {
            return null;
        }

        return Long.parseLong(val);
    }

    public static BigInteger stringToBigInteger(String val) {
        if (isNullOrEmpty(val)) {
            return null;
        }

        return new BigInteger(val);
    }

    public static BigInteger longToBigInteger(Long val) {
        return BigInteger.valueOf(val);
    }

    public static String objectToString(Object obj) {
        if (obj != null && !obj.toString().trim().isEmpty()) {
            return obj.toString();
        }

        return null;
    }
/*
    public static String dateObjectToString(Object obj) {
        if (obj != null && !obj.toString().trim().isEmpty()) {
            return DateUtil.dateToString((Date) obj, "dd.MM.yyyy");
        }

        return null;
    }*/

    public static String characterToString(Character val) {
        if (val != null) {
            return val.toString();
        }
        return null;
    }

    public static String objectToJson(Object obj) {
        return (new Gson().toJson(obj));
    }

    public static String getUserFullName(String lastName, String firstName, String patronName) {
        StringBuilder fullName = new StringBuilder();

        if (!isNullOrEmpty(lastName)) {
            fullName.append(getFirstUpperString(lastName));
        }

        if (!isNullOrEmpty(firstName)) {
            fullName.append(" ").append(getFirstUpperString(firstName));
        }

        if (!isNullOrEmpty(patronName)) {
            fullName.append(" ").append(getFirstUpperString(patronName));
        }

        return fullName.toString();
    }

    public static String getUserShortFullName(String lastName, String firstName, String patronName) {
        StringBuilder fullName = new StringBuilder();

        if (!isNullOrEmpty(lastName)) {
            fullName.append(getFirstUpperString(lastName));
        }

        if (!isNullOrEmpty(firstName)) {
            fullName.append(" ").append(firstName.substring(0, 1).toUpperCase()).append(".");
        }

        if (!isNullOrEmpty(patronName)) {
            fullName.append(patronName.substring(0, 1).toUpperCase()).append(".");
        }

        return fullName.toString();
    }

    public static String textConcat(String delimeter, String[] a) {
        StringBuilder sb = new StringBuilder();
        for (String anA : a) {
            if (!isNullOrEmpty(anA)) {
                if (!anA.equals("newline")) {
                    sb.append(anA);
                    sb.append(delimeter);
                } else {
                    sb.append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }


    /**
     * @return ServerName
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Double sum(List<? extends Number> numList) {
        Double result = 0.0;
        for (Number num : numList) {
            result += num.doubleValue();
        }
        return result;
    }

    /**
     * @author beljerin
     * @desc Из даты в календарь
     */
    public static Calendar getCalendar(Date date) {
        if (date != null) {
            Calendar cal = Calendar.getInstance(Locale.US);
            cal.setTime(date);
            return cal;
        }
        return null;
    }

    public static String nvl(String val1, String val2) {
        if (isNullOrEmpty(val1))
            return val2;
        return val1;
    }

    public static String getValueOrEmpty(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        return str;
    }

    /*
    * @desc getValue from url by Key
    * @since 13.02.2017
    * */
    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    // Webix не принимает ID = 0
    public static BigDecimal changeSFamRodId(BigDecimal id) {
        if (Objects.equals(id, BigDecimal.ZERO)) {
            return new BigDecimal(-1);
        }

        return id;
    }

    /**
     * @param osnId;
     * @param array;
     * @return {boolean} ищет внутри массива
     * @author beljerin
     */
    public static Boolean findIntoArrays(int osnId, int... array) {
        for (int anArray : array) {
            if (anArray == osnId) return true;
        }
        return false;
    }


}
