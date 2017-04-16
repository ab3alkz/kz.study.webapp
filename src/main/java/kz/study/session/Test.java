package kz.study.session;

import kz.study.entity.DEnding;
import kz.study.entity.DSuffix;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Test test = new Test();
        String s = "Балалармен";
        List<TestClass> list = test.getTestBd("SELECT * FROM all_ending");
        test.getTest(list, s);
    }


    private void getTest(List<TestClass> list, String s) throws SQLException {
        String[] strings = s.split(" ");
        for (String string : strings) {
            for (TestClass t : list) {
                if (string.contains(t.getValue())) {
                    String[] sD = string.split(t.getValue());
                    if (sD.length < 2) {
                        String a = Arrays.toString(string.split(t.getValue()));
                        a = a.replace("[", "");
                        a = a.replace("]", "");
                        System.out.println(" " + a + " " + t.getValue() + " " + getTestBdC(t.getAdId()).getValue());
                    }
//                    for (String newValue : string.split(t.getValue())) {
////                        System.out.println(newValue);
//                    }
//                    sD = string.replaceAll(t.getValue(), "");
//
                }
            }
        }
    }

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