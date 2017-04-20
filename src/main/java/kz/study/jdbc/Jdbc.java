package kz.study.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kussein-at
 * @since 19.04.2017.
 */
public class Jdbc {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";

    public static List<String> getTestBd(String sql) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("value"));
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
}
