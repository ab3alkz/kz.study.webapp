import com.sun.istack.internal.Nullable;
import kz.study.entity.DEnding;
import kz.study.gson.GsonAllDic;
import org.junit.Test;

import kz.study.jdbc.Jdbc;

import java.lang.invoke.SwitchPoint;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kz.study.jdbc.Jdbc.getTestBd;
import static kz.study.util.Util.isNullOrEmpty;


/**
 * @author kussein-at
 * @since 19.04.2017.
 */
public class AllTest {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";
    private static List<String> mainName = new ArrayList<>();

    @Test
    public void getTest() throws SQLException {
        List<String> textList = new ArrayList<>();
        String newS = "асланмен әлихан доп ойнады ";
        textList.addAll(Arrays.asList(newS.toLowerCase().split(" ")));


        List<String> allNameList = getTestAllName();
        if (allNameList != null) {

//            String[] sNew = s.split(" ");
            for (String s : getUniqueName(allNameList, textList)) {
//                if (newS.contains(allName)) {
//                    String ending = newS.replace(allName, "");
//                    newS = "";
//                    mainName = names;
//                    getEngingAnalyze(ending, 1);
//                } else {
//
//                }
                if (!isNullOrEmpty(s)) {
                    getEngingAnalyze(s, 1);
                    break;
                }
            }
//            for (String allName : allNameList) {
////                for (String newS : sNew) {
//
//
//            }
//            }
        }

    }

    private List<String> getUniqueName(List<String> allNameLis, List<String> textList) {
        List<String> uniqueName = new ArrayList<>();
        for (String allName : allNameLis) {
            for (String text : textList) {
                if (text.contains(allName)) {
                    mainName.add(allName);
                    String stext = text.replace(allName, "");
                    uniqueName.add(stext);
                    textList.remove(text);
                    break;
                } else if (!text.contains(allName)) {
                    System.out.println(text);
                }
            }
        }

        return uniqueName;
    }

    private String getValueOrEmpty(String value) {
        if (!isNullOrEmpty(value)) {
            return value;
        }
        return "";
    }

    private void getEngingAnalyze(String ending, long type) throws SQLException {
        if (type == 1) {
            for (GsonAllDic string : getTestBd()) {
                if (ending.equalsIgnoreCase(string.getValue())) {
                    DEnding obj = getTestBdC((Integer) string.getAddDopValue());
//                    System.out.println("Тубири: " + mainName);
//                    System.out.println(string.getValue() + getTestDEnding(Integer.parseInt(string.getAddValue())).getValue() + "    " + getValueOrEmpty(obj.getValue()));
                }
            }
        }
//        else {
//            for (GsonAllDic string : getTestBd()) {
//                if (ending.contains(string.getValue())) {
//
//                    DEnding obj = getTestBdC((Integer) string.getAddDopValue());
//
//                    System.out.println("Тубири: " + ending.replace(string.getValue(), ""));
//
//                    System.out.println(string.getValue() + "    " + obj.getValue());
//                }
//            }
//        }
    }

    /**
     * @desc Все окончания
     */
    @Nullable
    private static List<GsonAllDic> getTestBd() throws SQLException {
        List<GsonAllDic> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM all_ending s");
            while (rs.next()) {
                GsonAllDic gsonAllDic = new GsonAllDic();
                gsonAllDic.setId(rs.getString("id"));
                gsonAllDic.setAddValue(String.valueOf(rs.getInt("d_ending_id")));
                gsonAllDic.setValue(rs.getString("value"));
                gsonAllDic.setAddDopValue(rs.getInt("D_CASE_ID"));
                list.add(gsonAllDic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            assert rs != null;
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Nullable
    private DEnding getTestBdC(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DEnding dending = new DEnding();
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM d_case WHERE id = " + id;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dending.setId((long) rs.getInt("id"));
                dending.setValue(rs.getString("name"));
            }
            return dending;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Nullable
    private DEnding getTestDEnding(int id) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DEnding dending = new DEnding();
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM d_ending WHERE id = " + id;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dending.setId((long) rs.getInt("id"));
                dending.setValue(rs.getString("name"));
            }
            return dending;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Nullable
    private List<String> getTestAllName() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM p_person";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("value").toLowerCase());
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    @Test
    public void translator() {
        String text = "Жұлдыз аналитик";
        text = text.toLowerCase();
        char[] myCharArray = text.toCharArray();
        List<String> list = new ArrayList<>();
        StringBuilder latynTest = new StringBuilder();
        for (char c : myCharArray) {
            latynTest.append(getTestStringLatyn(c));
        }
        list.add(latynTest.toString());
        for (String s : list) {
            System.out.println(s);
        }
    }

    private String getTestStringLatyn(char text) {

        String newValue = String.valueOf(text);

        switch (newValue) {
            case "а":
                newValue = newValue.replace(newValue, "a");
                break;
            case "ә":
                newValue = newValue.replace(newValue, "ae");
                break;
            case "б":
                newValue = newValue.replace(newValue, "b");
                break;
            case "в":
                newValue = newValue.replace(newValue, "v");
                break;
            case "г":
                newValue = newValue.replace(newValue, "g");
                break;
            case "ғ":
                newValue = newValue.replace(newValue, "gh");
                break;
            case "д":
                newValue = newValue.replace(newValue, "d");
                break;
            case "ё":
            case "е":
            case "э":
                newValue = newValue.replace(newValue, "e");
                break;
            case "ж":
                newValue = newValue.replace(newValue, "zh");
                break;
            case "з":
                newValue = newValue.replace(newValue, "z");
                break;
            case "и":
                newValue = newValue.replace(newValue, "ij");
                break;
            case "й":
                newValue = newValue.replace(newValue, "j");
                break;
            case "к":
                newValue = newValue.replace(newValue, "k");
                break;
            case "қ":
                newValue = newValue.replace(newValue, "q");
                break;
            case "л":
                newValue = newValue.replace(newValue, "l");
                break;
            case "м":
                newValue = newValue.replace(newValue, "m");
                break;
            case "н":
                newValue = newValue.replace(newValue, "n");
                break;
            case "ң":
                newValue = newValue.replace(newValue, "ng");
                break;
            case "о":
                newValue = newValue.replace(newValue, "o");
                break;
            case "ө":
                newValue = newValue.replace(newValue, "oe");
                break;
            case "п":
                newValue = newValue.replace(newValue, "p");
                break;
            case "р":
                newValue = newValue.replace(newValue, "r");
                break;
            case "с":
                newValue = newValue.replace(newValue, "s");
                break;
            case "т":
                newValue = newValue.replace(newValue, "t");
                break;
            case "у":
                newValue = newValue.replace(newValue, "w");
                break;
            case "ұ":
                newValue = newValue.replace(newValue, "u");
                break;
            case "ү":
                newValue = newValue.replace(newValue, "ue");
                break;
            case "ф":
                newValue = newValue.replace(newValue, "f");
                break;
            case "х":
                newValue = newValue.replace(newValue, "h");
                break;
            case "ш":
                newValue = newValue.replace(newValue, "sh");
                break;
            case "щ":
                newValue = newValue.replace(newValue, "sh");
                break;
            case "ъ":
            case "ь":
                newValue = newValue.replace(newValue, "");
                break;
            case "ы":
                newValue = newValue.replace(newValue, "y");
                break;
            case "і":
                newValue = String.valueOf(text);
                newValue = newValue.replace(newValue, "i");
                break;
            case "ю":
                newValue = newValue.replace(newValue, "ju");
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
}


