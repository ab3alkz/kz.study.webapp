package kz.study.session;

import kz.study.entity.DAntonym;
import kz.study.entity.DEnding;
import kz.study.gson.TestClass;

import java.sql.*;
import java.util.*;

import static kz.study.util.Util.isNullOrEmpty;

/**
 * @author kussein-at
 * @since 16.04.2017.
 */
public class Test {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String url2 = "jdbc:mysql://localhost:3306/armasecond";
    private static final String user = "pma";
    private static final String pass = "123456";

//    public static void main(String[] args) throws SQLException {
//        String s = "асланмен әлихан доп ойнады";
////        String s = "Сегодня холодный зимний зимний день день Иван";
//        Test test = new Test();
//        List<TestClass> list = test.getTestBd();
//        test.getTest(list, s);
//    }

    private static List<TestClass> getTestBd() throws SQLException {
        List<TestClass> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM all_ending");
            while (rs.next()) {
                TestClass testClass = new TestClass();
                testClass.setId(rs.getInt("id"));
                testClass.setValue(rs.getString("value"));
                testClass.setAdId(rs.getInt("d_ending_id"));

                list.add(testClass);
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

//    private void j(List<String> s, List<TestClass> list) {
//
//        for (TestClass s1 : list) {
//            if (s.contains(s1.getValue())) {
//                System.out.println(s);
//            }
//        }
//    }

    private static List<TestClass> getTestDynamic(String sql) throws SQLException {
        List<TestClass> list = new ArrayList<>();
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
                testClass.setValue(rs.getString("value"));
                testClass.setSecValue(rs.getString("secvalue"));

                list.add(testClass);
            }
            return list;
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

    public static void main(String[] args) throws SQLException {
        String antSQL = "SELECT * FROM d_antonym ";
//        String s1 = "асырайдындар";
        String s1 = "асырайдындар Сегодня холодный зимний день Иван асырайды қорқақ қорқақтар";
        ArrayList<String[][]> data = new ArrayList<>();


        for (TestClass dAntonym : getTestDynamic(antSQL)) {
            String[] value = dAntonym.getValue().split(" ");
            String[] secValue = dAntonym.getSecValue().split(" ");
            data.add(new String[][]{{value[0], secValue[0]}});
        }

        getAllSynonymInText(s1, data);
    }

    private static TestClass getNameFromArray(String param) throws SQLException {
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
                testClass.setValue(rs.getString("value"));
                testClass.setSecValue(rs.getString("secvalue"));

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

    private static void getAllSynonymInText(String text, ArrayList<String[][]> data) throws SQLException {
        String[] split = text.split(" ");
        String antonym = "";
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

        System.out.println("В предложении было найдено " + list.size() + " антонимы : ");

        if (list.size() != 0) {
            for (String aSimW : list) {
                TestClass testClass = getNameFromArray(aSimW);
                if (testClass != null) {
                    if (testClass.getValue().equals(aSimW)) {
                        antonym = testClass.getSecValue();
                    } else {
                        antonym = testClass.getValue();
                    }
                }
                List<String> resList = new ArrayList<>();
                resList.add("[" + aSimW + "] " + " антоним к слове " + "[" + antonym + "] ");

                System.out.println(resList);
            }
        }
    }

    /*******************************************************************************************/
//    private static void tt(String s) {
//        String[][] SimWorldBase = {{"день", "будень"},
//                {"будень", "будни",}, {"зимний", "зима",}};
//
//        String[] NameBaseS = {"Маша", "Света", "Иван", "Сергей", "Ольга",
//                "Александр"};
//        Set<String> setEq = new HashSet<>();
//        ArrayList<String> simW = new ArrayList<>();
//        s = s.toLowerCase();
//        s = s.trim();
//        String[] SplitS = s.split(" ");
//
//        for (String Split : SplitS) {
//            boolean b = setEq.add(Split);
//            if (!b) {
//                simW.add(Split);
//            }
//        }
//
//        System.out.println("========= Характеристика предложения: =======");
//
//        if (s.endsWith("?")) {
//            System.out.println("[Предложение вопросительное]");
//        }
//        if (s.endsWith("!")) {
//            System.out.println("[Предложение восклицательное]");
//        }
//        if (s.endsWith(".") || s.endsWith("") || s.endsWith(" ")) {
//            System.out.println("[Предложение обычное]");
//        }
//        System.out.println("[Предложение состоит из " + SplitS.length
//                + " слов]");
//
//        for (String Split : SplitS) {
//            for (String NameBase : NameBaseS) {
//                if (Split.equalsIgnoreCase(NameBase)) {
//                    System.out
//                            .println("В предложении были найдены следующие имена: "
//                                    + NameBase);
//                }
//            }
//        }
//
//        String result = "";
//        if (simW.size() != 0) {
//            System.out.println("В предложении было найдено " + simW.size()
//                    + " похожих слова: ");
//            for (String aSimW : simW) {
//                System.out.print("[" + aSimW + "] ");
//                for (String[] aSimWorldBase : SimWorldBase) {
//                    if (aSimWorldBase[0].equalsIgnoreCase(aSimW)) {
//                        result = aSimWorldBase[1];
//                        System.out.println("Синонимы [" + result + "]");
//                        // Логическая цепочка.
//                        if (aSimWorldBase[0].equalsIgnoreCase(result)) {
//                            result = aSimWorldBase[1];
//                            System.out.println("Синонимы к син [" + result
//                                    + "]");
//                            break;
//                        }
//                        break;
//                    }
//                }
//            }
//
//        } else {
//            System.out.println("[Похожих слов в предложении не найдено]");
//        }
//    }

//    /**
//     * @param list;
//     * @param src;
//     * @desc жалгаулар;
//     */
//    private void getTest(List<TestClass> list, String src) throws SQLException {
//        List<String> pList = new ArrayList<>();
//        if (!isNullOrEmpty(src)) {
//            String[] strings = src.split(" ");
//            for (String string : strings) {
//                for (TestClass t : list) {
////                    if (string.length() >= 4) {
//                    string = string.replace(t.getValue(), " " + t.getValue() + " ");
//
//                    if (string.contains(t.getValue())) {
//                        System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//                    }
////                    String[] array = string.split(t.getValue());
////                    System.out.println("array " + Constants.toString(array));
////                    for (String s : array) {
////                        pList.add(s);
////                    }
//
////                            while (s.equals(t.getValue())) {
////                                System.out.println(" " + s + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
////
//////                                String[] sD = s.split(t.getValue());
//////
//////
//////                                System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//////                                if (sD.length < 2) {
//////                                    String a = Constants.toString(string.split(t.getValue()));
//////                                    a = a.replace("[", "");
//////                                    a = a.replace("]", "");
//////                                    System.out.println(" " + a + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//////                                }
//////                    for (String newValue : string.split(t.getValue())) {
////////                        System.out.println(newValue);
//////                    }
//////                    sD = string.replaceAll(t.getValue(), "");
//////
////                            }
//
//
////                    string = string.substring(string.length() - t.getValue().length());
//
////                        if (string.contains(t.getValue())) {
////
////
////                            String[] sD = string.split(t.getValue());
////
////
////                            System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
////                            if (sD.length < 2) {
////                                String a = Constants.toString(string.split(t.getValue()));
////                                a = a.replace("[", "");
////                                a = a.replace("]", "");
////                                System.out.println(" " + a + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
////                            }
//////                    for (String newValue : string.split(t.getValue())) {
////////                        System.out.println(newValue);
//////                    }
//////                    sD = string.replaceAll(t.getValue(), "");
//////
////                        }
//                }
//            }
//        }
////        j(pList, list);
//
//    }
//
//    private DEnding getTestBdC(int id) throws SQLException {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        DEnding dending = new DEnding();
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url, user, pass);
//            stmt = conn.createStatement();
//            String sql = "SELECT * FROM d_ending WHERE id = " + id;
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                dending.setId((long) rs.getInt("id"));
//                dending.setValue(rs.getString("name"));
//            }
//            return dending;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            rs.close();
//            stmt.close();
//            conn.close();
//        }
//        return null;
//    }
//
//    /*-------------------------------------------------------------*/
//    public static void main(String[] args) throws SQLException {
//        String antSQL = "SELECT * FROM D_SYNONYM ";
////        String s1 = "асырайдындар";
//        String s1 = "асырайдындар Сегодня холодный зимний день Иван асырайды";
//        ArrayList<String[][]> data = new ArrayList<>();
//
//        for (TestClass string : getTestDynamic(antSQL)) {
//            String[] s = string.getValue().split(" ");
//            for (String s2 : s) {
//                data.add(new String[][]{{s2}});
//            }
//        }
//
//        getAllSynonymInText(s1, data);
//    }
//
//    private static String getNameFromArray(String param) throws SQLException {
//        String sql = "SELECT * FROM D_SYNONYM WHERE VALUE LIKE '%" + param + "%'";
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(driverName);
//            conn = DriverManager.getConnection(url2, user, pass);
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                return rs.getString("value");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            assert rs != null;
//            rs.close();
//            stmt.close();
//            conn.close();
//        }
//        return null;
//    }
//
//    private static void getAllSynonymInText(String text, ArrayList<String[][]> data) throws SQLException {
//        String[] split = text.split(" ");
//        List<String> list = new ArrayList<>();
//        for (String s : split) {
//            for (String[][] datum : data) {
//                for (String[] strings : datum) {
//                    for (String string : strings) {
//                        string = string.replace(",", "");
//                        String[] str = string.split(" ");
//                        for (String s1 : str) {
//                            if (s.contains(s1)) {
//                                list.add(s);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        if (list.size() != 0) {
//            System.out.println("В предложении было найдено " + list.size() + " похожих слова: ");
//            for (String aSimW : list) {
//                List<String> list2 = new ArrayList<>();
//                String nameFromArray = getNameFromArray(aSimW);
//                if (nameFromArray != null) {
//                    String[] strings1 = getNameFromArray(aSimW).split(" ");
//
//                    Collections.addAll(list2, strings1);
//                } else if (isNullOrEmpty(getNameFromArray(aSimW))){
//                    String imW = aSimW.substring(0, 3);
//                    list2.add(getNameFromArray(imW));
//                }
//
//
//                System.out.println("[" + aSimW + "] " + " синоним к слове " + list2);
//            }
//        } else {
//            System.out.println("[Похожих слов в предложении не найдено]");
//        }
//    }
}