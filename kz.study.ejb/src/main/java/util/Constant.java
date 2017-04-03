/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormatSymbols;

/**
 * @author Bekzhan
 */
public class Constant {

    //constanta authorization
    public static final String USER = "user";

    //Стандартные лимит и старты для DataTable
    public static final int DEF_START_ZERO = 0;
    public static final int DEF_COUNT = 30;

    //Стандартные лимит и старты для DAction
    public static final int DEF_COUNT_DACTION = 20;

    /*
    * SMZSESSION constants
    */
    public static final int DEF_NUMBER_1 = 1;
    public static final int DEF_NUMBER_12 = 12;

    /*
    * PersonSession constants
    */
    public static final int DEF_LST_PRSS_CNT = 10;
    public static final int DEF_PAGESIZE_50 = 50;
    public static final int DEF_PERSON_10 = 10;
    public static final int DEF_PERSON_12 = 12;

    /*
    * DictionarySession constants
    */
    public static final int DICT_NUMBER_58 = 58;
    public static final int DICT_NUMBER_63 = 63;

    /*
    * DocumentSession constants
    */
    public static final int DEF_CNT_DECLAR = 12;

    /*
    * AppSession constants
    */
    public static final String APP_SOURCE_ZDOC = "zDoc";
    public static final String APP_SRC_ZDCPRV = "ZDocPrev";
    public static final String APP_SOURCE_MEG = "MEg";
    public static final String APP_LANG_VALUE_RU = "RU";

    public static final String ZTIP_NEW = "NEW";
    public static final String ZTIP_REC = "REC";
    public static final String ZTIP_MOV = "MOV";
    public static final String ZTIP_SUS = "SUS";

    public static final int CALENDAR_MONTH = 12;
    public static final int CALENDAR_DAY = 30;

    public static final String DEF_MINUS_1 = "-1";
    public static final String DEF_0 = "0";
    public static final String DEF_12 = "12";
    public static final String DEF_24 = "24";
    public static final String DEF_0704 = "0704";
    public static final String DEF_MSE = "MSE";
    public static final String DEF_ELC = "ELC";

    public static final String DEF_ERROR_MSG = "error";

    public static final String DEF_ADMIN = "admin";

    public static final int[] DEF_SS_OSN = {101, 102, 103, 104, 105};

    public static final String NOTICE_TYPE_XML = "xml";

    public static final Integer DEF_SS_NOTICATE_ID_1 = 1;
    public static final Integer DEF_SS_REFERENCES_ID_102 = 2;
    public static final Integer DEF_SS_REFERENCES_ID_105 = 3;
    public static final Integer DEF_SS_STATUS_ID_7 = 7;

    public static final String DEF_PATT_WITH = "dd MMMM";
    public static final String DEF_PATT_WOUT = "MMMM";
    public static final String DEF_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DEF_YYYY_MM_DD_DOT = "yyyy.MM.dd";
    public static final String DEF_MM_DD_YYYY_DOT = "MM.dd.yyyy";
    public static final String DEF_DD_MM_YYYY_DOT = "dd.MM.yyyy";
    public static final String DEF_PATT_ALL_VALUE = "dd.MM.yyyy HH.mm.ss";

    public static final DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };

    public static final String DEF_USERNAME = "j_username";
    public static final String DEF_PASSWORD = "j_password";

    public static final String DEF_TYPE_XML = "xml";

    public static final String DEF_USER_ROLE_BACK = "EMAKET_BACK";
    public static final String DEF_USER_ROLE_DEVELOPER = "EMAKET_DEVELOPER";

    public static final String DEF_JNDINAME_EM5 = "kz.gcvp_app_war_v1PU";
    public static final String DEF_JNDINAME_EMIMG = "kz.gcvp_app_img";

    public static final String DEF_N = "N";
    public static final String DEF_Y = "Y";

    public static final String DEF_ENCODING_UTF8 = "UTF-8";

    public static final String DEF_SLF = "SLF";

    public static final String DEF_ALL_LIFE = "(пожизненно)";
}
