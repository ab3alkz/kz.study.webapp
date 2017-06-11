package kz.study.session;

import kz.study.entity.DAntonym;
import kz.study.entity.DSynonym;
import kz.study.gson.*;
import kz.study.util.Utx;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.*;

import static kz.study.session.AppSession.language;
import static kz.study.util.Util.getGsonResult;
import static kz.study.util.Util.isNullOrEmpty;

/**
 * @author beljerin
 */
@Stateless
public class AnalizeSession extends Utx {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalizeSession.class);
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String url2 = "jdbc:mysql://localhost:3306/armasecond";
    private static final String user = "pma";
    private static final String pass = "123456";
    @PersistenceContext(unitName = "armaSecond")
    private EntityManager em2;

    public static String getTranslateString(String text) {
        char[] myCharArray = text.toCharArray();
        StringBuilder latynTest = new StringBuilder();
        for (char c : myCharArray) {
            latynTest.append(getTestStringLatyn(c));
        }
        return latynTest.toString();
    }

    public static String getTestStringLatyn(char text) {

        String newValue = String.valueOf(text);

        switch (newValue) {
            case "А":
                newValue = newValue.replace(newValue, "A");
                break;
            case "а":
                newValue = newValue.replace(newValue, "a");
                break;
            case "Ә":
                newValue = newValue.replace(newValue, "AE");
                break;
            case "ә":
                newValue = newValue.replace(newValue, "ae");
                break;
            case "Б":
                newValue = newValue.replace(newValue, "B");
                break;
            case "б":
                newValue = newValue.replace(newValue, "b");
                break;
            case "В":
                newValue = newValue.replace(newValue, "V");
                break;
            case "в":
                newValue = newValue.replace(newValue, "v");
                break;
            case "Г":
                newValue = newValue.replace(newValue, "G");
                break;
            case "г":
                newValue = newValue.replace(newValue, "g");
                break;
            case "Ғ":
                newValue = newValue.replace(newValue, "GH");
                break;
            case "ғ":
                newValue = newValue.replace(newValue, "gh");
                break;
            case "Д":
                newValue = newValue.replace(newValue, "D");
                break;
            case "д":
                newValue = newValue.replace(newValue, "d");
                break;
            case "Ё":
            case "Е":
            case "Э":
                newValue = newValue.replace(newValue, "E");
                break;
            case "ё":
            case "е":
            case "э":
                newValue = newValue.replace(newValue, "e");
                break;
            case "Ж":
                newValue = newValue.replace(newValue, "ZH");
                break;
            case "ж":
                newValue = newValue.replace(newValue, "zh");
                break;
            case "З":
                newValue = newValue.replace(newValue, "Z");
                break;
            case "з":
                newValue = newValue.replace(newValue, "z");
                break;
            case "и":
                newValue = newValue.replace(newValue, "ij");
                break;
            case "И":
                newValue = newValue.replace(newValue, "IJ");
                break;
            case "Й":
                newValue = newValue.replace(newValue, "J");
                break;
            case "й":
                newValue = newValue.replace(newValue, "j");
                break;
            case "К":
                newValue = newValue.replace(newValue, "K");
                break;
            case "к":
                newValue = newValue.replace(newValue, "k");
                break;
            case "Қ":
                newValue = newValue.replace(newValue, "Q");
                break;
            case "қ":
                newValue = newValue.replace(newValue, "q");
                break;
            case "Л":
                newValue = newValue.replace(newValue, "L");
                break;
            case "л":
                newValue = newValue.replace(newValue, "l");
                break;
            case "М":
                newValue = newValue.replace(newValue, "M");
                break;
            case "м":
                newValue = newValue.replace(newValue, "m");
                break;
            case "н":
                newValue = newValue.replace(newValue, "n");
                break;
            case "Н":
                newValue = newValue.replace(newValue, "N");
                break;
            case "Ң":
                newValue = newValue.replace(newValue, "NG");
                break;
            case "ң":
                newValue = newValue.replace(newValue, "ng");
                break;
            case "О":
                newValue = newValue.replace(newValue, "O");
                break;
            case "о":
                newValue = newValue.replace(newValue, "o");
                break;
            case "Ө":
                newValue = newValue.replace(newValue, "OE");
                break;
            case "ө":
                newValue = newValue.replace(newValue, "oe");
                break;
            case "П":
                newValue = newValue.replace(newValue, "P");
                break;
            case "п":
                newValue = newValue.replace(newValue, "p");
                break;
            case "Р":
                newValue = newValue.replace(newValue, "R");
                break;
            case "р":
                newValue = newValue.replace(newValue, "r");
                break;
            case "С":
            case "Ц":
                newValue = newValue.replace(newValue, "S");
                break;
            case "с":
            case "ц":
                newValue = newValue.replace(newValue, "s");
                break;
            case "Т":
                newValue = newValue.replace(newValue, "T");
                break;
            case "т":
                newValue = newValue.replace(newValue, "t");
                break;
            case "У":
            case "Ұ":
                newValue = newValue.replace(newValue, "U");
                break;
            case "у":
            case "ұ":
                newValue = newValue.replace(newValue, "u");
                break;
            case "Ү":
                newValue = newValue.replace(newValue, "UE");
                break;
            case "ү":
                newValue = newValue.replace(newValue, "ue");
                break;
            case "Ф":
                newValue = newValue.replace(newValue, "F");
                break;
            case "ф":
                newValue = newValue.replace(newValue, "f");
                break;
            case "Х":
                newValue = newValue.replace(newValue, "H");
                break;
            case "х":
                newValue = newValue.replace(newValue, "h");
                break;
            case "Ч":
            case "Ш":
            case "Щ":
                newValue = newValue.replace(newValue, "SH");
                break;
            case "ч":
            case "ш":
            case "щ":
                newValue = newValue.replace(newValue, "sh");
                break;
            case "Ъ":
            case "Ь":
                newValue = newValue.replace(newValue, "");
                break;
            case "ъ":
            case "ь":
                newValue = newValue.replace(newValue, "");
                break;
            case "Ы":
                newValue = newValue.replace(newValue, "Y");
                break;
            case "ы":
                newValue = newValue.replace(newValue, "y");
                break;
            case "І":
                newValue = newValue.replace(newValue, "I");
                break;
            case "і":
                newValue = newValue.replace(newValue, "i");
                break;
            case "Ю":
                newValue = newValue.replace(newValue, "JU");
                break;
            case "ю":
                newValue = newValue.replace(newValue, "ju");
                break;
            case "Я":
                newValue = newValue.replace(newValue, "JA");
                break;
            case "я":
                newValue = newValue.replace(newValue, "ja");
                break;
            default:
                newValue = String.valueOf(text);
                break;
        }

        return newValue;
    }

    private static String getNameFromArray(String param) throws SQLException {
        String sql = "SELECT * FROM D_SYNONYM WHERE VALUE LIKE '%" + param + "%'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url2, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                return rs.getString("value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    private static TestClass getAntonymByName(String param) throws SQLException {
        String sql = "SELECT * FROM d_antonym WHERE VALUE LIKE '%" + param + "%' OR SECVALUE LIKE '%" + param + "%'";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url2, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TestClass testClass = new TestClass();
                if (rs.getString("value") != null) {
                    testClass.setValue(rs.getString("value"));
                }
                if (rs.getString("secvalue") != null) {
                    testClass.setSecValue(rs.getString("secvalue"));
                }
                return testClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    private static GsonMorphology getAllAntonymText(String text, ArrayList<String[][]> data) throws SQLException {
        GsonMorphology gson = new GsonMorphology();

        String[] split = text.split(" ");
        String antonym = null;
        List<String> list = getStrings(data, split);

        String titleText = null;
        String dopText = null;
        switch (language) {
            case Kz:
                titleText = "Сөйлемде " + list.size() + " антоним табылды : ";
                dopText = " сөзіне антоним ";
                break;
            case Ln:
                titleText = "Soejlemde " + list.size() + " antonijm tabyldy : ";
                dopText = " soezine antonijm ";
                break;
            case Ru:
                titleText = "В предложении было найдено " + list.size() + " антонимы : ";
                dopText = " антоним к слове ";
                break;
        }

        gson.setAntonymTitle(titleText);

        if (list.size() != 0) {
            List<String> resList = new ArrayList<>();
            for (String aSimW : list) {
                TestClass testClass = getAntonymByName(aSimW);
                if (testClass != null) {
                    if (testClass.getValue().equals(aSimW)) {
                        antonym = testClass.getSecValue();
                    } else {
                        antonym = testClass.getValue();
                    }
                }
                resList.add("[" + aSimW + "] " + dopText + "[" + antonym + "] ");
            }
            gson.setAntonymResult(resList);
        }

        return gson;
    }

    @NotNull
    private static List<String> getStrings(ArrayList<String[][]> data, String[] split) {
        List<String> list = new ArrayList<>();
        for (String s : split) {
            for (String[][] datum : data) {
                for (String[] strings : datum) {
                    for (String string : strings) {
                        string = string.replace(",", "");
                        String[] str = string.split(" ");
                        for (String s1 : str) {
                            if (s.contains(s1)) {
                                list.add(s);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public GsonResult getAnalize(String text) throws Exception {
        GsonRazbor gsonRazbor = new GsonRazbor();
        gsonRazbor.setSymantic(getSymanticAnalyze(text));
        gsonRazbor.setMorph(getMorphologyAnalize(text));

        return getGsonResult(Boolean.TRUE, gsonRazbor);
    }

    private GsonResult getSymanticAnalyze(String text) {
        GsonCreateList gsonCreateList = new GsonCreateList();
        try {
            if (!isNullOrEmpty(text)) {
                List<String> words = new ArrayList<>();
                text = text.toLowerCase();
                words.addAll(Arrays.asList(text.split(" ")));

                List<String> list = new ArrayList<>(words);

                HashMap<String, Integer> hm = new HashMap<>();

                Integer item;
                for (String wrd : list) {
                    item = hm.get(wrd);
                    if (item == null) {
                        hm.put(wrd, 1);
                    } else {
                        hm.put(wrd, item + 1);
                    }
                }
                gsonCreateList.setWordCount(words.size());//Количество слов
                gsonCreateList.setUniqueWordCount(hm.size());//Количество уникальных слов
                gsonCreateList.setmLength(text.length());//Общее число символов
                gsonCreateList.setWoutMLength(text.replace(" ", "").length());//Количество символов без пробелов
                gsonCreateList.setRepeatedWord(hm);

                return getGsonResult(Boolean.TRUE, gsonCreateList);
            }
        } catch (Exception e) {
            LOGGER.error("error" + text, e);
        }
        return getGsonResult(Boolean.FALSE, null);
    }

    private GsonResult getMorphologyAnalize(String text) throws Exception {
        if (!isNullOrEmpty(text)) {
            GsonMorphologyMain gson = new GsonMorphologyMain();
            gson.setSynonym(getSynonyms(text));
            gson.setAntonym(getAntonyms(text));

            return getGsonResult(Boolean.TRUE, gson);
        }
        return null;
    }

    private GsonMorphology getSynonyms(String text) throws Exception {
        List<DSynonym> dSynonymList = em2.createNamedQuery("DSynonym.findOnlyValue").getResultList();
        ArrayList<String[][]> data = new ArrayList<>();
        for (DSynonym dSynonym : dSynonymList) {
            String[] s = dSynonym.getValue().split(" ");
            for (String s2 : s) {
                data.add(new String[][]{{s2}});
            }
        }

        return getAllSynonymInText(text, data);
    }

    private GsonMorphology getAllSynonymInText(String text, ArrayList<String[][]> data) throws Exception {
        GsonMorphology gson = new GsonMorphology();
        String[] split = text.split(" ");
        List<String> list = getStrings(data, split);

        String titleText = null;
        String dopText = null;
        switch (language) {
            case Kz:
                titleText = "Сөйлемде " + list.size() + " синоним табылды : ";
                dopText = " сөзіне синоним ";
                break;
            case Ln:
                titleText = "Soejlemde " + list.size() + " sijnonijm tabyldy : ";
                dopText = " soezine sijnonijm ";
                break;
            case Ru:
                titleText = "В предложении было найдено " + list.size() + " синонимы : ";
                dopText = " синоним к слове ";
                break;
        }

        if (list.size() != 0) {
            List<String> resList = new ArrayList<>();
            for (String aSimW : list) {
                gson.setSynonymTitle(titleText);
                List<String> list2 = new ArrayList<>();
                String nameFromArray = getNameFromArray(aSimW);
                if (nameFromArray != null) {
                    String[] strings1 = getNameFromArray(aSimW).split(" ");

                    Collections.addAll(list2, strings1);
                } else if (isNullOrEmpty(getNameFromArray(aSimW))) {
                    String imW = aSimW.substring(0, 3);
                    list2.add(getNameFromArray(imW));
                }
                resList.add("[" + aSimW + "] " + dopText + list2);
            }
            gson.setSynonymResult(resList);
        }

        return gson;
    }

    private GsonMorphology getAntonyms(String text) throws Exception {
        List<DAntonym> dSynonymList = em2.createNamedQuery("DAntonym.findAll").getResultList();
        ArrayList<String[][]> data = new ArrayList<>();

        for (DAntonym dAntonym : dSynonymList) {
            String[] value = dAntonym.getValue().split(" ");
            String[] secValue = dAntonym.getSecValue().split(" ");
            data.add(new String[][]{{value[0], secValue[0]}});
        }

        return getAllAntonymText(text, data);
    }

    public GsonResult getTranslate(String text) {
        if (!isNullOrEmpty(text)) {
            try {
                text = text.toLowerCase();
                List<String> list = new ArrayList<>();

                list.add(getTranslateString(text));
                return getGsonResult(Boolean.TRUE, list);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return getGsonResult(Boolean.FALSE, null);
    }
}