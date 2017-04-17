package kz.study.session;

import kz.study.entity.DEnding;
import kz.study.entity.DSuffix;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";

    public static void main(String[] args) throws SQLException {
        String s = "Менің атым Аслан";
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
//                    System.out.println("array " + Arrays.toString(array));
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
////                                    String a = Arrays.toString(string.split(t.getValue()));
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
//                                String a = Arrays.toString(string.split(t.getValue()));
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
}