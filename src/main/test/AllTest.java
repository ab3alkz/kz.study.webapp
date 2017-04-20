import com.sun.istack.internal.Nullable;
import kz.study.entity.DEnding;
import kz.study.gson.GsonAllDic;
import org.junit.Test;

import kz.study.jdbc.Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static kz.study.jdbc.Jdbc.getTestBd;


/**
 * @author kussein-at
 * @since 19.04.2017.
 */
public class AllTest {

    private static final String driverName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/arma";
    private static final String user = "pma";
    private static final String pass = "123456";

    @Test
    public void getTest() throws SQLException {
        String s = "Асланға";

        List<String> list = getTestAllName();
        if (list != null) {
            for (String names : list) {
                names = names.toLowerCase();
                s = s.toLowerCase();
                if (s.contains(names)) {
                    s = s.replace(names, "");

                    for (GsonAllDic string : getAllEndings()) {
                        if (s.equalsIgnoreCase(string.getValue())) {
                            DEnding obj = getTestBdC((Integer) string.getAddDopValue());
                            System.out.println("Тубири: " + names);
                            System.out.println(string.getValue() + "    " + obj.getValue());
                        }
                    }
                }
            }
        }


    }

    /**
     * @desc Все окончания
     */
    private static List<GsonAllDic> getAllEndings() throws SQLException {
        return getTestBd();
    }

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
                gsonAllDic.setAddValue(rs.getString("D_ENDING_ID"));
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
                list.add(rs.getString("VALUE"));
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
