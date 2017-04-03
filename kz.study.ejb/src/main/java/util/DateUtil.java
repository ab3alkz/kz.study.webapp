/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * @author Bekzhan
 */
public class DateUtil {

    /*public static String dateToString(Date date, String f) {
        if (date != null) {
            return new SimpleDateFormat(f, Locale.ENGLISH).format(date);
        }
        return null;
    }

    public static String dateToString(Date date) {
        return dateToString(date, DEF_DD_MM_YYYY_DOT);
    }

    public static String dateTimeToString(Date date, String f) {
        if (date != null) {
            return new SimpleDateFormat(f, Locale.ENGLISH).format(date);
        }
        return null;
    }

    public static Date stringToDate(String date) {
        if (!isNullOrEmpty(date)) {
            try {
                date = date.substring(0, 10);

                if (date.contains(".")) {
                    return new SimpleDateFormat(DEF_DD_MM_YYYY_DOT, Locale.ENGLISH).parse(date);
                } else if (date.contains("-")) {
                    return new SimpleDateFormat(DEF_YYYY_MM_DD, Locale.ENGLISH).parse(date);
                }
                return new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH).parse(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static Date stringToDate(String date, String format) {
        if (!isNullOrEmpty(date)) {
            try {
                date = date.substring(0, 10);
                return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static java.sql.Date stringToSqlDate(String date) {
        if (!isNullOrEmpty(date)) {
            return new java.sql.Date(stringToDate(date).getTime());
        }
        return null;
    }

    public static java.sql.Date stringToSqlDate(String date, String format) {
        if (date != null && !date.isEmpty()) {
            return new java.sql.Date(stringToDate(date, format).getTime());
        }
        return null;
    }

    public static Date stringToDates(String date) {
        if (!isNullOrEmpty(date)) {
            try {
                date = date.substring(0, 10);
                if (date.contains("-")) {
                    date = date.replace("-", ".");
                    return new SimpleDateFormat(DEF_YYYY_MM_DD_DOT, Locale.ENGLISH).parse(date);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    *//**
     * @param date;
     * @param sexId;
     * @return Date
     * @author a.kussein
     * (calculate the Дата назначение)
     * @since 29-07-2016
     *//*
    public static Date calculateDateObr(final Date date, Character sexId) {
        if (date != null) {
            Calendar dob = Calendar.getInstance();
            int id = 0;
            dob.setTime(date);
            if (sexId.equals('0')) {
                id = 58;
            } else if (sexId.equals('1')) {
                id = 63;
            }

            dob.add(Calendar.YEAR, id);
            return dob.getTime();
        }
        return null;
    }

    *//**
     * @param d1;
     * @param d2;
     * @return int
     * @author a.kussein
     * (разница между датами)
     * @since 29-07-2016
     *//*
    public static int differenceTwoYear(Date d1, Date d2) {
        if (d1 != null && d2 != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d1);
            int year = cal.get(Calendar.YEAR);
            cal.setTime(d2);
            int year2 = cal.get(Calendar.YEAR);
            return (year2 - year);
        }
        return 0;
    }

    public static Date getDateByYMD(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, 0, 0);
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

    *//*
    * @desc добавляет день в дату
    *//*
    public static Date getCalendarDateAddDay(String dt, int day) {
        if (!isNullOrEmpty(dt)) {
            Calendar date = Calendar.getInstance();
            date.setTime(stringToDate(dt));
            date.add(Calendar.DATE, +day);
            return date.getTime();
        }
        return null;
    }

    public static Date getDateByXmlDate(XMLGregorianCalendar dte) throws Exception {
        if (dte != null) {
            return getDateByYMD(dte.getYear(), dte.getMonth(), dte.getDay());
        }
        return null;
    }

    *//**
     * @param d;
     * @return int
     * @author a.kussein
     * (date to int)
     * @since 29-07-2016
     *//*
    public static int dateToInt(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
            return cal.get(Calendar.YEAR);
        } else
            return 0;
    }

    *//**
     * @param str;
     * @return date format or null
     * @author a.kussein
     * mmyyyy to dd.mm.yyyy
     * @since 03-02-2017
     *//*
    public static String getDateToBeginOrEnd(String str) throws Exception {
        if (!isNullOrEmpty(str)) {
            return str.length() > 6 ? "01." + str : null;
        }
        return null;
    }*/
}
