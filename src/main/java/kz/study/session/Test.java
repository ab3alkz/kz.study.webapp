package kz.study.session;

import kz.study.entity.DEnding;
import kz.study.entity.DSuffix;

import java.sql.*;
import java.util.*;

import static kz.study.util.Util.isNullOrEmpty;

/**
 * @author kussein-at
 * @since 16.04.2017.
 */
public class Test {

    private class TestClass<E> {
        private E id;
        private String value;
        private int adId;

        public E getId() {
            return id;
        }

        public void setId(E id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getAdId() {
            return adId;
        }

        public void setAdId(int adId) {
            this.adId = adId;
        }
    }

    private static final String driverName = "com.mysql.kz.study.jdbc.Jdbc.Driver";
    private static final String url = "kz.study.jdbc.Jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";

    public static void main(String[] args) throws SQLException {
        String s = "Асланға";
//        String s = "Сегодня холодный зимний зимний день день Иван";
        Test test = new Test();
        List<TestClass> list = test.getTestBd("SELECT * FROM all_ending");
        test.getTest(list, s);
    }

    /**
     * @param list;
     * @param src;
     * @desc жалгаулар;
     */
    private void getTest(List<TestClass> list, String src) throws SQLException {
        List<String> pList = new ArrayList<>();
        if (!isNullOrEmpty(src)) {
            String[] strings = src.split(" ");
            for (String string : strings) {
                for (TestClass t : list) {
//                    if (string.length() >= 4) {
                    string = string.replace(t.getValue(), " " + t.getValue() + " ");

                    if (string.contains(t.getValue())) {
                        System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
                    }
//                    String[] array = string.split(t.getValue());
//                    System.out.println("array " + Constants.toString(array));
//                    for (String s : array) {
//                        pList.add(s);
//                    }

//                            while (s.equals(t.getValue())) {
//                                System.out.println(" " + s + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//
////                                String[] sD = s.split(t.getValue());
////
////
////                                System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
////                                if (sD.length < 2) {
////                                    String a = Constants.toString(string.split(t.getValue()));
////                                    a = a.replace("[", "");
////                                    a = a.replace("]", "");
////                                    System.out.println(" " + a + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
////                                }
////                    for (String newValue : string.split(t.getValue())) {
//////                        System.out.println(newValue);
////                    }
////                    sD = string.replaceAll(t.getValue(), "");
////
//                            }


//                    string = string.substring(string.length() - t.getValue().length());

//                        if (string.contains(t.getValue())) {
//
//
//                            String[] sD = string.split(t.getValue());
//
//
//                            System.out.println(" " + string + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//                            if (sD.length < 2) {
//                                String a = Constants.toString(string.split(t.getValue()));
//                                a = a.replace("[", "");
//                                a = a.replace("]", "");
//                                System.out.println(" " + a + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
//                            }
////                    for (String newValue : string.split(t.getValue())) {
//////                        System.out.println(newValue);
////                    }
////                    sD = string.replaceAll(t.getValue(), "");
////
//                        }
                }
            }
        }
//        j(pList, list);

    }

//    private void j(List<String> s, List<TestClass> list) {
//
//        for (TestClass s1 : list) {
//            if (s.contains(s1.getValue())) {
//                System.out.println(s);
//            }
//        }
//    }

    private DEnding getTestBdC(int id) throws SQLException {
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

    private List<TestClass> getTestBd(String sql) throws SQLException {
        List<TestClass> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
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




    /*******************************************************************************************/
    private void tt(String s) {
        String[][] SimWorldBase = { { "день", "будень" },
                { "будень", "будни", }, { "зимний", "зима", } };

        String[] NameBaseS = { "Маша", "Света", "Иван", "Сергей", "Ольга",
                "Александр" };
        Set<String> setEq = new HashSet<>();
        ArrayList<String> simW = new ArrayList<>();
        s = s.toLowerCase();
        s = s.trim();
        String[] SplitS = s.split(" ");

        for (String Split : SplitS) {
            boolean b = setEq.add(Split);
            if (!b) {
                simW.add(Split);
            }
        }

        System.out.println("========= Характеристика предложения: =======");

        if (s.endsWith("?")) {
            System.out.println("[Предложение вопросительное]");
        }
        if (s.endsWith("!")) {
            System.out.println("[Предложение восклицательное]");
        }
        if (s.endsWith(".") || s.endsWith("") || s.endsWith(" ")) {
            System.out.println("[Предложение обычное]");
        }
        System.out.println("[Предложение состоит из " + SplitS.length
                + " слов]");

        for (String Split : SplitS) {
            for (String NameBase : NameBaseS) {
                if (Split.equalsIgnoreCase(NameBase)) {
                    System.out
                            .println("В предложении были найдены следующие имена: "
                                    + NameBase);
                }
            }
        }

        String result = "";
        if (simW.size() != 0) {
            System.out.println("В предложении было найдено " + simW.size()
                    + " похожих слова: ");
            for (String aSimW : simW) {
                System.out.print("[" + aSimW + "] ");
                for (String[] aSimWorldBase : SimWorldBase) {
                    if (aSimWorldBase[0].equalsIgnoreCase(aSimW)) {
                        result = aSimWorldBase[1];
                        System.out.println("Синонимы [" + result + "]");
                        // Логическая цепочка.
                        if (aSimWorldBase[0].equalsIgnoreCase(result)) {
                            result = aSimWorldBase[1];
                            System.out.println("Синонимы к син [" + result
                                    + "]");
                            break;
                        }
                        break;
                    }
                }
            }

        } else {
            System.out.println("[Похожих слов в предложении не найдено]");
        }
    }
}